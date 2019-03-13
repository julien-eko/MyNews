package com.darcos.julie.mynews.Models;

import com.darcos.julie.mynews.Models.TopStories.Result;
import com.darcos.julie.mynews.R;

public class Article {

    private String title;
    private String resume;
    private String date;
    private String url;
    private String image;


    public Article(){}


    public void setTitle(String title) {
        this.title = title;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}
