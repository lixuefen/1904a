package com.example.lenovo.zuoye1119.presenter;



import com.example.lenovo.zuoye1119.DatasCallBack;
import com.example.lenovo.zuoye1119.beans.DatasBean;
import com.example.lenovo.zuoye1119.model.DatasBeanModel;
import com.example.lenovo.zuoye1119.view.NetView;

import java.util.List;



public class DatasBeanPresenter implements DatasCallBack {
    private NetView netView;
    private DatasBeanModel datasBeanModel;

    public DatasBeanPresenter(NetView netView) {
        this.netView = netView;
        datasBeanModel = new DatasBeanModel();
    }

//添加
    public void insertData(DatasBean datasBeans) {
        datasBeanModel.insertData(datasBeans,this);
    }
    //查询
    public void loadData() {
       datasBeanModel.loadAll(this);
    }
    //删除
    public void deleteData(DatasBean datasBeans) {
        datasBeanModel.deleteData(datasBeans,this);
    }
    //修改
    public void upData(DatasBean datasBean1) {
        datasBeanModel.upData(datasBean1,this);
    }
    @Override
    public void OnSuccess(List<DatasBean> datasBeans) {
        netView.setData(datasBeans);
    }

    @Override
    public void OnFaile(String str) {
        netView.showTo(str);
    }



}
