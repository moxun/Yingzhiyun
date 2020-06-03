package com.yingzhiyun.yingquxue.activity.tiku;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.ExamineMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ExamineJson;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.ExamineAdapter;
import com.yingzhiyun.yingquxue.adapter.WenExaminAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ExaminePresenter;
import com.yingzhiyun.yingquxue.units.SerializableHashMap;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
文科答题卡

 */
public class WenExamineActivity extends BaseActicity<ExamineMvp.Examine_View, ExaminePresenter<ExamineMvp.Examine_View>>
        implements ExamineMvp.Examine_View {
    @BindView(R.id.finish)
    ImageView finish;

    @BindView(R.id.recy_answer)
    RecyclerView recyAnswer;
    @BindView(R.id.all_jiexi)
    TextView allJiexi;
    @BindView(R.id.wrong_jiexi)
    TextView wrongJiexi;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private int id;
    private String time;

    public static WenExamineActivity instance = null;
    private ExamineBean beam;

    @Override
    public void setExamineBean(ExamineBean examineBean) {
        Log.d("moxun", "setExamineBean: " + examineBean.getHint());
        beam =examineBean;

        WenExaminAdapter examineAdapter = new WenExaminAdapter(examineBean.getResult().getDaTiBeanList(), this,"ex");
        recyAnswer.setLayoutManager(new LinearLayoutManager(this));
        recyAnswer.setAdapter(examineAdapter);
    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {

    }

    @Override
    protected ExaminePresenter<ExamineMvp.Examine_View> createPresenter() {
        return new ExaminePresenter<>();
    }

    @Override
    protected void initData() {
        instance = this;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        time = intent.getStringExtra("time");
        SerializableHashMap serializableHashMap = (SerializableHashMap) intent.getExtras().get("map");
        LinkedHashMap<Integer, String> map = serializableHashMap.getMap();
        Log.d("moxun", "initData: " + map.size());
        List<ExamineJson.DetailBean> detailBeans = new ArrayList<>();

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Integer key = (Integer) entry.getKey();
            String value = (String) entry.getValue();
            detailBeans.add(new ExamineJson.DetailBean(0, 0, key + "", value));
        }
        String s = new Gson().toJson(new ExamineJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), id
                , SharedPreferenceUtils.getsubject_id(), time, detailBeans, MyApp.version,"Android"));

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_examine;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.all_jiexi, R.id.wrong_jiexi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.all_jiexi:
                startActivity(new Intent(this,WenAnalysisActivity.class).putExtra("bean",beam).putExtra("type","all"));
                break;
            case R.id.wrong_jiexi:
                startActivity(new Intent(this,WenAnalysisActivity.class).putExtra("bean",beam).putExtra("type","wrong"));
                break;
        }
    }
}
