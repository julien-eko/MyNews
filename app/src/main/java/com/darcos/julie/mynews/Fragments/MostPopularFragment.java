package com.darcos.julie.mynews.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.darcos.julie.mynews.Activities.WebViewActivity;
import com.darcos.julie.mynews.Models.Article;
import com.darcos.julie.mynews.Models.ArticleList;
import com.darcos.julie.mynews.Models.MostPopular.MostPopular;
import com.darcos.julie.mynews.Models.TopStories.TopStories;
import com.darcos.julie.mynews.R;
import com.darcos.julie.mynews.Utils.ItemClickSupport;
import com.darcos.julie.mynews.Utils.TimesStreams;
import com.darcos.julie.mynews.Views.TimesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView; // 1 - Declare RecyclerView

    //FOR DATA
    private Disposable disposable;
    private List<Article> list;
    private TimesAdapter adapter;
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    public MostPopularFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_article, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView(); // - 4 Call during UI creation
        this.executeHttpRequestWithRetrofit(); // 5 - Execute stream after UI creation
        // 4 - Configure the SwipeRefreshLayout
        this.configureSwipeRefreshLayout();
        this.configureOnClickRecyclerView();


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }
    // 2 - Configure the SwipeRefreshLayout
    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_article_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        Intent webView = new Intent(MostPopularFragment.this.getContext(), WebViewActivity.class);
                        webView.putExtra("url",adapter.getUrl(position));
                        webView.putExtra("title",adapter.getResume(position));
                        startActivity(webView);

                        Log.e("TAG", "Position : "+position);
                    }
                });
    }
    // -----------------
    // CONFIGURATION
    // -----------------

    // 3 - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.list =new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new TimesAdapter(this.list, Glide.with(this));
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private void executeHttpRequestWithRetrofit(){
        this.disposable = TimesStreams.streamMostPopular().subscribeWith(new DisposableObserver<MostPopular>() {
            @Override
            public void onNext(MostPopular articles) {

                updateUI(articles);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(MostPopular articles){

        swipeRefreshLayout.setRefreshing(false);
        this.list.clear();
        ArticleList.listMostPopular(this.list,articles);
        adapter.notifyDataSetChanged();
    }

}
