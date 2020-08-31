package com.example.swproject;

import java.io.Serializable;

public class ItemObject implements Serializable {
    private String title;
    private String img_url;
    private String detail_link;
    private String release;
    private String director;
    private String link;


    public ItemObject(String title, String url, String link, String release, String director){
        this.title = title;
        this.img_url = url;
        this.detail_link = link;
        this.release = release;
        this.director = director;
        this.link = link;
    }


    public String getTitle() {
        return title;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getDetail_link() {
        return detail_link;
    }

    public String getRelease() {
        return release;
    }

    public String getDirector() {
        return director;
    }

    public String getlink() {
        return link;
    }
}
