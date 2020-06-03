package com.yingzhiyun.yingquxue.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.userinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.SearchSchoolJson;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.sijiliebiao.SchoolSearchAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.userinfoPrsenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchSchoolActivity extends BaseActicity<userinfoMvp.userinfo_View, userinfoPrsenter<userinfoMvp.userinfo_View>> implements userinfoMvp.userinfo_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.search)
    TextView search;

    @BindView(R.id.recy_school)
    RecyclerView recySearch;
    @BindView(R.id.jieguo)
    TextView jieguo;
    private ArrayList<SchoolBean.ResultBean> resultBeans;
    private SchoolSearchAdapter schoolSearchAdapter;

    @Override
    public void setuserinfo(UserinfoBean userinfoBean) {

    }

    @Override
    public void setSchool(SchoolBean school) {
        resultBeans.clear();
        if(school.getResult().size()!=0){
            resultBeans.addAll(school.getResult());
            jieguo.setText("搜索结果");
        }else{
            jieguo.setText("没有相关学校");
        }
        schoolSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void setupdateinfo(CollectBean collectBean) {

    }

    @Override
    public void setmyBetList(BetListBean betListBean) {

    }

    @Override
    public void setmyBetFiles(YatiBean bashiinfoBean) {

    }

    @Override
    protected userinfoPrsenter<userinfoMvp.userinfo_View> createPresenter() {
        return new userinfoPrsenter<>();
    }

    @Override
    protected void initData() throws ParseException {
        resultBeans = new ArrayList<>();
        tvSearch.setLongClickable(true);
        schoolSearchAdapter = new SchoolSearchAdapter(resultBeans);
        recySearch.setLayoutManager(new LinearLayoutManager(this));
        recySearch.setAdapter(schoolSearchAdapter);
        schoolSearchAdapter.setOnItemListener(new SchoolSearchAdapter.OnItemListener() {
            @Override
            public void onClick(SchoolBean.ResultBean resultBean) {
                setResult(5,new Intent().putExtra("bean",resultBean));
                finish();
            }
        });
        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
//如果actionId是搜索的id，则进行下一步的操作
                    if (!StringUtils.isEmpty(tvSearch.getText().toString())) {
                        mPresentser.getSchool(new Gson().toJson(new SearchSchoolJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), tvSearch.getText().toString(), MyApp.version,"Android")));
                    } else {
                        ToastUtil.makeLongText(SearchSchoolActivity.this, "请输入内容");
                    }

                }
                return false; }
        });
        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!StringUtils.isEmpty(tvSearch.getText().toString())) {
                    mPresentser.getSchool(new Gson().toJson(new SearchSchoolJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), charSequence.toString(), MyApp.version,"Android")));
                } else {
                    ToastUtil.makeLongText(SearchSchoolActivity.this, "请输入内容");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_searchschool;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.search:
//                if (!StringUtils.isEmpty(tvSearch.getText().toString())) {
//                    mPresentser.getSchool(new Gson().toJson(new SearchSchoolJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), tvSearch.getText().toString())));
//                } else {
//                    ToastUtil.makeLongText(this, "请输入内容");
//                }
                finish();
                break;
        }
    }


}
