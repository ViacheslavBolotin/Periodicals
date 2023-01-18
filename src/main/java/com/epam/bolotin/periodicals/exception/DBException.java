package com.epam.bolotin.periodicals.exception;

public class DBException extends AppException{

    public DBException(String errCannotObtainDataById) {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
