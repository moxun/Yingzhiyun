package com.yingzhiyun.yingquxue.activity.homepagr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubmintSuceesActivity extends SimpleActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.hint)
    TextView hint;
    @BindView(R.id.back_home)
    TextView backHome;

    @Override
    protected void initData() throws ParseException {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("在线考试已结束，系统将自动评分生成成绩报告可以前往").append("<font color='#7D7DFF' size='24'>“我的”</font>").append("成绩报告中查看，请同学们耐心等待");
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_submitsucces;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick(R.id.back_home)
    public void onViewClicked() {
        Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
