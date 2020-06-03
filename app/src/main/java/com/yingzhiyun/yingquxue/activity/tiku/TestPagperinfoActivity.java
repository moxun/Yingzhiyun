package com.yingzhiyun.yingquxue.activity.tiku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.TestPaperInfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TestpagperinfoJson;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.PagerTypeAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.TestPagperInfoPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestPagperinfoActivity extends BaseActicity<TestPaperInfoMvp.TestPaperinfo_View, TestPagperInfoPresenter<TestPaperInfoMvp.TestPaperinfo_View>>
        implements TestPaperInfoMvp.TestPaperinfo_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.pagper_title)
    TextView pagperTitle;
    @BindView(R.id.pagper_info)
    TextView pagperInfo;
    @BindView(R.id.pagper_type)
    TextView pagperType;
    @BindView(R.id.recyType)
    RecyclerView recyType;
    @BindView(R.id.start_work)
    TextView startWork;
    private TestPagperInfo.ResultBean.TestPaperOutlineBeanBean testPaperOutlineBean;
    private ArrayList<TestPagperInfo.ResultBean.TestPaperOutlineBeanBean.ContentBean> contentBeans;
    private PagerTypeAdapter pagerTypeAdapter;
    private int id;
    private TestPagperInfo bean;
    private String juantype;
    public static TestPagperinfoActivity instance;

    @SuppressLint("SetTextI18n")
    @Override
    public void setTestPaperinfo(TestPagperInfo testPaperinfo) {
        if(testPaperinfo.getStatus()==200){
            bean =testPaperinfo;

            testPaperOutlineBean = testPaperinfo.getResult().getTestPaperOutlineBean();
            Log.d("moxun", "setTestPaperinfo: "+testPaperOutlineBean.getContent().size());
            pagperTitle.setText(testPaperOutlineBean.getTitle());
            if(bean.getResult().getScore()==null){
                pagperInfo.setText("共"+bean.getResult().getTotalSize()+"题");
            }else{
                pagperInfo.setText("满分"+testPaperOutlineBean.getTotalScore()+"，共"+bean.getResult().getTotalSize()+"题");
            }

            pagperType.setText("共分为"+testPaperOutlineBean.getContent().size()+"个部分");
            contentBeans.addAll(testPaperOutlineBean.getContent());
            pagerTypeAdapter.notifyDataSetChanged();
        }else{
            ToastUtil.makeLongText(this,testPaperinfo.getHint());
        }
    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {

    }

    @Override
    protected TestPagperInfoPresenter<TestPaperInfoMvp.TestPaperinfo_View> createPresenter() {
        return new TestPagperInfoPresenter<>();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        instance=this;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        bean = (TestPagperInfo) intent.getSerializableExtra("bean");
        juantype = intent.getStringExtra("juantype");
        contentBeans = new ArrayList<>();
        pagerTypeAdapter = new PagerTypeAdapter(R.layout.item_pagpertype, contentBeans);
        recyType.setLayoutManager(new LinearLayoutManager(this));
        recyType.setNestedScrollingEnabled(false);
        recyType.setAdapter(pagerTypeAdapter);
        if(bean!=null){

            testPaperOutlineBean = bean.getResult().getTestPaperOutlineBean();
            pagperTitle.setText(testPaperOutlineBean.getTitle());
            if(bean.getResult().getScore()==null){
                pagperInfo.setText("共"+bean.getResult().getTotalSize()+"题");
            }else{
                pagperInfo.setText("满分"+testPaperOutlineBean.getTotalScore()+"，共"+bean.getResult().getTotalSize()+"题");
            }
            pagperType.setText("共分为"+testPaperOutlineBean.getContent().size()+"个部分");
            contentBeans.addAll(testPaperOutlineBean.getContent());
            pagerTypeAdapter.notifyDataSetChanged();
        }else{
            mPresentser.getTestPagperInfo(new Gson().toJson(new TestpagperinfoJson(SharedPreferenceUtils.getUserid(),SharedPreferenceUtils.getToken(), id +"", MyApp.version,"Android")));
        }


    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_testpagperinfo;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.finish, R.id.start_work})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.start_work:
                if(bean!=null){
                    String title = bean.getResult().getTitle();
                    finish();
                    startActivity(new Intent(this, ZuTiActivity.class).putExtra("id",id).putExtra("bean", bean).putExtra("juantype",juantype));
//                    if(!(SharedPreferenceUtils.getsubject_id()==6)){
//                        startActivity(new Intent(this, LiberalartZutiActivity.class).putExtra("id",id));
//                    }else{
//                        startActivity(new Intent(this, ZuTiActivity.class).putExtra("id",id));
//                    }
                }else{
                    ToastUtil.makeLongText(this,"您未登录或账号信息过期");
                    finish();
                    startActivity(PwdLoginActivity.class);
                }


                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        contentBeans.clear();
    }
}
