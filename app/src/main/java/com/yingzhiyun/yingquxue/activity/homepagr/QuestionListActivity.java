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

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.Mvp.TiRecordMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TeachingShaiJson;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.BashiAdapter;
import com.yingzhiyun.yingquxue.adapter.ProvinceAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;
import com.yingzhiyun.yingquxue.presenter.TiRecordPresenter;
import com.yingzhiyun.yingquxue.units.ActivityCollector;
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

public class QuestionListActivity  extends BaseActicity<TestPaperListMvp.TestPaperList_View, TestPaperListPresenter<TestPaperListMvp.TestPaperList_View>>
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
    private PopupWindow popupWindow;
    private Animation animIn;
    private Animation animOut;
    private TranslateAnimation animation;
    private View view;
    private ArrayList<SelectedOptionsBean.ResultBean> resultBeans;
    private ProvinceAdapter provinceAdapter;
    private HomePagerBean.ResultBean.MenuBean moduleBean;
    private int page=1;
    private ArrayList<ZiyuanBean.ResultBean> ziyuanLists=new ArrayList<>();
    private BashiAdapter bashiAdapter;
    private int province=21;
    private String provinceString="河南";
    private ZiyuanBean.ResultBean listBean;
    private TextView shoucang;

    @Override
    protected void initData() throws ParseException {
        animIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        animOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);
        initPopup();

        moduleBean = (HomePagerBean.ResultBean.MenuBean) getIntent().getSerializableExtra("bean");
        if(moduleBean.getType().equals("folderList-noOpt ")){
            textStage.setVisibility(View.GONE);
            chooseStage.setVisibility(View.GONE);
        }
        toolTitle.setText(moduleBean.getTitle());
        textStage.setText(provinceString);
        ArrayList<String> jsonstrings = new ArrayList<>();
        jsonstrings.add("province");
        mPresentser.getfilterItem(new Gson().toJson(new TeachingShaiJson(jsonstrings)));
        bashiAdapter = new BashiAdapter(ziyuanLists);
        recyTsetpagper.setLinearLayout();
        recyTsetpagper.setAdapter(bashiAdapter);
        bashiAdapter.OnsetOnClickListener(new BashiAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(ZiyuanBean.ResultBean resultBean, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", resultBean.getId());
                bundle.putString("title", resultBean.getTitle());
                bundle.putBoolean("is",resultBean.isFolderCollection());
                startActivity(BashiinfoActivity.class, bundle);
            }

            @Override
            public void setCollListener(ZiyuanBean.ResultBean resultBean, int position, TextView textView) {
                listBean = resultBean;
                shoucang = textView;
                CollictionList(resultBean.getId());
            }
        });
        textStage.setText(provinceString);
        recyTsetpagper.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                    page=1;
                getList();
            }

            @Override
            public void onLoadMore() {


                page++;
       getList();
            }
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("indexListTypeId",moduleBean.getId());
            jsonObject.put("province", province);
            jsonObject.put("pageNum", page);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPresentser.getZiyuan(jsonObject.toString());
    }

    private void getList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("indexListTypeId",moduleBean.getId());
            jsonObject.put("province", province);
            jsonObject.put("pageNum", page);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPresentser.getZiyuan(jsonObject.toString());
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_questionlist;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }
    @Override
    protected void Errorcool(String msg) {
        super.showLoading(msg);
        finish();
        startActivity(PwdLoginActivity.class);
        ToastUtil.makeShortText(this,msg);
    }
    private void initPopup() {
        popupWindow = new PopupWindow(this);
        view = LayoutInflater.from(this).inflate(R.layout.pop_province, null);
        RecyclerView recyclerView = view.findViewById(R.id.recy_province);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        resultBeans = new ArrayList<>();
        provinceAdapter = new ProvinceAdapter(resultBeans);
        recyclerView.setAdapter(provinceAdapter);
        ImageButton close = view.findViewById(R.id.close);

        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.setFocusable(true);
        provinceAdapter.OnsetOnClickListener(new ProvinceAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(SelectedOptionsBean.ResultBean resultBean, int position) {
                province=resultBean.getId();
                provinceString=resultBean.getName();
                textStage.setText(provinceString);
                getList();
                popupWindow.dismiss();
            }
        });

        popupWindow.setWidth(ScreenUtils.getScreenW(this));
        // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
        animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setDuration(200);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mainDarkview.startAnimation(animOut);
                mainDarkview.setVisibility(View.GONE);

            }
        });

    }

    @OnClick({R.id.back, R.id.text_stage, R.id.choose_stage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:

                finish();
                break;
            case R.id.text_stage:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAtLocation(findViewById(R.id.layout_ra), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    view.startAnimation(animation);
                    //背景变暗
                    mainDarkview.startAnimation(animIn);
                    mainDarkview.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.choose_stage:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAtLocation(findViewById(R.id.layout_ra), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    view.startAnimation(animation);
                    //背景变暗
                    mainDarkview.startAnimation(animIn);
                    mainDarkview.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


    @Override
    public void setTestPaperList(TestPaperListBean testPaperList) {

    }
    @Override
    protected void showLoading(String msg) {
        super.showLoading(msg);
        if(listBean.isFolderCollection()){
            shoucang.setText("收藏");
            shoucang.setBackgroundResource(R.drawable.shoucang);
            listBean.setFolderCollection(false);
            ToastUtil.makeShortText(this,"取消收藏成功");
        }   else {
            shoucang.setText("已收藏");
            shoucang.setBackgroundResource(R.drawable.yiguanzhu);
            listBean.setFolderCollection(true);
            ToastUtil.makeShortText(this,"收藏成功");
        }

    }
    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {
        if(teachingShaixuanBean.getStatus()==200){
            resultBeans.addAll(teachingShaixuanBean.getResult().getProvince());
            provinceAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {
        if (ziyuan.getStatus() == 200) {
            recyTsetpagper.setPullLoadMoreCompleted();
            Log.d("0000000000", "setZiyuan: "+page+"---"+ziyuan.getResult().size());
            if (page == 1) {
                if (ziyuan.getResult().size() > 0) {
                    ziyuanLists.clear();
                    linearModle.setVisibility(View.GONE);
                    recyTsetpagper.setVisibility(View.VISIBLE);

                    ziyuanLists.addAll(ziyuan.getResult());
                    bashiAdapter.notifyDataSetChanged();
                } else {
                    linearModle.setVisibility(View.VISIBLE);
                    recyTsetpagper.setVisibility(View.GONE);
                }
            } else {
                if (ziyuan.getResult().size() > 0) {

                    ziyuanLists.addAll(ziyuan.getResult());
                    bashiAdapter.notifyDataSetChanged();
                } else {
                    recyTsetpagper.setPullRefreshEnable(false);
                }
            }


        } else {

            ToastUtil.makeShortText(this, "身份过期");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setbetList(BetListBean betListBean) {

    }

    @Override
    public void setbetDetail(BetDetailBean betDetailBean) {

    }

    @Override
    protected TestPaperListPresenter<TestPaperListMvp.TestPaperList_View> createPresenter() {
        return new TestPaperListPresenter<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
