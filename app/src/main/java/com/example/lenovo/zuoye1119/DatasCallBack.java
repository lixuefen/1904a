package com.example.lenovo.zuoye1119;



import com.example.lenovo.zuoye1119.beans.DatasBean;

import java.util.List;



public interface DatasCallBack {
    void OnSuccess(List<DatasBean> datasBeans);
    void OnFaile(String str);
}
