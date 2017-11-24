package com.github.lzenczuk.wscraper;

/**
 * Created by dev on 24/11/17.
 */
public class SuccessfulResponse implements DownloadResult{
    private final String url;
    private final String content;

    public SuccessfulResponse(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "SuccessfulResponse{" +
                "url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
