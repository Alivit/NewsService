package ru.clevertec.exception_handler.exception;

public class ServerErrorException extends RuntimeException{

    public ServerErrorException(String msg) {
        super(msg);
    }
}
