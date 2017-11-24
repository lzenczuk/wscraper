package com.github.lzenczuk.wscraper;

/**
 * Created by dev on 24/11/17.
 */
public class ServerErrorResult implements DownloadResult {

    private final String url;
    private final String errorMessage;
    private final int code;

    public ServerErrorResult(String url, int code, String errorMessage) {
        this.url = url;
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ServerErrorResult{" +
                "url='" + url + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", code=" + code +
                '}';
    }
}
