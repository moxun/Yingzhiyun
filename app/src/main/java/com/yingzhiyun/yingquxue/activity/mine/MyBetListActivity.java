package com.yingzhiyun.yingquxue.activity.mine;

import android.widget.ImageButton;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.userinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.ForecastAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.userinfoPrsenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MyBetListActivity extends BaseActicity<userinfoMvp.userinfo_View, userinfoPrsenter<userinfoMvp.userinfo_View>> implements userinfoMvp.userinfo_View {
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_mybet)
    PullLoadMoreRecyclerView recyMybet;
    private JSONObject jsonObject;
    private ArrayList<BetListBean.ResultBean> resultBeans;
    private ForecastAdapter forecastAdapter;

    @Override
    public void setuserinfo(UserinfoBean userinfoBean) {

    }

    @Override
    public void setSchool(SchoolBean school) {

    }

    @Override
    public void setupdateinfo(CollectBean collectBean) {

    }

    @Override
    public void setmyBetList(BetListBean betListBean) {
        if(betListBean.getStatus()==200){
            if(betListBean.getResult().size()>0){
                resultBeans.clear();

                resultBeans.addAll(betListBean.getResult());
                forecastAdapter.notifyDataSetChanged();
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
    public void setmyBetFiles(YatiBean bashiinfoBean) {

    }

    @Override
    protected userinfoPrsenter<userinfoMvp.userinfo_View> createPresenter() {
        return new userinfoPrsenter<>();
    }

    @Override
    protected void initData() throws ParseException {

        resultBeans = new ArrayList<>();
        toolTitle.setText("我的押题卷");
        forecastAdapter = new ForecastAdapter(resultBeans, this,"mine", "0");
        recyMybet.setLinearLayout();
        recyMybet.setAdapter(forecastAdapter);
        recyMybet.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                getList();
                recyMybet.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                recyMybet.setPullLoadMoreCompleted();
            }
        });
        getList();


    }

    private void getList() {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());


            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPresentser.getmyBetList(jsonObject.toString());
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_mybet;
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
