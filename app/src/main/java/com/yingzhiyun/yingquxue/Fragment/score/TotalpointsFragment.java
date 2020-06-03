package com.yingzhiyun.yingquxue.Fragment.score;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.AverageBean;
import com.yingzhiyun.yingquxue.OkBean.FullscoreBean;
import com.yingzhiyun.yingquxue.OkBean.RankTypeBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.AverageAdapter;
import com.yingzhiyun.yingquxue.adapter.RankTypeAdapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;
import com.yingzhiyun.yingquxue.units.CircleProgressBar;
import com.yingzhiyun.yingquxue.units.RadarMapData;
import com.yingzhiyun.yingquxue.units.RadarMapView;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;

public class TotalpointsFragment extends SimpleFragment {
    private final FullscoreBean bean;
    @BindView(R.id.fenshu)
    TextView fenshu;
    @BindView(R.id.cp)
    CircleProgressBar cp;
    @BindView(R.id.all_fen)
    TextView allFen;
    @BindView(R.id.manfen)
    TextView manfen;
    @BindView(R.id.all_score)
    TextView allScore;
    @BindView(R.id.text_score)
    TextView textScore;
    @BindView(R.id.recy_ranktype)
    RecyclerView recyRanktype;
    @BindView(R.id.recy_average)
    RecyclerView recyAverage;
    @BindView(R.id.radar_map)
    RadarMapView radarMap;
    @BindView(R.id.defen)
    TextView defen;
    @BindView(R.id.youshi_sub)
    TextView youshiSub;
    @BindView(R.id.lieshi_text)
    TextView lieshiText;
    @BindView(R.id.lieshi_sub)
    TextView lieshiSub;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    private RadarMapData radarMapData;
    private ArrayList<RankTypeBean> rankTypeBeans;
    private ArrayList<AverageBean> averageBeans;

    public TotalpointsFragment(FullscoreBean fullscoreBean) {
        super();
        this.bean = fullscoreBean;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_totalpoints;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() throws JSONException {
        cp.setMaxProgress(bean.getFullScore());
        cp.setProgress(bean.getDoScore());
        allScore.setText(bean.getFullScore() + "");
        defen.setText(bean.getDoScore()+"");
        allFen.setText(bean.getFullScore() + "");
        rankTypeBeans = new ArrayList<>();
        rankTypeBeans.add(new RankTypeBean(bean.getClassRank()+"", "/"+bean.getClassStudent(), "班级排名"));
        rankTypeBeans.add(new RankTypeBean(bean.getGradeRank()+"", "/"+bean.getGradeStudent(), "年级排名"));
        rankTypeBeans.add(new RankTypeBean(bean.getBatchRank()+"", "/"+bean.getBatchStudent(), "联考排名"));
        RankTypeAdapter rankTypeAdapter = new RankTypeAdapter(rankTypeBeans);
        recyRanktype.setLayoutManager(new GridLayoutManager(context, 3));
        recyRanktype.setAdapter(rankTypeAdapter);

        averageBeans = new ArrayList<>();
        averageBeans.add(new AverageBean(bean.getDoScore(), bean.getClassAvg(), "班级平均分"));
        averageBeans.add(new AverageBean(bean.getDoScore(), bean.getGradeAvg(), "年级平均分"));
        averageBeans.add(new AverageBean(bean.getDoScore(), bean.getBatchRank(), "联考平均分"));
        AverageAdapter averageAdapter = new AverageAdapter(averageBeans);
        recyAverage.setLayoutManager(new LinearLayoutManager(context));
        recyAverage.setAdapter(averageAdapter);
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Double> doubles = new ArrayList<>();
        for (int i = 0; i < bean.getGradeList().size(); i++) {
            strings.add(bean.getGradeList().get(i).getSubjectName());
            doubles.add((double) bean.getGradeList().get(i).getDoScore());
        }
        String[] strings1 = strings.toArray(new String[strings.size()]);
        Double[] objects = doubles.toArray(new Double[doubles.size()]);
        radarMapData = new RadarMapData();
        radarMapData.setCount(bean.getGradeList().size());
        radarMapData.setMainPaintColor(Color.rgb(16, 145, 233));
        radarMapData.setTitles(strings1);
        radarMapData.setValuse(objects);
        radarMapData.setTextSize(30);
        radarMap.setData(radarMapData);

        Double max = Collections.max(doubles);
        Double min = Collections.min(doubles);
        for (int i = 0; i < bean.getGradeList().size(); i++) {
           if( (double)bean.getGradeList().get(i).getDoScore()==max){
               youshiSub.setText(bean.getGradeList().get(i).getSubjectName());
           }
           if((double)bean.getGradeList().get(i).getDoScore()==min){
               youshiSub.setText(bean.getGradeList().get(i).getSubjectName());
           }
        }
    }
}