package com.accessed.ecommerce.utils.helper;

import com.accessed.ecommerce.exception.AppException;
import com.accessed.ecommerce.utils.enums.ErrorCode;
import org.springframework.data.util.Pair;

public class PaginationInput {

        public static Pair<Integer, Integer> validatePaginationInput(int pageNumberInt, int pageSizeInt) {
            try {
                if (pageNumberInt < 0 || pageSizeInt < 0) {
                    throw new AppException(ErrorCode.WRONG_INPUT);
                }
                return Pair.of(pageNumberInt, pageSizeInt);
            } catch (NumberFormatException e) {
                throw new AppException(ErrorCode.WRONG_INPUT);
            }
        }

}
