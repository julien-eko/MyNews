package com.darcos.julie.mynews.Utils;

import com.darcos.julie.mynews.Models.TopStories;


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
}
