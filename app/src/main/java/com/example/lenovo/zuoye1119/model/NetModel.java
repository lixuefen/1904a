package com.example.lenovo.zuoye1119.model;



import com.example.lenovo.zuoye1119.ApiService;
import com.example.lenovo.zuoye1119.ArtsCallBack;
import com.example.lenovo.zuoye1119.beans.ArtBean;
import com.example.lenovo.zuoye1119.beans.DatasBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class NetModel {
    public void loadArt(int page, final ArtsCallBack artsCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ARTURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Observable<ArtBean> observable = service.getArt(page, 294);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ArtBean artBean) {
                        //请求成功后，把数据回传给p
                        if (artBean != null && artBean.getData() != null &&
                                artBean.getData().getDatas() != null) {
                            List<DatasBean> datasBeans = artBean.getData().getDatas();
                            artsCallBack.OnSuccess(datasBeans);
                        }else {
                           artsCallBack.showTo("没有请求到数据");
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        artsCallBack.showTo(e.getMessage());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
