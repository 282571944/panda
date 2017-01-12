package com.watermelon.luomi.panda.net;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.internal.util.ActionSubscriber;
import rx.schedulers.Schedulers;

/**
 * API
 * Created by luomi on 2016-09-13.
 */
public class APIService {
    static Retrofit retrofit;
    static String base_url;

    public APIService() {
        retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    static class APIServiceHolder {
        public static APIService apiService = new APIService();
    }

    public static APIService getInstance() {
        return APIServiceHolder.apiService;
    }

    /**
     * init
     *
     * @param url
     * @return
     */
    public static APIService initInstance(String url) {
        base_url = url;
        return APIServiceHolder.apiService;
    }

    /**
     * checkUpdate
     *
     * @param activity
     * @param version
     * @return
     */
    public static Call<VersionResponse> checkUpdate(Activity activity, String version) {
        NetService servicce = retrofit.create(NetService.class);
        return servicce.checkUpdate(version);
    }

    /**
     * get latest news
     *
     * @return
     */
    public static void getLatestNews(Action1 action1,Action1 action2) {
        NetService servicce = retrofit.create(NetService.class);
        servicce.getLatestNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1,action2);
    }

    /**
     * get past news
     *
     * @return
     */
    public static void getPastNews(String beforeDate,Action1 action1,Action1 action2) {
        NetService servicce = retrofit.create(NetService.class);
        servicce.getPastNews(beforeDate).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1,action2);
    }

    /**
     * get latest news
     *
     * @return
     */
    public static void getNewsDetail(String newsId,Action1 action1,Action1 action2) {
        NetService servicce = retrofit.create(NetService.class);
        servicce.getNewsDetail(newsId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1,action2);
    }

    /**
     * get latest news
     *
     * @return
     */
    public static void getShortComments(String newsId,Action1 action1,Action1 action2) {
        NetService servicce = retrofit.create(NetService.class);
        servicce.getShortComments(newsId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1,action2);
    }
}
