package com.watermelon.luomi.panda.activity.fragments;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watermelon.luomi.panda.BR;
import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.activity.fragments.NewsFragment.OnListFragmentInteractionListener;
import com.watermelon.luomi.panda.net.APIService;
import com.watermelon.luomi.panda.net.NewsResponse;
import com.watermelon.luomi.panda.net.NewsResponse.StoriesBean;
import com.watermelon.luomi.panda.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import space.sye.z.library.RefreshRecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link NewsResponse} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NewsItemRecyclerViewAdapter extends RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder> {

    private final List<StoriesBean> storiesBeens = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;
    Activity context;
    RefreshRecyclerView recyclerView;

    public NewsItemRecyclerViewAdapter(Activity context, RefreshRecyclerView recyclerView, OnListFragmentInteractionListener listener) {
        this.context = context;
        this.recyclerView = recyclerView;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_news,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        viewHolder.setViewDataBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final StoriesBean item = storiesBeens.get(position);
        holder.storiesBean = item;
        holder.getViewDataBinding().setVariable(BR.storiesBean, item);
        holder.getViewDataBinding().executePendingBindings();

        holder.mView.setOnClickListener(v -> mListener.onListFragmentInteraction(holder.storiesBean));
    }

    @Override
    public int getItemCount() {
        return storiesBeens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private ViewDataBinding viewDataBinding;
        private StoriesBean storiesBean;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }

        public void setViewDataBinding(ViewDataBinding viewDataBinding) {
            this.viewDataBinding = viewDataBinding;
        }
    }

    /**
     * refresh data
     */
    public void refreshData() {
        APIService.getInstance().getLatestNews(o -> {
            NewsResponse newsResponse = (NewsResponse) o;
            List<StoriesBean> refreshStoriesBeens = new ArrayList<>();
            for (StoriesBean refreshStoriesBeen : newsResponse.getStories()) {
                refreshStoriesBeen.setDate(newsResponse.getDate());
                refreshStoriesBeens.add(refreshStoriesBeen);
            }
            if (storiesBeens.isEmpty()) {
                this.storiesBeens.addAll(0, refreshStoriesBeens);
                NewsItemRecyclerViewAdapter.this.notifyDataSetChanged();
            } else {
                if (refreshStoriesBeens.isEmpty()) {
                    CommonUtils.toast(context, context.getResources().getString(R.string.newest));
                } else {
                    if (storiesBeens.get(0).getId() == refreshStoriesBeens.get(0).getId()) {
                        CommonUtils.toast(context, context.getResources().getString(R.string.newest));
                    } else {
                        this.storiesBeens.addAll(0, refreshStoriesBeens);
                        NewsItemRecyclerViewAdapter.this.notifyDataSetChanged();
                    }
                }
            }
            recyclerView.onRefreshCompleted();
        }, o -> {
            CommonUtils.toast(context, context.getResources().getString(R.string.check_network));
            recyclerView.onRefreshCompleted();
        });
    }

    /**
     * load more
     */
    public void loadMore(){
        APIService.getInstance().getPastNews(storiesBeens.get(storiesBeens.size()-1).getDate()+"",o -> {
            NewsResponse newsResponse = (NewsResponse) o;
            List<StoriesBean> refreshStoriesBeens = new ArrayList<>();
            for (StoriesBean refreshStoriesBeen : newsResponse.getStories()) {
                refreshStoriesBeen.setDate(newsResponse.getDate());
                refreshStoriesBeens.add(refreshStoriesBeen);
            }
            if (storiesBeens.isEmpty()) {
                this.storiesBeens.addAll(0, refreshStoriesBeens);
                NewsItemRecyclerViewAdapter.this.notifyDataSetChanged();
            } else {
                if (refreshStoriesBeens.isEmpty()) {
                    CommonUtils.toast(context, context.getResources().getString(R.string.no_more));
                } else {
                    if (storiesBeens.get(storiesBeens.size()-1).getId() == refreshStoriesBeens.get(refreshStoriesBeens.size()-1).getId()) {
                        CommonUtils.toast(context, context.getResources().getString(R.string.no_more));
                    } else {
                        this.storiesBeens.addAll(refreshStoriesBeens);
                        NewsItemRecyclerViewAdapter.this.notifyDataSetChanged();
                    }
                }
            }
            recyclerView.onRefreshCompleted();
        }, o -> {
            CommonUtils.toast(context, context.getResources().getString(R.string.check_network));
            recyclerView.onRefreshCompleted();
        });
    }
}
