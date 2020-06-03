package com.yingzhiyun.yingquxue.activity.homepagr;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.ExaminationMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TijiaoJson;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.OkBean.localbean.PracChooseBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.tiku.DatiKaActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ExamineActivity;
import com.yingzhiyun.yingquxue.adapter.PracticeZuoAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ExaminationPresenter;
import com.yingzhiyun.yingquxue.units.ResizableImageView;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yk.big_picture_library.BigView;
import com.yk.big_picture_library.LoadNetImageCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;

public class PracticeZuoActivity extends BaseActicity<ExaminationMvp.Examination_View, ExaminationPresenter<ExaminationMvp.Examination_View>>
        implements ExaminationMvp.Examination_View, LoadNetImageCallBack {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.answer_card)
    TextView answerCard;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.handler)
    ImageButton handler;
    @BindView(R.id.viewpager)
    RecyclerView viewpager;
    @BindView(R.id.back_test)
    ImageView backTest;
    @BindView(R.id.datika)
    TextView datika;
    @BindView(R.id.jioajuan)
    TextView jioajuan;
    private ArrayList<PracticeZuoBean.ResultBean.ExamDetailBean> examDetailBeans;
    private PracticeZuoAdapter practiceZuoAdapter;
    private LinkedHashMap<PracticeZuoBean.ResultBean.ExamDetailBean, List<String>> integerStringLinkedHashMap = new LinkedHashMap<>();
    private CountDownTimer timer;
    private int id;
    private int time;
    private int alltime;
    private JSONObject jsonObject;
    private int width;
    private String title;


    @Override
    protected void initData() throws ParseException {
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        toolTitle.setText(title);
        examDetailBeans = new ArrayList<>();
        practiceZuoAdapter = new PracticeZuoAdapter(examDetailBeans, this);
        practiceZuoAdapter.setOnItemListener(new PracticeZuoAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos, PracChooseBean projectc, String s, String type, PracticeZuoBean.ResultBean.ExamDetailBean examineshowAdapter) {

                if (type.equals("RadioSelect")) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add(projectc.getKeys());
                    integerStringLinkedHashMap.put(examineshowAdapter, strings);
                } else {


                    String str2 = s.replace(" ", "");

                    List<String> list = Arrays.asList(str2.split(","));
                    integerStringLinkedHashMap.put(examineshowAdapter, list);
                }
            }
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        viewpager.setLayoutManager(staggeredGridLayoutManager);
        viewpager.setAdapter(practiceZuoAdapter);
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


