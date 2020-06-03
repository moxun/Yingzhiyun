package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.userinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.BashiinfoAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.userinfoPrsenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyyatiinfoActivity extends BaseActicity<userinfoMvp.userinfo_View, userinfoPrsenter<userinfoMvp.userinfo_View>> implements userinfoMvp.userinfo_View {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_mybet)
    PullLoadMoreRecyclerView recyMybet;
    private BetListBean.ResultBean bean;
    private JSONObject jsonObject;
    private ArrayList<BashiinfoBean.ResultBean.DetailBean> resultBeans = new ArrayList<>();
    private BashiinfoAdapter bashiinfoAdapter;

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

    }

    @Override
    public void setmyBetFiles(YatiBean bashiinfoBean) {

        if (bashiinfoBean.getStatus() == 200) {

            if (bashiinfoBean.getResult() != null) {
                for (int i = 0; i < bashiinfoBean.getResult().size(); i++) {
            if(bashiinfoBean.getResult().get(i).getDetail().size()>0){
                resultBeans.add(bashiinfoBean.getResult().get(i));
            }


                }

                if (resultBeans.size() > 0) {

                    bashiinfoAdapter.notifyDataSetChanged();
                }
            }

        } else if (bashiinfoBean.getStatus() == 511) {
            finish();
            startActivity(PwdLoginActivity.class);
        }else  if(bashiinfoBean.getStatus()==529){
            finish();
            ToastUtil.makeShortText(this,bashiinfoBean.getHint());
        }
    }

    @Override
    protected userinfoPrsenter<userinfoMvp.userinfo_View> createPresenter() {
        return new userinfoPrsenter<>();
    }

    @Override
    protected void initData() throws ParseException {
        bean = (BetListBean.ResultBean) getIntent().getSerializableExtra("bean");
        bashiinfoAdapter = new BashiinfoAdapter(resultBeans, this);
        recyMybet.setLinearLayout();
        toolTitle.setText(bean.getTitle());
        recyMybet.setAdapter(bashiinfoAdapter);
        recyMybet.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                resultBeans.clear();
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

    @Override
    public int createLayoutID() {
        return R.layout.activity_mybet;
    }

    private void getList() {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());

            jsonObject.put("userBetId",bean.getId());
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("===========", "getList: "+jsonObject.toString());
        mPresentser.getmyBetFiles(jsonObject.toString());
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
