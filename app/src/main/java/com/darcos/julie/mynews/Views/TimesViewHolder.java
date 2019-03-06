package com.darcos.julie.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.darcos.julie.mynews.Models.Result;
import com.darcos.julie.mynews.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class TimesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_main_item_title) TextView textView;

    @BindView(R.id.fragment_main_item_website) TextView texViewWebsite;
    @BindView(R.id.fragment_main_item_image) ImageView imageView;

    public TimesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithTimesUser(Result article, RequestManager glide){
        this.textView.setText(article.getTitle());
        this.texViewWebsite.setText(article.getUrl());

        glide.load(article.getMultimedia().get(0).getUrl()).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
}
