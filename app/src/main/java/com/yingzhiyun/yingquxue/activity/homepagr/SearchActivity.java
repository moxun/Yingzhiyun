package com.yingzhiyun.yingquxue.activity.homepagr;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sch.rfview.AnimRFLinearLayoutManager;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.SContentMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.SearchcontentBean;
import com.yingzhiyun.yingquxue.OkBean.Searchcontentjson;
import com.yingzhiyun.yingquxue.OkBean.daobean.HistoryHelper;
import com.yingzhiyun.yingquxue.OkBean.daobean.SearchHistory;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.SearchAdapter;

import com.yingzhiyun.yingquxue.adapter.SearchcontenAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ScontentPresenter;
import com.yingzhiyun.yingquxue.units.CommonUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;

import com.sch.rfview.AnimRFRecyclerView;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActicity<SContentMvp.SContent_View, ScontentPresenter<SContentMvp.SContent_View>>
        implements SContentMvp.SContent_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.deleteall)
    ImageView deleteall;
    @BindView(R.id.recy_search)
    RecyclerView recySearch;
    @BindView(R.id.rela_history)
    RelativeLayout relaHistory;
    @BindView(R.id.recy_content)
    PullLoadMoreRecyclerView recyContent;
    private SearchAdapter searchAdapter;
    private String search;
    private boolean isadd;
    private int page = 1;
    private ArrayList<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> resultBeans;
    private SearchcontenAdapter searchcontenAdapter;

    @Override
    protected void initData() {

        page=1;
        if(HistoryHelper.getInstance().query().size()>0){
            deleteall.setVisibility(View.VISIBLE);
        }else {
            deleteall.setVisibility(View.GONE);
        }
        tvSearch.setLongClickable(true);
        searchAdapter = new SearchAdapter(HistoryHelper.getInstance().query());
        recySearch.setLayoutManager(new LinearLayoutManager(this));
        recySearch.setAdapter(searchAdapter);

        resultBeans = new ArrayList<>();
        searchcontenAdapter = new SearchcontenAdapter(resultBeans, this);

        recyContent.setLinearLayout();
        recyContent.setAdapter(searchcontenAdapter);
        recyContent.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                mPresentser.getSContent(new Gson().toJson(new Searchcontentjson(SharedPreferenceUtils.getUserid()
                        , SharedPreferenceUtils.getToken(), page, MyApp.version,"Android", search)));
                recyContent.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresentser.getSContent(new Gson().toJson(new Searchcontentjson(SharedPreferenceUtils.getUserid()
                        , SharedPreferenceUtils.getToken(), page, MyApp.version,"Android", search)));
                recyContent.setPullLoadMoreCompleted();
            }
        });
        searchAdapter.OnsetOnClickListener(new SearchAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(SearchHistory s) {
                page=1;
                search=s.getTitle();
                mPresentser.getSContent(new Gson().toJson(new Searchcontentjson(SharedPreferenceUtils.getUserid()
                        , SharedPreferenceUtils.getToken(), page, MyApp.version,"Android", search)));
            }
        });

        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
//如果actionId是搜索的id，则进行下一步的操作
                    serarch();
                }
                return false; }
        });


    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.iv_search, R.id.deleteall, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.iv_search:
                serarch();
                break;
            case R.id.deleteall:
                deleteall.setVisibility(View.GONE);
                HistoryHelper.getInstance().deleteAll();
                searchAdapter.list.clear();
                searchAdapter.notifyDataSetChanged();
                break;
            case R.id.search:
                serarch();
                break;
        }
    }

    private void serarch() {
        page = 1;

        if (StringUtils.isEmpty(tvSearch.getText().toString())) {

            ToastUtil.makeLongText(this,"请输入搜索内容");
            return;
        }
        boolean isadd = HistoryHelper.getInstance().queryLikeId(tvSearch.getText().toString());
        if (isadd) {
            HistoryHelper.getInstance().insert(new SearchHistory(null, tvSearch.getText().toString()));
            searchAdapter.list.add(new SearchHistory(null, tvSearch.getText().toString()));
            searchAdapter.notifyDataSetChanged();

        }
        search=tvSearch.getText().toString();
        mPresentser.getSContent(new Gson().toJson(new Searchcontentjson(SharedPreferenceUtils.getUserid()
                , SharedPreferenceUtils.getToken(), page, "Android", MyApp.version,search)));
        tvSearch.setText("");
        deleteall.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSContent(SearchcontentBean sContent) {

        if (sContent.getResult().size() > 0) {
            relaHistory.setVisibility(View.GONE);
            recyContent.setVisibility(View.VISIBLE);
            resultBeans.addAll(sContent.getResult());
            searchcontenAdapter.notifyDataSetChanged();
        }else{
            ToastUtil.makeLongText(this,"暂无内容");
        }
        if (page == 1) {

            if (sContent.getResult().size() > 0) {
                resultBeans.clear();
                resultBeans.addAll(sContent.getResult());
                searchcontenAdapter.notifyDataSetChanged();
            } else {
                ToastUtil.makeLongText(this,"暂无内容");
            }
        } else {
            if (sContent.getResult().size() > 0) {

                resultBeans.addAll(sContent.getResult());
                searchcontenAdapter.notifyDataSetChanged();
            } else {
                recyContent.setPullRefreshEnable(false);
            }
        }
    }

    @Override
    protected ScontentPresenter<SContentMvp.SContent_View> createPresenter() {
        return new ScontentPresenter<>();
    }


}
