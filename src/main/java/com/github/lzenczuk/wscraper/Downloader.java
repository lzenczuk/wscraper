package com.github.lzenczuk.wscraper;

/**
 * Created by dev on 24/11/17.
 */
public interface Downloader {
    DownloadResult downloadMainPage(long id);
    DownloadResult downloadUpVotes(long id);
    DownloadResult downloadDownVotes(long id);
    DownloadResult downloadCommentsPage(long id, int page);
}
