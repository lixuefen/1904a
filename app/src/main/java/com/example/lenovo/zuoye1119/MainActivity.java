package com.example.lenovo.zuoye1119;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.lenovo.zuoye1119.fragments.CollectFragment;
import com.example.lenovo.zuoye1119.fragments.MainFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout myRl;
    private TabLayout myTab;
    private RelativeLayout mMyRl;
    private TabLayout mMyTab;
    private FragmentManager fm;
    private MainFragment mainFragment;
    private CollectFragment collectFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTab();
        initFragment();
    }
    private void initFragment() {
        fm = getSupportFragmentManager();
        mainFragment = new MainFragment();
        collectFragment = new CollectFragment();
        FragmentTransaction trn = fm.beginTransaction();
        trn.add(R.id.myRl,mainFragment);
        trn.add(R.id.myRl,collectFragment);
        trn.hide(collectFragment);
        trn.commit();
    }
    private void initTab() {
        mMyTab.addTab(mMyTab.newTab().setText("首页"));
        mMyTab.addTab(mMyTab.newTab().setText("收藏"));
        mMyTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==0){
                    FragmentTransaction tn = fm.beginTransaction();
                    tn.show(mainFragment);
                    tn.hide(collectFragment);
                    tn.commit();
                }else {
                    FragmentTransaction tn1 = fm.beginTransaction();
                    tn1.show(collectFragment);
                    tn1.hide(mainFragment);
                    tn1.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void initView() {
        myRl = (RelativeLayout) findViewById(R.id.myRl);
        myTab = (TabLayout) findViewById(R.id.myTab);
    }
}
