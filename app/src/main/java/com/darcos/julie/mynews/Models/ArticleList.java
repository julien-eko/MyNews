package com.darcos.julie.mynews.Models;

import com.darcos.julie.mynews.Models.MostPopular.MostPopular;
import com.darcos.julie.mynews.Models.MostPopular.ResultMostPopular;
import com.darcos.julie.mynews.Models.TopStories.Result;
import com.darcos.julie.mynews.Models.TopStories.TopStories;

import java.util.List;

public class ArticleList {


    public static void listTopStories (List<Article> listArticle, TopStories topStories){
        for(Result result:topStories.getResults()){
            Article article = new Article();


            //if no image displays a default image else app crash
            if (result.getMultimedia().size() !=0) {
                article.setImage(result.getMultimedia().get(0).getUrl());
            } else {
               article.setImage(null);
            }


            article.setUrl(result.getUrl());

            article.setResume(result.getTitle());

            if (result.getSubsection().equals("")) {
                article.setTitle(result.getSection());
            } else {
                article.setTitle(result.getSection() + " > " + result.getSubsection());
            }

            article.setDate(date(result.getCreatedDate()));

          listArticle.add(article);
        }

    }


    public static void listMostPopular (List<Article> listArticle, MostPopular mostPopular){
        for(ResultMostPopular result : mostPopular.getResults()){

            Article article = new Article();


            //if no image displays a default image else app crash
            if (result.getMedia().get(0).getMediaMetadata().size() !=0) {
                article.setImage(result.getMedia().get(0).getMediaMetadata().get(0).getUrl());
            } else {
                article.setImage(null);
            }



            article.setUrl(result.getUrl());

            article.setResume(result.getTitle());

            article.setTitle(result.getSection());


            listArticle.add(article);
        }

    }


    public static String date (String date){
        String year=date.substring(2,4);
        String month=date.substring(5,7);
        String day=date.substring(8,10);

        date = month + "/" + day + "/" + year;
        return date;
    }
}
