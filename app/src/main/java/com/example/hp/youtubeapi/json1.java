package com.example.hp.youtubeapi;

import java.util.ArrayList;

/**
 * Created by hp on 6/23/2018.
 */

public class json1 {
    ArrayList<json2> items;

    public json1(ArrayList<json2> items)
    {
        this.items = items;
    }

    public ArrayList<json2> getItems() {
        return items;
    }
}
