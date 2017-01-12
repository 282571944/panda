package com.watermelon.luomi.panda.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.net.APIService;
import com.watermelon.luomi.panda.net.CommentResponse;
import com.watermelon.luomi.panda.net.CommentResponse.CommentsBean;
import com.watermelon.luomi.panda.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.listener.OnBothRefreshListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;

public class CommentsActivity extends BaseActivity {
    RefreshRecyclerView recyclerView;
    CommentsItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    List<CommentsBean> commentsBeens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        CommonUtils.setToolbar(this,R.string.tab6_name);
        recyclerView = (RefreshRecyclerView) findViewById(R.id.list);
        myItemRecyclerViewAdapter = new CommentsItemRecyclerViewAdapter(commentsBeens,o -> {
        });
        RecyclerViewManager.with(myItemRecyclerViewAdapter, new LinearLayoutManager(CommentsActivity.this)).setMode(RecyclerMode.BOTH).setOnBothRefreshListener(new OnBothRefreshListener() {
            @Override
            public void onPullDown() {
                APIService.getInstance().getShortComments(getIntent().getStringExtra("newsId"),o -> {
                    recyclerView.onRefreshCompleted();
                    myItemRecyclerViewAdapter.addComments(((CommentResponse)o).getComments());
                }, o -> {
                    recyclerView.onRefreshCompleted();
                    CommonUtils.toast(CommentsActivity.this,getResources().getString(R.string.check_network));
                });
                recyclerView.onRefreshCompleted();
            }

            @Override
            public void onLoadMore() {
                recyclerView.onRefreshCompleted();
            }
        }).addHeaderView(LayoutInflater.from(CommentsActivity.this).inflate(R.layout.comments_header, null)).into(recyclerView, CommentsActivity.this);

        APIService.getInstance().getShortComments(getIntent().getStringExtra("newsId"),o -> {
            recyclerView.onRefreshCompleted();
            myItemRecyclerViewAdapter.addComments(((CommentResponse)o).getComments());
        }, o -> {
            recyclerView.onRefreshCompleted();
            CommonUtils.toast(CommentsActivity.this,getResources().getString(R.string.check_network));
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            CommentsActivity.this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public interface ReplyToUser {
        void reply(String id);
    }
}
