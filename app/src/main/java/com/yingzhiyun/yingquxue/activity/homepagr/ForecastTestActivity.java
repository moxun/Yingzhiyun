package com.yingzhiyun.yingquxue.activity.homepagr;

import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.ForecastAdapter;
import com.yingzhiyun.yingquxue.adapter.ProvinceAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;
import com.yingzhiyun.yingquxue.units.ScreenUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForecastTestActivity extends BaseActicity<TestPaperListMvp.TestPaperList_View, TestPaperListPresenter<TestPaperListMvp.TestPaperList_View>>
        implements TestPaperListMvp.TestPaperList_View {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.text_stage)
    TextView textStage;
    @BindView(R.id.choose_stage)
    ImageView chooseStage;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.recy_tsetpagper)
    PullLoadMoreRecyclerView recyTsetpagper;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.main_darkview)
    View mainDarkview;
    @BindView(R.id.layout_ra)
    RelativeLayout layoutRa;
    private PopupWindow popupWindow;
    private Animation animIn;
    private Animation animOut;
    private TranslateAnimation animation;
    private View view;
    private ArrayList<SelectedOptionsBean.ResultBean> resultBeans;
    private ProvinceAdapter provinceAdapter;
    private ArrayList<BetListBean.ResultBean> strings;
    private ForecastAdapter forecastAdapter;
    private String gradetype;

    @Override
    public void setTestPaperList(TestPaperListBean testPaperList) {

    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {

    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {

    }

    @Override
    public void setbetList(BetListBean betListBean) {
        if(betListBean.getStatus()==200){
            if(betListBean.getResult().size()>0){
                strings.clear();
                recyTsetpagper.setVisibility(View.VISIBLE);
                linearModle.setVisibility(View.GONE);
                strings.addAll(betListBean.getResult());
                forecastAdapter.notifyDataSetChanged();
            }else {
                recyTsetpagper.setVisibility(View.GONE);
                linearModle.setVisibility(View.VISIBLE);
            }
        }else if(betListBean.getStatus()==511){
            finish();
            startActivity(PwdLoginActivity.class);
            ToastUtil.makeShortText(this,"身份过期请重新登录");
        }else {
            ToastUtil.makeShortText(this,betListBean.getHint());
        }
    }

    @Override
    public void setbetDetail(BetDetailBean betDetailBean) {

    }

    @Override
    protected TestPaperListPresenter<TestPaperListMvp.TestPaperList_View> createPresenter() {
        return new TestPaperListPresenter<>();
    }

    @Override
    protected void initData() throws ParseException {

        toolTitle.setText("押题卷");
        gradetype = getIntent().getStringExtra("gradetype");
        textStage.setVisibility(View.GONE);
        chooseStage.setVisibility(View.GONE);
        animIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        animOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);

        strings = new ArrayList<>();

        linearModle.setVisibility(View.GONE);
        recyTsetpagper.setVisibility(View.VISIBLE);
        forecastAdapter = new ForecastAdapter(strings,this,"all",gradetype);
        recyTsetpagper.setLinearLayout();
        recyTsetpagper.setPushRefreshEnable(true);
        recyTsetpagper.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                getList();
                recyTsetpagper.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                recyTsetpagper.setPullLoadMoreCompleted();
            }
        });
        recyTsetpagper.setAdapter(forecastAdapter);
getList();
    }
    private void getList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("gradeType",gradetype);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("moxun", "initData: "+jsonObject.toString());
        mPresentser.getbetList(jsonObject.toString());
    }
    @Override
    public int createLayoutID() {
        return R.layout.activity_questionlist;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;


        }
    }
}
