package com.yingzhiyun.yingquxue.Fragment.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.userinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.VipHintBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.OkBean.localbean.TestBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.mine.UpdateinfoActivity;
import com.yingzhiyun.yingquxue.activity.vip.VipinfoActivity;
import com.yingzhiyun.yingquxue.adapter.MineAdapter;
import com.yingzhiyun.yingquxue.adapter.ResponeAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils;
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack;
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback;
import com.yingzhiyun.yingquxue.presenter.userinfoPrsenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;

public class MineFragment extends BaseFragment<userinfoMvp.userinfo_View, userinfoPrsenter<userinfoMvp.userinfo_View>> implements userinfoMvp.userinfo_View {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.recy_mine)
    RecyclerView recyMine;
    @BindView(R.id.vip)
    ImageView vip;
    @BindView(R.id.recy_respone)
    RecyclerView recyRespone;
    @BindView(R.id.no_login)
    RelativeLayout noLogin;
    @BindView(R.id.user_head)
    ImageView userHead;
    @BindView(R.id.user_title)
    TextView userTitle;
    @BindView(R.id.school_name)
    TextView schoolName;
    @BindView(R.id.re_login)
    RelativeLayout reLogin;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.mylearn)
    TextView mylearn;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.clazz)
    TextView clazz;
    @BindView(R.id.vip_hint)
    ImageView vipHint;
    private List<ModuleBean> moduleBeans = new ArrayList<>();
    private ArrayList<TestBean> recordBeans = new ArrayList<>();

    private ResponeAdapter recordAdapter;
    private JSONObject jsonObject;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

        if (SharedPreferenceUtils.getisLogin()) {
            noLogin.setVisibility(View.GONE);
            reLogin.setVisibility(View.VISIBLE);
        } else {
            noLogin.setVisibility(View.VISIBLE);
            reLogin.setVisibility(View.GONE);
        }

        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(SharedPreferenceUtils.getuserhead())
                .error(R.mipmap.icon_userhead)
                .apply(requestOptions)
                .into(userHead);
        userTitle.setText(SharedPreferenceUtils.getusername());
        jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());


            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (SharedPreferenceUtils.getisLogin()) {
            presenter.getmyBetList(jsonObject.toString());
        }
        if (SharedPreferenceUtils.getSchool().equals("")) {
            schoolName.setText("请添加学校");
        } else {
            schoolName.setText(SharedPreferenceUtils.getSchool());
        }


        clazz.setText(SharedPreferenceUtils.getClazz());
        moduleBeans.add(new ModuleBean("我的课程", R.mipmap.icon_course, 0));
        moduleBeans.add(new ModuleBean("我的错题", R.mipmap.icon_mywrong, 0));
        moduleBeans.add(new ModuleBean("成绩报告", R.mipmap.icon_mtexam, 0));
        moduleBeans.add(new ModuleBean("做题记录", R.mipmap.icon_tirecord, 0));
        recyMine.setNestedScrollingEnabled(false);
        recyMine.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyMine.setAdapter(new MineAdapter(moduleBeans, getContext()));
        recordBeans.add(new TestBean(R.drawable.icon_duihuan, "课程兑换", ""));
        recordBeans.add(new TestBean(R.mipmap.icon_balance, "我的钱包", ""));
        recordBeans.add(new TestBean(R.mipmap.icon_guanzhu, "我的关注", ""));
        recordBeans.add(new TestBean(R.mipmap.icon_mine_faourt, "我的收藏", ""));
        recordBeans.add(new TestBean(R.mipmap.icon_liulan, "浏览记录", ""));
        recordBeans.add(new TestBean(R.mipmap.icon_fan, "问题反馈", ""));
        recordBeans.add(new TestBean(R.mipmap.icon_study, "学习天地", ""));

        recordBeans.add(new TestBean(R.mipmap.icon_settings, "设置", ""));

        recordAdapter = new ResponeAdapter(recordBeans, context);
        recyRespone.setNestedScrollingEnabled(false);
        recyRespone.setLayoutManager(new LinearLayoutManager(getContext()));
        recyRespone.setAdapter(recordAdapter);
        vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VipinfoActivity.class);
            }
        });
    }


    @OnClick({R.id.login, R.id.vip, R.id.re_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(new Intent(getContext(), PwdLoginActivity.class));
                break;
            case R.id.vip:
                break;
            case R.id.re_login:
                startActivity(UpdateinfoActivity.class);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //TODO now visible to user
            if (SharedPreferenceUtils.getisLogin()) {
                presenter.getmyBetList(jsonObject.toString());
            }
            isVipInfo();
            presenter.getuserinfo(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
        } else {
            if (SharedPreferenceUtils.getisLogin()) {
                presenter.getmyBetList(jsonObject.toString());
            }
            //TODO now invisible to user
            isVipInfo();
            presenter.getuserinfo(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
        }
    }

    public void isVipInfo() {


        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString()
                .url(FristServer.URL + "vipInfo")
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<VipHintBean>() {
                    @Override
                    public void onError(String e) {

                    }

                    @Override
                    public void onResponse(VipHintBean response) throws JSONException {

                        if (response.getResult().isVip()) {
                            vipHint.setImageResource(R.drawable.icon_vip_hint);
                        }else {
                            vipHint.setImageResource(R.drawable.icon_novip_hint);
                        }
                    }
                }));

    }

    @Override
    public void onResume() {
        super.onResume();


        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(SharedPreferenceUtils.getuserhead())
                .apply(requestOptions)
                .into(userHead);
        userTitle.setText(SharedPreferenceUtils.getusername());
        schoolName.setText(SharedPreferenceUtils.getSchool());
        if (SharedPreferenceUtils.getClazz().equals("")) {
            clazz.setText("请添加年级");
        } else {
            clazz.setText(SharedPreferenceUtils.getClazz());
        }
        isVipInfo();
        presenter.getuserinfo(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
    }


    @Override
    public void setuserinfo(UserinfoBean userinfoBean) {
        if (userinfoBean.getStatus() == 511) {
            SharedPreferenceUtils.setisLogin(false);
            noLogin.setVisibility(View.VISIBLE);
            reLogin.setVisibility(View.GONE);
        }
    }

    @Override
    public void setSchool(SchoolBean school) {

    }

    @Override
    public void setupdateinfo(CollectBean collectBean) {

    }

    @Override
    public void setmyBetList(BetListBean betListBean) {

        if (betListBean.getStatus() == 200) {
            if (betListBean.getResult() != null) {
                if (betListBean.getResult().size() > 0) {
                    if (recordBeans.size() < 9) {
                        recordBeans.add(0, new TestBean(R.mipmap.icon_yatitwo, "我的押题卷", ""));
                        recordAdapter.notifyDataSetChanged();
                    }

                } else {
                    recordBeans.clear();
                    recordBeans.add(new TestBean(R.drawable.icon_duihuan, "课程兑换", ""));
                    recordBeans.add(new TestBean(R.mipmap.icon_balance, "我的钱包", ""));
                    recordBeans.add(new TestBean(R.mipmap.icon_guanzhu, "我的关注", ""));
                    recordBeans.add(new TestBean(R.mipmap.icon_mine_faourt, "我的收藏", ""));
                    recordBeans.add(new TestBean(R.mipmap.icon_liulan, "浏览记录", ""));
                    recordBeans.add(new TestBean(R.mipmap.icon_fan, "问题反馈", ""));
                    recordBeans.add(new TestBean(R.mipmap.icon_study, "学习天地", ""));

                    recordBeans.add(new TestBean(R.mipmap.icon_settings, "设置", ""));
                    recordAdapter.notifyDataSetChanged();
                }
            }

        }
    }

    @Override
    public void setmyBetFiles(YatiBean bashiinfoBean) {

    }

    @Override
    protected userinfoPrsenter<userinfoMvp.userinfo_View> createPresenter() {
        return new userinfoPrsenter<>();
    }
}
