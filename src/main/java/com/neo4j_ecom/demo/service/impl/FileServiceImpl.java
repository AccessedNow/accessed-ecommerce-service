package com.neo4j_ecom.demo.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.neo4j_ecom.demo.exception.AppException;
import com.neo4j_ecom.demo.service.FileService;
import com.neo4j_ecom.demo.utils.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final TransferManager transferManager;
    private final ExecutorService executorService;
    private final AmazonS3 amazonS3;

    @Value("${file.image.base-uri}")
    private String baseURI;

    @Value("${firebase.link-base}")
    String linkBase;
    //
//    @Value("${aws.s3.bucket.name}")
    String bucketName;

    public FileServiceImpl(TransferManager transferManager, ExecutorService executorService, AmazonS3 amazonS3,  @Value("${aws.s3.bucket.name}") String bucketName) {
        this.transferManager = transferManager;
        this.executorService = executorService;
        this.bucketName = bucketName;
        this.amazonS3 = amazonS3;

    }


    @Override
    public void createUploadedFolder(String folder) throws URISyntaxException {
        URI uri = new URI(folder);
        Path path = Paths.get(uri);
        File tmpDir = new File(path.toString());
        if (!tmpDir.isDirectory()) {
            try {
                Files.createDirectory(tmpDir.toPath());
                log.info("created directory {}", tmpDir.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.info("directory {} already exists", tmpDir.getAbsolutePath());
        }
    }

    @Override
    public String storeFile(MultipartFile file, String folder) throws URISyntaxException {
        String finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        log.info("finalName in storeFile: {}", finalName);

        finalName = finalName.replaceAll("\\s", "");

        URI uri = new URI(baseURI + folder + "/" + finalName);


        log.info("uri in storeFile: {}", uri);
        Path path = Paths.get(uri);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalName;
    }

    @Override
    public void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new AppException(ErrorCode.MISSING_REQUIRE_PARAM);
        }

        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("jpg", "png", "jpeg");
        List<String> allowedMimeTypes = Arrays.asList(
                "image/jpg",
                "image/jpeg",
                "image/png"
        );

        // Validate extension
        boolean isValidExtension = allowedExtensions.stream().anyMatch(ext ->
                fileName.toLowerCase().endsWith("." + ext));
        if (!isValidExtension) {
            throw new AppException(ErrorCode.INVALID_FILE_EXTENSION);
        }

        // Validate MIME type
        String contentType = file.getContentType();
        log.info("contentType: {}", contentType);

        boolean isValidMimeType = allowedMimeTypes.contains(contentType);
        log.info("isValidMimeType: {}", isValidMimeType);

        if (!isValidMimeType) {
            throw new AppException(ErrorCode.INVALID_FILE_MIME_TYPE);
        }

        // Check file size
        long maxSize = 1024 * 1024 * 1000; // 100 MB

        log.info("file size: {}", file.getSize());
        if (file.getSize() > maxSize) {
            throw new AppException(ErrorCode.INVALID_FILE_SIZE);
        }
    }

    @Override
    public String storeFileFirebase(MultipartFile file, String folder) throws URISyntaxException, IOException {

        String finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        log.info("finalName in storeFile: {}", finalName);

        finalName = finalName.replaceAll("\\s", "");

        InputStream inputStream = file.getInputStream();
        Bucket bucket = StorageClient.getInstance().bucket();
        bucket.create(folder + "/" + finalName, inputStream, file.getContentType());

        String link = linkBase + URLEncoder.encode(folder + "/", StandardCharsets.UTF_8) + finalName + "?alt=media";
        log.info("link: {}", link);
        return link;
    }

    @Override
    public List<String> storeFileS3(List<File> files, String folder) throws URISyntaxException, IOException, InterruptedException {
        List<Future<String>> futures = new ArrayList<>();
        for (File file : files) {
            futures.add(executorService.submit(()->{
                String finalName = System.currentTimeMillis() + "-" + file.getName();
                log.info("finalName in storeFile: {}", finalName);
                finalName = folder + "/" + finalName.replaceAll("\\s", "");
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, finalName, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead);

                Upload upload = transferManager.upload(putObjectRequest);
                upload.waitForCompletion();

                return transferManager.getAmazonS3Client().getUrl(bucketName,finalName).toString();
            }));
        }
        List<String> urls = new ArrayList<>();
        for (Future<String> future : futures) {
            try {
                urls.add(future.get()); // Nhận kết quả URL của từng tệp
            } catch (Exception e) {
                throw new RuntimeException("Error in parallel upload", e);
            }
        }
        return urls;
    }

    @Override
    public void deleteFileS3(List<String> imageURLs) throws FileNotFoundException {
        for (String imageURL : imageURLs) {
            String objectKey = URLEncoder.encode(getFileKey(imageURL), StandardCharsets.UTF_8);
            if (objectKey!=null){
                amazonS3.deleteObject(bucketName, objectKey);
            }
        }
    }

    @Override
    public void deleteFileFirebase(String path) throws FileNotFoundException {
        try {

            String fileName = this.extractFileNameFromUrl(path);

            Blob blob = StorageClient.getInstance().bucket().get(fileName);
            if (blob != null) {
                boolean deleted = blob.delete();
                log.info("deleted file status: {}", deleted);
            } else {
                throw new AppException(ErrorCode.FILE_NOT_FOUND);
            }

        } catch (Exception e) {
            throw new AppException(ErrorCode.FILE_NOT_FOUND);
        }
    }



    private String extractFileNameFromUrl(String fileUrl) {
        // Extract the part after "/o/" and decode the URL-encoded characters
        String fileName = fileUrl.split("/o/")[1].split("\\?alt=media")[0];
        return fileName.replace("%2F", "/");  // Replace URL-encoded %2F with /
    }

    public String getFileKey(String url){
        int index = url.indexOf(".com/") + 5;
        return url.substring(index);
    }

}