package com.github.lzenczuk.wscraper;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by dev on 24/11/17.
 */
public class AHCDownloaderTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void fetchExistingMainPage(){
        stubFor(get(urlEqualTo("/link/245/")).willReturn(aResponse().withStatus(200).withBody("Page 245")));

        AHCDownloader downloader = new AHCDownloader("http://localhost:8089");

        DownloadResult downloadResult = downloader.downloadMainPage(245);

        assertThat(downloadResult, instanceOf(SuccessfulResponse.class));

        SuccessfulResponse successfulResponse = (SuccessfulResponse) downloadResult;

        assertEquals(successfulResponse.getUrl(), "http://localhost:8089/link/245/");
        assertEquals(successfulResponse.getContent(), "Page 245");

        verify(getRequestedFor(urlEqualTo("/link/245/")));
    }

    @Test
    public void fetchNotExistingMainPage(){
        stubFor(get(urlEqualTo("/link/246/")).willReturn(aResponse().withStatus(404).withStatusMessage("Page not found")));

        AHCDownloader downloader = new AHCDownloader("http://localhost:8089");

        DownloadResult downloadResult = downloader.downloadMainPage(246);

        assertThat(downloadResult, instanceOf(NotFoundResponse.class));

        NotFoundResponse response = (NotFoundResponse) downloadResult;

        assertEquals(response.getUrl(), "http://localhost:8089/link/246/");

        verify(getRequestedFor(urlEqualTo("/link/246/")));
    }

    @Test
    public void fetchServerErrorMainPage(){
        stubFor(get(urlEqualTo("/link/247/")).willReturn(aResponse().withStatus(500).withStatusMessage("Internal error")));

        AHCDownloader downloader = new AHCDownloader("http://localhost:8089");

        DownloadResult downloadResult = downloader.downloadMainPage(247);

        assertThat(downloadResult, instanceOf(ServerErrorResult.class));

        ServerErrorResult response = (ServerErrorResult) downloadResult;

        assertEquals(response.getUrl(), "http://localhost:8089/link/247/");
        assertEquals(response.getCode(), 500);
        assertEquals(response.getErrorMessage(), "Internal error");

        verify(getRequestedFor(urlEqualTo("/link/247/")));
    }
}
