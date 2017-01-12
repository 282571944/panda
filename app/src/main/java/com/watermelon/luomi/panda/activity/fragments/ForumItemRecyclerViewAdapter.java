package com.watermelon.luomi.panda.activity.fragments;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watermelon.luomi.panda.BR;
import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.activity.fragments.ForumFragment.OnListFragmentInteractionListener;
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
public class ForumItemRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<StoriesBean> storiesBeens = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;
    Activity context;
    RefreshRecyclerView recyclerView;

    enum ViewType {
        SINGLE, MULTI
    }

    public ForumItemRecyclerViewAdapter(Activity activity, RefreshRecyclerView recyclerView, OnListFragmentInteractionListener listener) {
        this.recyclerView = recyclerView;
        this.context = activity;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ViewType.SINGLE.ordinal() == viewType) {
            ViewDataBinding bindingSingle = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_forum, parent, false);
            SingleViewHolder singleViewHolder = new SingleViewHolder(bindingSingle.getRoot());
            singleViewHolder.setViewDataBinding(bindingSingle);
            return singleViewHolder;
        } else if (ViewType.MULTI.ordinal() == viewType) {
            ViewDataBinding bindingMulti = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_forum, parent, false);
            MultiViewHolder multiViewHolder = new MultiViewHolder(bindingMulti.getRoot());
            multiViewHolder.setViewDataBinding(bindingMulti);
            return multiViewHolder;
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final StoriesBean item = storiesBeens.get(position);
        if (holder instanceof SingleViewHolder) {
            SingleViewHolder singleViewHolder = (SingleViewHolder) holder;
            singleViewHolder.storiesBean = item;
            singleViewHolder.getViewDataBinding().setVariable(BR.forum, item);
            singleViewHolder.getViewDataBinding().setVariable(BR.display, position == 5 ? true : false);
            singleViewHolder.getViewDataBinding().executePendingBindings();

            singleViewHolder.mView.setOnClickListener(v -> mListener.onListFragmentInteraction(singleViewHolder.storiesBean, "1"));
        } else if (holder instanceof MultiViewHolder) {
            MultiViewHolder multiViewHolder = (MultiViewHolder) holder;
            multiViewHolder.storiesBean = item;
            multiViewHolder.getViewDataBinding().setVariable(BR.forum, item);
            multiViewHolder.getViewDataBinding().setVariable(BR.display, position == 5 ? true : false);
            multiViewHolder.getViewDataBinding().executePendingBindings();

            multiViewHolder.mView.setOnClickListener(v -> mListener.onListFragmentInteraction(multiViewHolder.storiesBean, "1"));
        } else {
            //TO DO MORE TYPE
        }
    }

    @Override
    public int getItemCount() {
        return storiesBeens.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ViewType.SINGLE.ordinal() : ViewType.MULTI.ordinal();
    }

    public class MultiViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private ViewDataBinding viewDataBinding;
        private StoriesBean storiesBean;

        public MultiViewHolder(View view) {
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


    public class SingleViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private ViewDataBinding viewDataBinding;
        private StoriesBean storiesBean;

        public SingleViewHolder(View view) {
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
        APIService.getLatestNews(o -> {
            NewsResponse newsResponse = (NewsResponse) o;
            List<StoriesBean> refreshStoriesBeens = new ArrayList<>();
            for (StoriesBean refreshStoriesBeen : newsResponse.getStories()) {
                refreshStoriesBeen.setDate(newsResponse.getDate());
                refreshStoriesBeens.add(refreshStoriesBeen);
            }
            if (storiesBeens.isEmpty()) {
                this.storiesBeens.addAll(0, refreshStoriesBeens);
                ForumItemRecyclerViewAdapter.this.notifyDataSetChanged();
            } else {
                if (refreshStoriesBeens.isEmpty()) {
                    CommonUtils.toast(context, context.getResources().getString(R.string.newest));
                } else {
                    if (storiesBeens.get(0).getId() == refreshStoriesBeens.get(0).getId()) {
                        CommonUtils.toast(context, context.getResources().getString(R.string.newest));
                    } else {
                        this.storiesBeens.addAll(0, refreshStoriesBeens);
                        ForumItemRecyclerViewAdapter.this.notifyDataSetChanged();
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
    public void loadMore() {
        APIService.getPastNews(storiesBeens.get(storiesBeens.size() - 1).getDate() + "", o -> {
            NewsResponse newsResponse = (NewsResponse) o;
            List<StoriesBean> refreshStoriesBeens = new ArrayList<>();
            for (StoriesBean refreshStoriesBeen : newsResponse.getStories()) {
                refreshStoriesBeen.setDate(newsResponse.getDate());
                refreshStoriesBeens.add(refreshStoriesBeen);
            }
            if (storiesBeens.isEmpty()) {
                this.storiesBeens.addAll(0, refreshStoriesBeens);
                ForumItemRecyclerViewAdapter.this.notifyDataSetChanged();
            } else {
                if (refreshStoriesBeens.isEmpty()) {
                    CommonUtils.toast(context, context.getResources().getString(R.string.no_more));
                } else {
                    if (storiesBeens.get(storiesBeens.size() - 1).getId() == refreshStoriesBeens.get(refreshStoriesBeens.size() - 1).getId()) {
                        CommonUtils.toast(context, context.getResources().getString(R.string.no_more));
                    } else {
                        this.storiesBeens.addAll(refreshStoriesBeens);
                        ForumItemRecyclerViewAdapter.this.notifyDataSetChanged();
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
