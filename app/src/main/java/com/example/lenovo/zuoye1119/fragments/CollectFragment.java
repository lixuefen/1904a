package com.example.lenovo.zuoye1119.fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.example.lenovo.zuoye1119.R;
import com.example.lenovo.zuoye1119.adapters.MyRvAdapter;
import com.example.lenovo.zuoye1119.beans.DatasBean;
import com.example.lenovo.zuoye1119.presenter.DatasBeanPresenter;
import com.example.lenovo.zuoye1119.view.NetView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CollectFragment extends Fragment implements NetView {
    private View view;
    private RecyclerView mCollectRec;
    private List<DatasBean> datas1;
    private MyRvAdapter myRvAdapter;
    private DatasBeanPresenter datasBeanPresenter;
    private int _index;
    private PopupWindow popupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.collect_fragment_layout, null);

        initView(view);
        initCollectRec();
        datasBeanPresenter = new DatasBeanPresenter(this);
        datasBeanPresenter.loadData();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false){
            datasBeanPresenter.loadData();
        }
    }

    private void initCollectRec() {
        mCollectRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mCollectRec.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        datas1 = new ArrayList<>();
        myRvAdapter = new MyRvAdapter(datas1, getContext());
        myRvAdapter.setListener(new MyRvAdapter.OnItemClickLongListener() {
            @Override
            public void OnItemLong(View view, int position) {
                _index = position;
            }
        });
        mCollectRec.setAdapter(myRvAdapter);
        registerForContextMenu(mCollectRec);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,1,"删除");
        menu.add(2,2,2,"修改");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                DatasBean datasBeans = datas1.get(_index);
                datasBeanPresenter.deleteData(datasBeans);
                break;
            case 2:
                View view1 = View.inflate(getContext(),R.layout.popup_item_layout,null);
                popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                popupWindow.showAtLocation(view1, Gravity.HORIZONTAL_GRAVITY_MASK,0,0);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                });
                final EditText ettitle = view1.findViewById(R.id.ettitle);
                Button xiugai = view1.findViewById(R.id.xiugai);
                xiugai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = ettitle.getText().toString();
                        DatasBean datasBean1 = datas1.get(_index);
                        datasBean1.setTitle(str);
                        datasBeanPresenter.upData(datasBean1);
                        popupWindow.dismiss();
                    }
                });
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initView(View view) {
        mCollectRec = (RecyclerView) view.findViewById(R.id.collectRec);
    }

    @Override
    public void setData(List<DatasBean> datasBeans) {
        datas1.clear();
        datas1.addAll(datasBeans);
        myRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTo(String str) {
        Log.i("tag",str);
    }
}
