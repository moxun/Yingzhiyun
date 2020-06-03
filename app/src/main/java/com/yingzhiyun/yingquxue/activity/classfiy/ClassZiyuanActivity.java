package com.yingzhiyun.yingquxue.activity.classfiy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.ClassifyMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.CoursewareJson;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectInfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.OnceSearchActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ClassifyPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassZiyuanActivity extends BaseActicity<ClassifyMvp.Classify_View, ClassifyPresenter<ClassifyMvp.Classify_View>> implements ClassifyMvp.Classify_View {

    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_live)
    RecyclerView recyLive;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    private ZiyuanAdapter ziyuanAdapter;
    private ArrayList<ZiyuanBean.ResultBean> resultBeans = new ArrayList<>();

    @Override
    public void setSubject(SubjectBean subject) {

    }

    @Override
    public void setCoursewareList(ZiyuanBean coursewareList) {

        if (coursewareList.getStatus() == 200) {
            if (coursewareList.getResult().size() >0) {
                recyLive.setVisibility(View.VISIBLE);
                linearModle.setVisibility(View.GONE);
                resultBeans.addAll(coursewareList.getResult());
                ziyuanAdapter.notifyDataSetChanged();
            } else {
                recyLive.setVisibility(View.GONE);
                linearModle.setVisibility(View.VISIBLE);
            }

        } else if(coursewareList.getStatus()==511){
            finish();
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    protected ClassifyPresenter<ClassifyMvp.Classify_View> createPresenter() {
        return new ClassifyPresenter<>();
    }

    @Override
    protected void initData() {


        toolTitle.setText("名校试卷");
        resultBeans = new ArrayList<>();
        ziyuanAdapter = new ZiyuanAdapter(resultBeans, this);
        recyLive.setNestedScrollingEnabled(false);
        recyLive.setLayoutManager(new LinearLayoutManager(this));
        recyLive.setAdapter(ziyuanAdapter);
        ivSearch.setVisibility(View.VISIBLE);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putString("type","classify");
                startActivity(OnceSearchActivity.class,bundle);
            }
        });

        getList();
    }

    private void getList() {
        mPresentser.getCoursewareList(new Gson().toJson(new CoursewareJson(MyApp.version,"Android",1 , 0, 1, SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken())));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_mylive;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }



}
