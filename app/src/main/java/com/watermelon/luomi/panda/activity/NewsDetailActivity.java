package com.watermelon.luomi.panda.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.databinding.ActivityNewsDetailBinding;
import com.watermelon.luomi.panda.net.APIService;
import com.watermelon.luomi.panda.net.NewsDetailResponse;
import com.watermelon.luomi.panda.utils.CommonUtils;


@SuppressWarnings("deprecation")
public class NewsDetailActivity extends BaseActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNewsDetailBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);
        CommonUtils.setToolbar(this,R.string.tab4_name);
        APIService.getInstance().getNewsDetail(getIntent().getStringExtra("newsId"), o -> {
            viewDataBinding.setNewsDetail(((NewsDetailResponse) o));
        }, o1 -> {
            CommonUtils.toast(NewsDetailActivity.this,getResources().getString(R.string.check_network));
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            NewsDetailActivity.this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onComment(View view){
        startActivity(new Intent(NewsDetailActivity.this,CommentsActivity.class));
    }
}
