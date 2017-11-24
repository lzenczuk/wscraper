package com.github.lzenczuk.wscraper;

/**
 * Created by dev on 24/11/17.
 */
public class NotFoundResponse implements DownloadResult{
    private final String url;

    public NotFoundResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "NotFoundResponse{" +
                "url='" + url + '\'' +
                '}';
    }
}
