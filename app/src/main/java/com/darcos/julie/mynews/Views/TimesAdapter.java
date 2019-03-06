package com.darcos.julie.mynews.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.darcos.julie.mynews.Models.Result;
import com.darcos.julie.mynews.R;


import java.util.List;

public class TimesAdapter extends RecyclerView.Adapter<TimesViewHolder> {
    // FOR DATA
    private List<Result> list;
    private RequestManager glide;

    // CONSTRUCTOR
    public TimesAdapter(List<Result> list,RequestManager glide) {
        this.list = list;
        this.glide = glide;
    }

    @Override
    public TimesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_top_stories_item, parent, false);

        return new TimesViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(TimesViewHolder viewHolder, int position) {
        viewHolder.updateWithTimesUser(this.list.get(position),this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
