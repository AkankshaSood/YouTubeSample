package com.example.hp.youtubeapi;

/**
 * Created by hp on 6/20/2018.
 */

public class Video {

    String name;
    String id;
    String imgURL;

    public Video(String id,String name)
    {
        this.id = id;
        this.name = name;
        //imgURL  = "https://www.google.com/images/srpr/logo11w.png";
        imgURL = "https://img.youtube.com/vi/"+id+"/0.jpg";
        //this.thumbnail = thumbnail;
    }



    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }
}
