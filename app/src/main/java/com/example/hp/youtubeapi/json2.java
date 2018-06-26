package com.example.hp.youtubeapi;

import java.util.ArrayList;

/**
 * Created by hp on 6/23/2018.
 */

public class json2 {

    json3 statistics;
    json4 snippet;
    json5 contentDetails;

    public json2(json3 statistics,json4 snippet, json5 contentDetails)
    {
        this.statistics = statistics;
        this.snippet = snippet;
        this.contentDetails = contentDetails;
    }

    public json3 getStatistics() {
        return statistics;
    }

    public json4 getSnippet() {
        return snippet;
    }

    public json5 getContentDetails() {
        return contentDetails;
    }
}
