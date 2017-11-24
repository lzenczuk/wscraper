package com.github.lzenczuk.wscraper;

/**
 * Created by dev on 24/11/17.
 */
public class ClientErrorResponse implements DownloadResult {
    private final String message;

    public ClientErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ClientErrorResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
