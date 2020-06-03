package com.yingzhiyun.yingquxue.activity.tiku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.ExamineMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ExamineJson;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.adapter.ExamineAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ExaminePresenter;
import com.yingzhiyun.yingquxue.units.SerializableHashMap;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamineActivity extends BaseActicity<ExamineMvp.Examine_View, ExaminePresenter<ExamineMvp.Examine_View>>
        implements ExamineMvp.Examine_View {

    @BindView(R.id.finish)
    ImageView finish;

    @BindView(R.id.recy_answer)
    RecyclerView recyAnswer;
    @BindView(R.id.all_jiexi)
    TextView allJiexi;
    @BindView(R.id.wrong_jiexi)
    TextView wrongJiexi;

    private int id;
    private String time;

    public static ExamineActivity instance = null;
    private ExamineBean bean;
    private String juantype;
    private ExamineBean bean1;

    @SuppressLint("SetTextI18n")
    @Override
    public void setExamineBean(ExamineBean examineBean) throws ParseException {

    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {

    }
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }
    @Override
    protected ExaminePresenter<ExamineMvp.Examine_View> createPresenter() {
        return new ExaminePresenter<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ZujuanActivity.instance!=null){
            ZujuanActivity.instance.finish();
            if(Chooseknowledge.instance!=null){
                Chooseknowledge.instance.finish();
                CelectBookActivity.instance.finish();
            }

            TestPagperinfoActivity.instance.finish();
        }
        DatiKaActivity.instance.finish();
        ZuTiActivity.instance.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Intent intent = new Intent();
//        intent.putExtra("id", SharedPreferenceUtils.getsubject_id());
//        intent.setClass(this, CelectBookActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳到的界面
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//它可以关掉所要到的界面中间的activity
//        startActivity(intent);

    }

    @Override
    protected void initData() throws ParseException {
        instance=this;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        time = intent.getStringExtra("time");
        bean1 = (ExamineBean) intent.getSerializableExtra("bean");

        ExamineAdapter examineAdapter = new ExamineAdapter(bean1.getResult().getDaTiBeanList(), this,"ex");
        recyAnswer.setLayoutManager(new LinearLayoutManager(this));
        recyAnswer.setAdapter(examineAdapter);

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");






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
                if(ZujuanActivity.instance!=null){
                    ZujuanActivity.instance.finish();
                    Chooseknowledge.instance.finish();
                    CelectBookActivity.instance.finish();
                    TestPagperinfoActivity.instance.finish();
                }
                DatiKaActivity.instance.finish();
                ZuTiActivity.instance.finish();
                finish();
                break;
            case R.id.all_jiexi:
                startActivity(new Intent(this,AnalysisActivity.class).putExtra("bean",bean1).putExtra("type","all"));
                break;
            case R.id.wrong_jiexi:
                startActivity(new Intent(this,AnalysisActivity.class).putExtra("bean",bean1).putExtra("type","wrong"));
            break;
        }
    }
}
