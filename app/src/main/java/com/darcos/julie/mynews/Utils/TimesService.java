package com.darcos.julie.mynews.Utils;

import com.darcos.julie.mynews.Models.MostPopular.MostPopular;
import com.darcos.julie.mynews.Models.Search.Search;
import com.darcos.julie.mynews.Models.TopStories.TopStories;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TimesService {


        @GET("svc/topstories/v2/{section}.json?api-key=JyNdblyOMqqNNknG0iu5INNajfu1wAyj")
        Observable<TopStories> getTopStories(@Path("section") String section);


        @GET("svc/mostpopular/v2/viewed/1.json?api-key=JyNdblyOMqqNNknG0iu5INNajfu1wAyj")
        Observable<MostPopular> getMostPopular();

        @GET("svc/search/v2/articlesearch.json?api-key=JyNdblyOMqqNNknG0iu5INNajfu1wAyj")
        Observable<Search> getSearch(@Query("q") String queryTerm, @Query("begin_date") String beginDate, @Query("end_date") String endDate);



    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
