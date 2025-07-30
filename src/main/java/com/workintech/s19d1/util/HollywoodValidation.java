package com.workintech.s19d1.util;

import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class HollywoodValidation {
    public static void checkId(Long id) {
        if(id<0 || id==null){
            throw new ApiException("id cannot be less than o or null id:"+id, HttpStatus.BAD_REQUEST);
        }
    }
}