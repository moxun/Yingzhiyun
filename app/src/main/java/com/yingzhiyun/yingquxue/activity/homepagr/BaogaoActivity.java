package com.yingzhiyun.yingquxue.activity.homepagr;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.ExaminationMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.BaogaoItem;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.BaoGaoAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ExaminationPresenter;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartModel;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartView;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AASeriesElement;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartEnum.AAChartType;
import com.yingzhiyun.yingquxue.units.CircleProgressBar;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaogaoActivity extends BaseActicity<ExaminationMvp.Examination_View, ExaminationPresenter<ExaminationMvp.Examination_View>>
        implements ExaminationMvp.Examination_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
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
    @BindView(R.id.defen)
    TextView defen;
    @BindView(R.id.recy_ranktype)
    RecyclerView recyRanktype;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.text_1)
    TextView text1;
    @BindView(R.id.text_2)
    TextView text2;
    @BindView(R.id.text_3)
    TextView text3;
    @BindView(R.id.AAChartView)
    com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartView AAChartView;
    @BindView(R.id.Chartview)
    com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartView Chartview;
    @BindView(R.id.zhanbi)
    TextView zhanbi;
    @BindView(R.id.text_4)
    TextView text4;
    @BindView(R.id.text_5)
    TextView text5;
    private AAChartModel aaChartModel;
    private int id;
    private ArrayList<BaogaoItem> baogaoItems;
    private JSONObject jsonObject;
    private BaoGaoAdapter baoGaoAdapter;
    private float v;

    @Override
    protected void initData() throws ParseException {

        id = getIntent().getIntExtra("id", 0);
        baogaoItems = new ArrayList<>();

        baoGaoAdapter = new BaoGaoAdapter(baogaoItems);
        recyRanktype.setLayoutManager(new GridLayoutManager(this, 2));
        recyRanktype.setAdapter(baoGaoAdapter);


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
        mPresentser.getscorequery(jsonObject.toString());
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_baogao;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @Override
    protected ExaminationPresenter<ExaminationMvp.Examination_View> createPresenter() {
        return new ExaminationPresenter<>();
    }



    AAChartModel configurePieChart(Object[][] objects) {
        return new AAChartModel()
                .chartType(AAChartType.Pie)

                .backgroundColor("#ffffff")
                .title("")
                .dataLabelsEnabled(false)//是否直接显示扇形图数据
                .yAxisTitle("℃")
                .legendEnabled(false)
                .series(new AASeriesElement[]{
                                new AASeriesElement()
                                        .name("数量")

                                        .data(objects)
                                ,
                        }
                );
    }

    AAChartModel configureColumnChart(String[] strings, Object[] objects) {
        return new AAChartModel()
                .chartType(AAChartType.Column)
                .polar(true)
                .dataLabelsEnabled(false)
                .legendEnabled(false)
                .title("")
                .xAxisGridLineWidth((float) 1)
                .categories(strings)
                .series(new AASeriesElement[]{
                                new AASeriesElement()
                                        .type(AAChartType.Line)
                                        .name("数量")
                                        .data(objects)
                        }

                );
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
    public void setgoExam(PracticeZuoBean practiceZuoBean) {

    }

    @Override
    public void setexamSubmit(BaominginfoBean baominginfoBean) {

    }

    @Override
    public void setexamTimes(BaominginfoBean baominginfoBean, String type) {

    }

    @Override
    public void setexam(MyExamBean myExamBean) {

    }

    @Override
    public void setscorequery(AcoreQueryBean acoreQueryBean) {
        if (acoreQueryBean.getStatus() == 200) {
            AcoreQueryBean.ResultBean scorebean = acoreQueryBean.getResult();
            if(acoreQueryBean.getHint().equals("该结果暂时无法查询")){
                finish();
                ToastUtil.makeShortText(this,"考试排名中，请稍后");
                return;
            }

            cp.setMaxProgress(Integer.parseInt(scorebean.getScore()));



            v = Float.parseFloat(scorebean.getUserScore());
            cp.setProgress((int) v);
            allScore.setText(scorebean.getScore());
            allFen.setText(scorebean.getUserScore());
            defen.setText(scorebean.getUserScore());
            baogaoItems.add(new BaogaoItem("<font color='#333333' size='32'>" + scorebean.getRank() + "</font><font color='#CCCCCC' size='10'>/" + scorebean.getTotal() + "</font>", "联考排名"));
            baogaoItems.add(new BaogaoItem("<font color='#333333' size='32'>" + scorebean.getAvgScore() + "</font><font color='#CCCCCC' size='10'>分</font>", "联考平均分"));
            baogaoItems.add(new BaogaoItem("<font color='#333333' size='32'>" + scorebean.getTime() + "</font><font color='#CCCCCC' size='10'>分钟</font>", "考试时长"));
            baogaoItems.add(new BaogaoItem("<font color='#333333' size='32'>" + scorebean.getAvgTime() + "</font><font color='#CCCCCC' size='10'>分钟</font>", "考试平均时长"));
            baoGaoAdapter.notifyDataSetChanged();
            text1.setText(Html.fromHtml("<font color='#333333' size='28'>考试用时:</font><font color='#DB253B' size='28'>" + scorebean.getUserTime() + "</font><font color='#333333' size='28'>分钟   联考平均用时：</font>" + "<font color='#DB253B' size='28'>" + scorebean.getAvgTime() + "</font><font color='#333333' size='32'>分钟</font>"));
            text2.setText(Html.fromHtml("<font color='#333333' size='28'>本次联考总人数</font><font color='#DB253B' size='28'>" + scorebean.getTotal() + "</font><font color='#333333' size='28'>人   你在联考中的名次为</font>" + "<font color='#DB253B' size='28'>" + scorebean.getRank() + "</font>"));
            text3.setText(Html.fromHtml("<font color='#333333' size='28'>联考平均分为</font><font color='#DB253B' size='28'>" + scorebean.getAvgScore() + "</font><font color='#333333' size='28'>分 </font>"));
            if (scorebean.getKptMapList().size() > 0) {
                Object[][] objects = new Object[scorebean.getKptMapList().size()][scorebean.getKptMapList().size()];
                for (int i = 0; i < scorebean.getKptMapList().size(); i++) {
                    Object[] objects1 = new Object[2];
                    objects1[0] = scorebean.getKptMapList().get(i).getName();
                    objects1[1] = scorebean.getKptMapList().get(i).getCount();
                    objects[i] = objects1;
                }
                aaChartModel = configurePieChart(objects);
                AAChartView.aa_drawChartWithChartModel(aaChartModel);
            } else {
                Object[][] objects = new Object[scorebean.getQteMapList().size()][scorebean.getQteMapList().size()];
                for (int i = 0; i < scorebean.getQteMapList().size(); i++) {
                    Object[] objects1 = new Object[2];
                    objects1[0] = scorebean.getQteMapList().get(i).getName();
                    objects1[1] = scorebean.getQteMapList().get(i).getCount();
                    objects[i] = objects1;
                }
                aaChartModel = configurePieChart(objects);
                AAChartView.aa_drawChartWithChartModel(aaChartModel);
            }


            if (scorebean.getKptMapList().size() > 0) {
                String[] strings = new String[scorebean.getKptMapList().size()];
                Object[] objects = new Object[scorebean.getKptMapList().size()];
                for (int i = 0; i < scorebean.getKptMapList().size(); i++) {
                    strings[i] = scorebean.getKptMapList().get(i).getName();
                    objects[i] = scorebean.getKptMapList().get(i).getRight() / scorebean.getKptMapList().get(i).getCount();
                }
                AAChartModel aaChartModel = configureColumnChart(strings, objects);
                Chartview.aa_drawChartWithChartModel(aaChartModel);
            } else {
                String[] strings = new String[scorebean.getQteMapList().size()];
                Object[] objects = new Object[scorebean.getQteMapList().size()];
                for (int i = 0; i < scorebean.getQteMapList().size(); i++) {
                    strings[i] = scorebean.getQteMapList().get(i).getName();
                    objects[i] = scorebean.getQteMapList().get(i).getRight() / scorebean.getQteMapList().get(i).getCount();
                }
                AAChartModel aaChartModel = configureColumnChart(strings, objects);
                Chartview.aa_drawChartWithChartModel(aaChartModel);
            }

            if( scorebean.getKptNum()==0){
                text4.setText(Html.fromHtml("<font color='#333333' size='28'>本次考试共:</font><font color='#DB253B' size='28'>" + scorebean.getQuestionNum() + "</font><font color='#333333' size='28'>道题"));
            }else {
                text4.setText(Html.fromHtml("<font color='#333333' size='28'>本次考试共:</font><font color='#DB253B' size='28'>" + scorebean.getQuestionNum() + "</font><font color='#333333' size='28'>道题，</font>" + "<font color='#DB253B' size='28'>" + scorebean.getKptNum() + "</font><font color='#333333' size='32'>个知识点</font>"));
            }


            if(scorebean.getWorstKpt()!=null){
                text5.setText(Html.fromHtml("<font color='#333333' size='28'>掌握较差的知识点是：</font><font color='#DB253B' size='28'>" + scorebean.getWorstKpt() + "</font>"));
            }else {
                text5.setText(Html.fromHtml("<font color='#333333' size='28'>掌握较差的知识点是：</font><font color='#DB253B' size='28'>" + "无" + "</font>"));
            }


        }
    }

    @Override
    public void setexamAnalysis(ExamAnalysisBean examAnalysisBean) {

    }


    @OnClick({R.id.finish, R.id.all_jiexi,R.id.wrong_jiexi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.all_jiexi:
                Bundle bundle = new Bundle();
                bundle.putString("type","all");
                bundle.putString("title","全部解析");
                bundle.putInt("id",id);
                startActivity(ExamAnalysisActivity.class,bundle);
                break;
            case R.id.wrong_jiexi:
                Bundle b = new Bundle();
                b.putString("type","false");
                b.putString("title","错题解析");
                b.putInt("id",id);
                startActivity(ExamAnalysisActivity.class,b);
                break;
        }
    }
}
