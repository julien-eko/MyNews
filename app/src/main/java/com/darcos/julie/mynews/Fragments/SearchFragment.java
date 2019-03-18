package com.darcos.julie.mynews.Fragments;


import android.content.Context;
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
import com.darcos.julie.mynews.Models.Search.Search;
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
public class SearchFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view_search)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    //FOR DATA
    private Disposable disposable;
    // 2 - Declare list of users (GithubUser) & Adapter
    private List<Article> list;
    private TimesAdapter adapter;
    private String beginDate;
    private String endDate;
    private String querySearch;
    @BindView(R.id.fragment_main_swipe_container_search)
    SwipeRefreshLayout swipeRefreshLayout;

        private test mCallback;

    public interface test {
        public String beginDate ();
        public String endDate ();
        public String querySearch ();
    }


    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        this.beginDate=mCallback.beginDate();
        this.endDate= mCallback.endDate();
        this.querySearch=mCallback.querySearch();

        Toast.makeText(getActivity(), this.beginDate,
                Toast.LENGTH_LONG).show();

        this.configureRecyclerView(); // - 4 Call during UI creation
        this.executeHttpRequestWithRetrofit(); // 5 - Execute stream after UI creation
        // 4 - Configure the SwipeRefreshLayout
        this.configureSwipeRefreshLayout();
        this.configureOnClickRecyclerView();


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 4 - Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }

    // --------------
    // ACTIONS
    // --------------


    // --------------
    // FRAGMENT SUPPORT
    // --------------

    // 3 - Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallback = (test) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // 2 - Configure the SwipeRefreshLayout
    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_article_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        Intent webView = new Intent(SearchFragment.this.getContext(), WebViewActivity.class);
                        webView.putExtra("url", adapter.getUrl(position));
                        webView.putExtra("title", adapter.getResume(position));
                        startActivity(webView);

                        Log.e("TAG", "Position : " + position);
                    }
                });
    }
    // -----------------
    // CONFIGURATION
    // -----------------

    // 3 - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // 3.1 - Reset list
        this.list = new ArrayList<>();
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

    private void executeHttpRequestWithRetrofit() {

        this.disposable = TimesStreams.streamSearch(querySearch, beginDate, endDate).subscribeWith(new DisposableObserver<Search>() {
            @Override
            public void onNext(Search articles) {
                // 6 - Update RecyclerView after getting results from Github API
                updateUI(articles);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(Search articles) {
        swipeRefreshLayout.setRefreshing(false);
        this.list.clear();
        ArticleList.listSearchArticle(this.list, articles);
        adapter.notifyDataSetChanged();
    }

}

