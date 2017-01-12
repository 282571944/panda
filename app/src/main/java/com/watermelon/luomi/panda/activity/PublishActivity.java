package com.watermelon.luomi.panda.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.databinding.ActivityPublishBinding;
import com.watermelon.luomi.panda.net.NewsDetailResponse;
import com.watermelon.luomi.panda.utils.CommonUtils;

public class PublishActivity extends BaseActivity {
    NewsDetailResponse newsDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPublishBinding viewDataBinding = DataBindingUtil.setContentView(PublishActivity.this, R.layout.activity_publish);
        CommonUtils.setToolbar(this,R.string.tab5_name);
        newsDetail = new NewsDetailResponse();
        newsDetail.setCan_comment(true);
        viewDataBinding.setNewsDetail(newsDetail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            PublishActivity.this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
