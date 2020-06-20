package com.bootstudy.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookResourceNotFoundException extends RuntimeException {
    public BookResourceNotFoundException(String bookId) {
        super("Book is not found (bookId = " + bookId + ")");
    }
}
