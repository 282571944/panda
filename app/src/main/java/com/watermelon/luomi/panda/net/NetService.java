package com.watermelon.luomi.panda.net;

import com.watermelon.luomi.panda.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by luomi on 2016-09-09.
 */
public interface NetService {

    @Headers("Content-type:application/wocai")
    @POST("myphp/panda.php")
    Call<ResponseContent> SayHello(@Body RequestContent requestContent);

    @GET("api/4/version/android/{version}")
    Call<VersionResponse> checkUpdate(@Path("version")String version);

    @GET("api/4/news/latest")
    Observable<NewsResponse> getLatestNews();

    @GET("api/4/news/before/{date}")
    Observable<NewsResponse> getPastNews(@Path("date")String newsId);

    @GET("api/4/news/{newsId}")
    Observable<NewsDetailResponse> getNewsDetail(@Path("newsId")String newsId);

    @GET("api/4/story/{newsId}/short-comments")
    Observable<CommentResponse> getShortComments(@Path("newsId")String newsId);
}
