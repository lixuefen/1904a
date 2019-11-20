package com.example.lenovo.zuoye1119.presenter;



import com.example.lenovo.zuoye1119.ArtsCallBack;
import com.example.lenovo.zuoye1119.beans.DatasBean;
import com.example.lenovo.zuoye1119.model.NetModel;
import com.example.lenovo.zuoye1119.view.NetView;

import java.util.List;



public class NetPresenter implements ArtsCallBack {
    private NetView netView;
    private NetModel netModel;

    public NetPresenter(NetView netView) {
        this.netView = netView;
        netModel = new NetModel();
    }
    public void loadArt(int page) {
        netModel.loadArt(page,this);
    }

    @Override
    public void OnSuccess(List<DatasBean> datasBeans) {
        netView.setData(datasBeans);
    }

    @Override
    public void showTo(String str) {
        netView.showTo(str);
    }
}
