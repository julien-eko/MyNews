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

    public void articleTopStories(Result topStories) {
        if (topStories.getSubsection() != null) {
            this.title = topStories.getSection() + " > " + topStories.getSubsection();
        } else {
            this.title = topStories.getSection();
        }

        this.resume=topStories.getAbstract();

        this.url=topStories.getUrl();

        this.image=topStories.getMultimedia().get(0).getUrl();

        //this.date=

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
