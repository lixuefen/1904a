package com.example.lenovo.zuoye1119.model;



import com.example.lenovo.zuoye1119.BaseApp;
import com.example.lenovo.zuoye1119.DatasCallBack;
import com.example.lenovo.zuoye1119.beans.DatasBean;

import java.util.List;



public class DatasBeanModel {
    public void insertData(DatasBean datasBeans, DatasCallBack datasCallBack) {
        //添加到数据库
        try {
            BaseApp.getInstance().getDaoSession().getDatasBeanDao().insertOrReplace(datasBeans);
            datasCallBack.OnFaile("收藏成功");
        } catch (Exception e) {
            e.printStackTrace();
            datasCallBack.OnFaile("收藏失败");
        }
    }

    public void loadAll(DatasCallBack datasCallBack) {

        try {
            List<DatasBean> datasBeans = BaseApp.getInstance().getDaoSession().getDatasBeanDao().loadAll();
            if(datasBeans != null && datasBeans.size()>0){//查出数据，展示
                datasCallBack.OnSuccess(datasBeans);
            }else { //没有数据，提示
                datasCallBack.OnFaile("没有数据");
            }
        } catch (Exception e) {//查询出错的提示
            e.printStackTrace();
            datasCallBack.OnFaile("查询异常");
        }
    }

    public void deleteData(DatasBean datasBeans,DatasCallBack datasCallBack) {
        try {
            BaseApp.getInstance().getDaoSession().getDatasBeanDao().delete(datasBeans);
            datasCallBack.OnFaile("删除成功");
            loadAll(datasCallBack);//重新查询数据库
        } catch (Exception e) {
            e.printStackTrace();
            datasCallBack.OnFaile("删除异常");
        }
    }

    public void upData(DatasBean datasBean1,DatasCallBack datasCallBack) {
        try {
            BaseApp.getInstance().getDaoSession().getDatasBeanDao().update(datasBean1);
            datasCallBack.OnFaile("修改成功");
            loadAll(datasCallBack);//重新查询数据库
        } catch (Exception e) {
            e.printStackTrace();
            datasCallBack.OnFaile("修改异常");
        }
    }
}
