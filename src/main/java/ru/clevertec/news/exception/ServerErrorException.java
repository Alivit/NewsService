package ru.clevertec.news.exception;

public class ServerErrorException extends RuntimeException{

    public ServerErrorException(String msg) {
        super(msg);
    }
}
