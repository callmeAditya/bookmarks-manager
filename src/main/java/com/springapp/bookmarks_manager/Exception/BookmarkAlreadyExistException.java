package com.springapp.bookmarks_manager.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookmarkAlreadyExistException extends RuntimeException {
    public BookmarkAlreadyExistException(String message) {
        super(message);
    }

    public BookmarkAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}