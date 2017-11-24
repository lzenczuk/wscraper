package com.github.lzenczuk.wscraper;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by dev on 24/11/17.
 */
public class AHCDownloader implements Downloader {

    public static final String MAIN_URL = "https://www.wykop.pl";

    private final HttpClient client;
    private final String mainUrl;

    public AHCDownloader() {
        mainUrl = MAIN_URL;
        client = HttpClients.createDefault();


    }

    /**
     * Unit tests dedicated constructor
     * @param alternativeMainUrl
     */
    AHCDownloader(String alternativeMainUrl) {
        this.mainUrl = alternativeMainUrl;
        client = HttpClients.createDefault();
    }

    @Override
    public DownloadResult downloadMainPage(long id) {
        String url = String.format(mainUrl + "/link/%d/", id);
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse httpResponse = client.execute(httpGet);

            switch (httpResponse.getStatusLine().getStatusCode()){
                case 200:
                    StringWriter writer = new StringWriter();

                    String encoding = "UTF-8";
                    if(httpResponse.getEntity().getContentEncoding()!=null){
                        encoding = httpResponse.getEntity().getContentEncoding().getValue();
                    }

                    IOUtils.copy(httpResponse.getEntity().getContent(), writer, encoding);
                    return new SuccessfulResponse(url, writer.toString());
                case 404:
                    return new NotFoundResponse(url);
                default:
                    return new ServerErrorResult(url, httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase());
            }

        } catch (IOException e) {
            return new ClientErrorResponse(e.getMessage());
        }
    }

    @Override
    public DownloadResult downloadUpVotes(long id) {
        return null;
    }

    @Override
    public DownloadResult downloadDownVotes(long id) {
        return null;
    }

    @Override
    public DownloadResult downloadCommentsPage(long id, int page) {
        return null;
    }
}
