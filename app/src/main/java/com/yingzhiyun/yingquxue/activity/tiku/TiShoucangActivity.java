package com.yingzhiyun.yingquxue.activity.tiku;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.WrongMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.MyTiJson;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.WrongAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.presenter.WrongPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiShoucangActivity extends BaseActicity<WrongMvp.Wrong_View, WrongPresenter<WrongMvp.Wrong_View>> implements WrongMvp.Wrong_View  {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_live)
    XRecyclerView recyLive;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private ArrayList<MyTiBean.ResultBean> resultBeans;
    private WrongAdapter wrongAdapter;
    private int page = 1;
    private int id;

    @Override
    public void setWronglist(WrongtitleBean wronglist) {

    }

    @Override
    public void setWrong(MyTiBean myTiBean) {

    }

    @Override
    public void setdeleteWrong(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setCollection(MyTiBean myTiBean) {
        if (myTiBean.getResult().size() > 0) {
            resultBeans.addAll(myTiBean.getResult());
            wrongAdapter.notifyDataSetChanged();
            recyLive.setVisibility(View.VISIBLE);
            linearModle.setVisibility(View.GONE);
        } else {
            if(page==1){
                recyLive.setVisibility(View.GONE);
                linearModle.setVisibility(View.VISIBLE);
            }else{
                ToastUtil.makeLongText(this,"暂无数据");
            }

        }
    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {

    }

    @Override
    public void setTiRecordlist(WrongtitleBean wronglist) {

    }

    @Override
    protected WrongPresenter<WrongMvp.Wrong_View> createPresenter() {
        return new WrongPresenter<>();
    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        toolTitle.setText("我的收藏");
        resultBeans = new ArrayList<>();
        wrongAdapter = new WrongAdapter(resultBeans, this,id,"collect");

        recyLive.setLayoutManager(new LinearLayoutManager(this));
        recyLive.setAdapter(wrongAdapter);
        //0代表刷新，1代表加载更多
        recyLive.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                page++;
                Jiexi(page);
                recyLive.loadMoreComplete();
            }

        });




    }

    private void Jiexi(int page) {
        mPresentser.getCollection(new Gson().toJson(new MyTiJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), id, page, MyApp.version,"Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_wronglist;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }



    @Override
    protected void onResume() {
        super.onResume();
        resultBeans.clear();
        page=1;
        Jiexi(page);
    }
}
