package com.overswayit.plesnisavezsrbije.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.models.News;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);

        holder.titleTextView.setText(news.title);
        holder.dateTextView.setText(news.date);
        holder.contentTextView.setText(news.content);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_heading)
        TextView titleTextView;
        @BindView(R.id.news_date)
        TextView dateTextView;
        @BindView(R.id.news_content)
        TextView contentTextView;
        @BindView(R.id.news_read_more)
        TextView readMoreTextView;
        @BindView(R.id.news_read_less)
        TextView readLessTextView;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.news_read_more})
        public void onReadMore(View view) {
            contentTextView.setMaxLines(Integer.MAX_VALUE);
            readMoreTextView.setVisibility(View.GONE);
            readLessTextView.setVisibility(View.VISIBLE);
        }

        @OnClick({R.id.news_read_less})
        public void onReadLess(View view) {
            contentTextView.setMaxLines(3);
            readLessTextView.setVisibility(View.GONE);
            readMoreTextView.setVisibility(View.VISIBLE);
        }
    }
}
