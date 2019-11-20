package com.example.lenovo.zuoye1119.fragments;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.lenovo.zuoye1119.R;
import com.example.lenovo.zuoye1119.adapters.MyRvAdapter;
import com.example.lenovo.zuoye1119.beans.DatasBean;
import com.example.lenovo.zuoye1119.presenter.DatasBeanPresenter;
import com.example.lenovo.zuoye1119.presenter.NetPresenter;
import com.example.lenovo.zuoye1119.view.NetView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainFragment extends Fragment implements NetView {
    private View view;
    private RecyclerView mMyRec;
    private SmartRefreshLayout mMySmrec;
    private List<DatasBean> datas;
    private MyRvAdapter myRvAdapter;
    private int page = 1;
    private NetPresenter netPresenter;
    private DatasBeanPresenter datasBeanPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.main_fragment_layout, null);

        initView(view);
        initRec();
        netPresenter = new NetPresenter(this);
        datasBeanPresenter = new DatasBeanPresenter(this);
        netPresenter.loadArt(page);
        return view;
    }

    private void initRec() {
        mMyRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mMyRec.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        datas = new ArrayList<>();
        myRvAdapter = new MyRvAdapter(datas, getContext());
        mMyRec.setAdapter(myRvAdapter);
        myRvAdapter.setListener(new MyRvAdapter.OnItemClickLongListener() {
            @Override
            public void OnItemLong(View view, int position) {
                DatasBean datasBeans = datas.get(position);
                datasBeanPresenter.insertData(datasBeans);
                Toast.makeText(getContext(),"插入成功",Toast.LENGTH_SHORT).show();
            }
        });
        mMySmrec.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                netPresenter.loadArt(page);
            }
        });
        mMySmrec.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                netPresenter.loadArt(page);
            }
        });
    }

    private void initView(View view) {
        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMySmrec = (SmartRefreshLayout) view.findViewById(R.id.mySmrec);
    }

    @Override
    public void setData(List<DatasBean> datasBeans) {
        datas.addAll(datasBeans);
        myRvAdapter.notifyDataSetChanged();
        mMySmrec.finishRefresh();
        mMySmrec.finishLoadmore();
    }

    @Override
    public void showTo(String str) {
        Log.i("tag",str);
    }
}
