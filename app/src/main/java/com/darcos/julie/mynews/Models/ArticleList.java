package com.darcos.julie.mynews.Models;

import android.widget.Toast;

import com.darcos.julie.mynews.Models.MostPopular.MostPopular;
import com.darcos.julie.mynews.Models.MostPopular.ResultMostPopular;
import com.darcos.julie.mynews.Models.Search.Doc;
import com.darcos.julie.mynews.Models.Search.Search;
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

    public static  void listSearchArticle (List<Article> listArticle, Search search){
        for(Doc result : search.getResponse().getDocs()){
            Article article = new Article();

            //if no image displays a default image else app crash
            if (result.getMultimedia().size() !=0) {

                article.setImage("https://static01.nyt.com/" + result.getMultimedia().get(0).getUrl());
            } else {
                article.setImage(null);
            }

            article.setUrl(result.getWebUrl());

            article.setResume(result.getHeadline().getMain());

            article.setTitle(result.getSectionName());

            //erreur
            //if (result.getSubsectoinName().equals("")) {
            //    article.setTitle(result.getSectionName());
            //} else {
            //    article.setTitle(result.getSectionName() + " > " + result.getSubsectoinName());
            //}


            article.setDate(date(result.getPubDate()));

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

            article.setDate(date(result.getPublishedDate()));

            listArticle.add(article);
        }

    }


    public static String date (String date){
        String year=date.substring(2,4);
        String month=date.substring(5,7);
        String day=date.substring(8,10);

        date = day + "/" + month + "/" + year;
        return date;
    }
}
