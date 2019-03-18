package com.darcos.julie.mynews.Utils;

import com.darcos.julie.mynews.Models.MostPopular.MostPopular;
import com.darcos.julie.mynews.Models.Search.Search;
import com.darcos.julie.mynews.Models.TopStories.TopStories;


import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TimesStreams {

    public static Observable<TopStories> streamTopStories(String section){
        TimesService timesService = TimesService.retrofit.create(TimesService.class);
        return timesService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<MostPopular> streamMostPopular(){
        TimesService timesService = TimesService.retrofit.create(TimesService.class);
        return timesService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public  static Observable<Search> streamSearch(String queryTerm, String beginDate, String endDate){
        TimesService timesService = TimesService.retrofit.create(TimesService.class);
        return timesService.getSearch(queryTerm,beginDate,endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
