package com.zqq.ssm.exception;

/**
 * @Author zqq
 * @Date 2019/11/13  16:26
 */
public class UserException extends Exception {
    
    public String message;

    public UserException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
