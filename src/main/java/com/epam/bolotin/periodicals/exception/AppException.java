package com.epam.bolotin.periodicals.exception;

/**
 * @author: Viacheslav Bolotin
 * @date: 03.01.2023
 */

public class AppException extends Exception{
    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
