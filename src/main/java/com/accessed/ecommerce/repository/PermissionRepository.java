package com.accessed.ecommerce.repository;

import com.accessed.ecommerce.model.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface PermissionRepository extends MongoRepository<Permission, String> {}