//        backTest.setImage(R.mipmap.tttt);
//        //加载 InputStream
//        backTest.setImage(InputStream is)
//        //加载网络图片 callBack : 加载中的回调

        mPresentser.getgoExam(jsonObject.toString());
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_praczuo;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @Override
    public void setExaminationList(ExaminationListBean examineBean) {

    }

    @Override
    public void setexamDetail(BaominginfoBean baominginfoBean) {

    }

    @Override
    public void setexamsing(BaominginfoBean baominginfoBean) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresentser.getexamTimes(jsonObject.toString(),"shuaixin");
    }
    @Override
    public void setgoExam(PracticeZuoBean practiceZuoBean) {

        if (practiceZuoBean.getStatus() == 200) {
            alltime = practiceZuoBean.getResult().getTime();

            timer = new CountDownTimer(alltime * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    String secondToDayHourMinutes = getTime((int) millisUntilFinished);

                    if (secondToDayHourMinutes.equals("00:05:00")) {
                        dialogHint("离考试结束还有5分钟", "确定").show();
                    }
                    answerCard.setText(secondToDayHourMinutes);
                }

                @Override
                public void onFinish() {
                    exSubmint();
                    timer.cancel();
                }
            };
            timer.start();
            viewpager.setItemViewCacheSize(practiceZuoBean.getResult().getExamDetail().size());
            examDetailBeans.addAll(practiceZuoBean.getResult().getExamDetail());
            practiceZuoAdapter.notifyDataSetChanged();
            Log.d("moxun", "setgoExam: "+width);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .fitCenter()
                    .override(width,practiceZuoBean.getResult().getImgHeight()+100);
            Glide.with(this)
                    .load(practiceZuoBean.getResult().getExamUrl())
                    .apply(new RequestOptions().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).placeholder(R.color.white).error(R.color.white).dontAnimate())
                    .into(backTest);
        } else if (practiceZuoBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setexamSubmit(BaominginfoBean baominginfoBean) {
        if (baominginfoBean.getStatus() == 200) {
            startActivity(SubmintSuceesActivity.class);
        } else {

        }
    }

    @Override
    public void setexamTimes(BaominginfoBean baominginfoBean,String type) {
        if (baominginfoBean.getStatus() == 200) {

            time = baominginfoBean.getResult().getTime();
            Log.d("==========", "setexamTimes: "+time);
            if(time==0){
                Iterator<Map.Entry<PracticeZuoBean.ResultBean.ExamDetailBean, List<String>>> iter = integerStringLinkedHashMap.entrySet().iterator();
                ArrayList<TijiaoJson.ExamCardBean.ExamDetailBean> examDetailBeans = new ArrayList<>();
                while (iter.hasNext()) {
                    Map.Entry<PracticeZuoBean.ResultBean.ExamDetailBean, List<String>> entry = iter.next();
                    PracticeZuoBean.ResultBean.ExamDetailBean key = entry.getKey();
                    List<String> value = entry.getValue();
                    examDetailBeans.add(new TijiaoJson.ExamCardBean.ExamDetailBean(key.getTh(), key.isMultiple(), key.getId() + "", key.getKeys(), value));
                }
                Log.d("moxun", "exSubmint: " + alltime);
                Log.d("moxun", "exSubmint: " + time);
                int i = alltime - time;

                TijiaoJson.ExamCardBean examCardBean = new TijiaoJson.ExamCardBean(examDetailBeans);
                TijiaoJson android = new TijiaoJson(examCardBean, SharedPreferenceUtils.getUserid(), i, SharedPreferenceUtils.getToken(), "Android", MyApp.version, id);
                String s = new Gson().toJson(android);

                mPresentser.getexamSubmit(s);
                return;
            }
            if(type.equals("submint")){
                Iterator<Map.Entry<PracticeZuoBean.ResultBean.ExamDetailBean, List<String>>> iter = integerStringLinkedHashMap.entrySet().iterator();
                ArrayList<TijiaoJson.ExamCardBean.ExamDetailBean> examDetailBeans = new ArrayList<>();
                while (iter.hasNext()) {
                    Map.Entry<PracticeZuoBean.ResultBean.ExamDetailBean, List<String>> entry = iter.next();
                    PracticeZuoBean.ResultBean.ExamDetailBean key = entry.getKey();
                    List<String> value = entry.getValue();
                    examDetailBeans.add(new TijiaoJson.ExamCardBean.ExamDetailBean(key.getTh(), key.isMultiple(), key.getId() + "", key.getKeys(), value));
                }
                Log.d("moxun", "exSubmint: " + alltime);
                Log.d("moxun", "exSubmint: " + time);
                int i = alltime - time;

                TijiaoJson.ExamCardBean examCardBean = new TijiaoJson.ExamCardBean(examDetailBeans);
                TijiaoJson android = new TijiaoJson(examCardBean, SharedPreferenceUtils.getUserid(), i, SharedPreferenceUtils.getToken(), "Android", MyApp.version, id);
                String s = new Gson().toJson(android);

                mPresentser.getexamSubmit(s);
            }else {
                timer.cancel();
                timer = new CountDownTimer(time * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        String secondToDayHourMinutes = getTime((int) millisUntilFinished);
                        if (secondToDayHourMinutes.equals("00:05:00")) {
                            dialogHint("离考试结束还有5分钟", "确定").show();
                        }
                        answerCard.setText(secondToDayHourMinutes);

                    }

                    @Override
                    public void onFinish() {
                        exSubmint();
                        timer.cancel();
                    }
                };
                timer.start();
            }

        }
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


    @OnClick({R.id.finish, R.id.jioajuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.jioajuan:

                dialogPrompt("确认交卷？", "", "交卷", "取消").show();
                break;
        }
    }

    @Override
    protected void dialogRightBtn() {
        super.dialogRightBtn();

        exSubmint();

    }

    private void exSubmint() {
        mPresentser.getexamTimes(jsonObject.toString(),"submint");

    }

    public static String getTime(int second) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(second);
        return hms;
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    @Override
    public void onLoadSucceed() {

    }

    @Override
    public void onLoadFail(Exception e) {

    }

    @Override
    public void onLoadProgress(int progress) {

    }


}
