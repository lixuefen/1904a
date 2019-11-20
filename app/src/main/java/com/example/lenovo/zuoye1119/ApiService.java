package com.example.lenovo.zuoye1119;



import com.example.lenovo.zuoye1119.beans.ArtBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface ApiService {
    public static String ARTURL="https://www.wanandroid.com/";
    @GET("project/list/{page}/json")
    Observable<ArtBean> getArt(@Path("page") int page, @Query("cid") int cid);
}
