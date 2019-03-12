package com.darcos.julie.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import com.darcos.julie.mynews.Models.TopStories.Result;
import com.darcos.julie.mynews.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class TimesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_main_item_title) TextView textView;

    @BindView(R.id.fragment_main_item_website) TextView texViewWebsite;
    @BindView(R.id.fragment_main_item_image) ImageView imageView;
    @BindView(R.id.fragment_main_item_date) TextView dateView;
    public TimesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithTimesUser(Result article, RequestManager glide) {

        if (article.getSubsection().equals("")) {
            this.textView.setText(article.getSection());
        } else {
            this.textView.setText(article.getSection() + " > " +article.getSubsection());
        }

        this.texViewWebsite.setText(article.getAbstract());
        //creer une fonction pour afficher la bonne date
        //this.dateView.setText(article.getPublishedDate());



        //if no image displays a default image else app crash
        if (article.getMultimedia().size() !=0) {
            glide.load(article.getMultimedia().get(0).getUrl()).into(imageView);
        } else {
            glide.load(R.drawable.news).into(imageView);
        }
    }
}
