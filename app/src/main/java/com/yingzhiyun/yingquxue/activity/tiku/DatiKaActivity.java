package com.yingzhiyun.yingquxue.activity.tiku;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Fragment.tiku.CelectFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.GapFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.TopicTypeFragment;
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
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatiKaActivity extends BaseActicity<ExamineMvp.Examine_View, ExaminePresenter<ExamineMvp.Examine_View>>
        implements ExamineMvp.Examine_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.recy_answer)
    RecyclerView recyAnswer;
    @BindView(R.id.btn_jiaojuan)
    TextView btnJiaojuan;
    private int id;
    private String time;
    public static DatiKaActivity instance = null;
    private ExamineBean bean;
    private LinkedHashMap<Integer, String> map;
    private boolean allAnswer = true;
    private boolean wenallAnswer = true;
    private String type;

    @Override
    protected void initData() {
        instance = this;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        time = intent.getStringExtra("time");
        type = intent.getStringExtra("type");
        String juantype = intent.getStringExtra("juantype");
        SerializableHashMap serializableHashMap = (SerializableHashMap) intent.getExtras().get("map");
        map = serializableHashMap.getMap();
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
        Log.d("-----------", "initData: "+s);
        mPresentser.getExamine(s,juantype);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_dati;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    public void setExamineBean(ExamineBean examineBean) {
        bean = examineBean;
        if (type.equals("math")) {
            ExamineAdapter examineAdapter = new ExamineAdapter(examineBean.getResult().getDaTiBeanList(), this, "card");
            recyAnswer.setLayoutManager(new LinearLayoutManager(this));
            recyAnswer.setAdapter(examineAdapter);
        } else {
            WenExaminAdapter examineAdapter = new WenExaminAdapter(examineBean.getResult().getDaTiBeanList(), this, "card");
            recyAnswer.setLayoutManager(new LinearLayoutManager(this));
            recyAnswer.setAdapter(examineAdapter);
        }

    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {

    }

    @Override
    protected ExaminePresenter<ExamineMvp.Examine_View> createPresenter() {
        return new ExaminePresenter<>();
    }

    @Override
    protected void dialogRightBtn() {
        super.dialogRightBtn();

        finish();
            startActivity(new Intent(DatiKaActivity.this, ExamineActivity.class).putExtra("id", id).putExtra("time", time
            ).putExtra("bean",bean));

    }

    @OnClick({R.id.finish, R.id.btn_jiaojuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_jiaojuan:
                if (bean != null) {
                    List<ExamineBean.ResultBean.DaTiBeanListBean> daTiBeanList = bean.getResult().getDaTiBeanList();

                        for (int i = 0; i < daTiBeanList.size(); i++) {
                            for (int j = 0; j < daTiBeanList.get(i).getStemBeanList().size(); j++) {
                                if (daTiBeanList.get(i).getStemBeanList().get(j).getStatus() != null) {
                                    if (daTiBeanList.get(i).getStemBeanList().get(j).getStatus().equals("empty")) {
                                        allAnswer = false;
                                    }
                                }
                            }
                        }
                        if (allAnswer) {
                            SerializableHashMap myMap = new SerializableHashMap();
                            myMap.setMap(map);//将hashmap数据添加到封装的myMap中
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("map", myMap);

                            finish();
                            startActivity(new Intent(DatiKaActivity.this, ExamineActivity.class).putExtra("id", id).putExtra("time", time
                            ).putExtra("bean",bean));
                        } else {
                            dialogPrompt("确认交卷？","本次卷子还有题目未作答","交卷","取消").show();
                        }

                }
                break;
        }
    }
}
