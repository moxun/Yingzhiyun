package com.yingzhiyun.yingquxue.activity.homepagr;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.Mvp.ExaminationMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ExaminationPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaomingActivity extends BaseActicity<ExaminationMvp.Examination_View, ExaminationPresenter<ExaminationMvp.Examination_View>>
        implements ExaminationMvp.Examination_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.kaoshi_time)
    TextView kaoshiTime;
    @BindView(R.id.baoming_size)
    TextView baomingSize;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.hint)
    TextView hint;
    private int id;
    private JSONObject jsonObject;

    @Override
    public void setExaminationList(ExaminationListBean examineBean) {

    }

    @Override
    public void setexamDetail(BaominginfoBean baominginfoBean) {
        if (baominginfoBean.getStatus() == 200) {

            String secondToDayHourMinutes = getSecondToDayHourMinutes(baominginfoBean.getResult().getTime() * 1000, baominginfoBean.getResult().isIsSign());
            kaoshiTime.setText(Html.fromHtml(secondToDayHourMinutes));
            StringBuilder stringBuilder = new StringBuilder();
            hint.setText(baominginfoBean.getResult().getInstruction().replace("\\n","\n").replace("\\r","\r"));
            stringBuilder.append("<font color='#ffffff' >").append(baominginfoBean.getResult().getSignNum()).append("</font>").append("<font color='#000000' >人报名</font>");
            baomingSize.setText(Html.fromHtml(stringBuilder.toString()));
            if (baominginfoBean.getResult().isIsSign()) {
                btnLogin.setEnabled(false);
                btnLogin.setText("已报名");
            }
        }else if (baominginfoBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        }


    }


    @Override
    public void setexamsing(BaominginfoBean baominginfoBean) {
        Log.d("=========", "setexamsing: "+baominginfoBean.getHint());
        if (baominginfoBean.getStatus() == 200) {
            ToastUtil.makeShortText(this, "报名成功");
            btnLogin.setEnabled(false);
            btnLogin.setText("已报名");
            mPresentser.getexamDetail(jsonObject.toString());
        } else if (baominginfoBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        }else {
            ToastUtil.makeShortText(this, baominginfoBean.getHint());
        }
    }

    @Override
    public void setgoExam(PracticeZuoBean practiceZuoBean) {

    }

    @Override
    public void setexamSubmit(BaominginfoBean baominginfoBean) {

    }

    @Override
    public void setexamTimes(BaominginfoBean baominginfoBean, String s) {

    }

    @Override
    public void setexam(MyExamBean myExamBean) {

    }

    @Override
    public void setscorequery(AcoreQueryBean acoreQueryBean) {

    }

    @Override
    public void setexamAnalysis(ExamAnalysisBean examAnalysisBean) {

    }

    @Override
    protected ExaminationPresenter<ExaminationMvp.Examination_View> createPresenter() {
        return new ExaminationPresenter<>();
    }

    @Override
    protected void initData() throws ParseException {
        id = getIntent().getIntExtra("id", 0);
        jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("oenId", id);


            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresentser.getexamDetail(jsonObject.toString());
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_baoming;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    public static String getSecondToDayHourMinutes(long ms, boolean isSign) {

        int ss = 1000;

        int mi = ss * 60;

        int hh = mi * 60;

        int dd = hh * 24;

        long day = ms / dd;

        long hour = (ms - day * dd) / hh;

        long minute = (ms - day * dd - hour * hh) / mi;

        long second = (ms - day * dd - hour * hh - minute * mi) / ss;


        String strDay = day < 10 ? "0" + day : "" + day;

        String strHour = hour < 10 ? "0" + hour : "" + hour;

        String strMinute = minute < 10 ? "0" + minute : "" + minute;


        StringBuilder stringBuilder = new StringBuilder();
        if (isSign) {
            stringBuilder.append("<font color='#000000' >距离考试开始</font>").append("<font color='#ffffff' >").append(strDay).append("</font><font color='#000000'>天</font>").append("<font color='#ffffff' >").append(strHour)
                    .append("</font><font color='#000000' >小时</font>").append("<font color='#ffffff' >").append(strMinute).append("</font><font color='#000000' >分钟</font>");
        } else {
            stringBuilder.append("<font color='#000000' >距离报名结束</font>").append("<font color='#ffffff' >").append(strDay).append("</font><font color='#000000'>天</font>").append("<font color='#ffffff' >").append(strHour)
                    .append("</font><font color='#000000' >小时</font>").append("<font color='#ffffff' >").append(strMinute).append("</font><font color='#000000' >分钟</font>");
        }


        return stringBuilder.toString();

    }


    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
                    jsonObject.put("token", SharedPreferenceUtils.getToken());
                    jsonObject.put("oenId", id);
                    jsonObject.put("version", MyApp.version);
                    jsonObject.put("device", "Android");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mPresentser.getexamsing(jsonObject.toString());
                break;
        }
    }


}
