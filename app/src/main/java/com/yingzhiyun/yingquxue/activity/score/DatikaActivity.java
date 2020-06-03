package com.yingzhiyun.yingquxue.activity.score;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatikaActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.datika)
    ImageView datika;
    @BindView(R.id.answer)
    ImageView answer;

    @Override
    protected void initData() throws ParseException {
        toolTitle.setText("答题卡及解析");
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_datika;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.datika, R.id.answer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                break;
            case R.id.datika:
                break;
            case R.id.answer:
                break;
        }
    }
}
