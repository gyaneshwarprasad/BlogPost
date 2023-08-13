package com.blogepost.exceptions;

import org.springframework.http.HttpStatus;

public class BlogPostException extends Throwable{

    public BlogPostException(HttpStatus httpStatus, String invalidJwtSignature) {
    }
}
