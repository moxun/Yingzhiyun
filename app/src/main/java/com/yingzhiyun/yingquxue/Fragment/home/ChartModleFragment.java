package com.yingzhiyun.yingquxue.Fragment.home;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.StudentinfoBean;
import com.yingzhiyun.yingquxue.OkBean.TestBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.QueryscoreActivity;
import com.yingzhiyun.yingquxue.activity.score.MineScoreActivity;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartModel;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartView;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAMoveOverEventMessageModel;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AASeriesElement;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartEnum.AAChartType;
import com.yingzhiyun.yingquxue.units.MD5Util;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChartModleFragment extends SimpleFragment  implements AAChartView.AAChartViewCallBack{
    private final String type;
    private final StudentinfoBean testBean;
    @BindView(R.id.AAChartView)
    AAChartView AAChartView;
    private List<String> batchname=new ArrayList<>();
    private List<Object> batchscore=new ArrayList<>();
    private List<Object> classRank=new ArrayList<>();
    private List<Object> gradeRank=new ArrayList<>();
    private List<Object> batchRank=new ArrayList<>();
    private String[] stringName;
    private Object[] objectscore;
    private Object[] objectClassrank;
    private Object[] objectgradeRank;
    private Object[] objectbatchRank;
    public ChartModleFragment(String i,StudentinfoBean bean) {
        super();
        this.type=i;
        this.testBean=bean;
    }



    @Override
    public int createLayoutId() {
        return R.layout.fragment_chartmodle;
    }

    @Override
    protected void initData() throws JSONException {
//        StringBuilder stringBuilder = null;
//        try {
//            InputStream is = getClass().getClassLoader().
//                    getResourceAsStream("assets/"+"province.json");
//            InputStreamReader streamReader = new InputStreamReader(is);
//            BufferedReader reader  = new BufferedReader(streamReader);
//            String line;
//            stringBuilder = new StringBuilder();
//            while ((line = reader.readLine()) != null){
//                stringBuilder.append(line);
//            }
//            reader.close();
//            reader.close();
//            is.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        TestBean testBean = new Gson().fromJson(stringBuilder.toString(), TestBean.class);
        for (int i = 0; i < testBean.getBatchList().size(); i++) {
            StudentinfoBean.BatchListBean batchListBean = testBean.getBatchList().get(i);
            batchname.add(batchListBean.getName());
            batchscore.add(batchListBean.getScore());
            classRank.add(batchListBean.getClassRank());
            gradeRank.add(batchListBean.getGradeRank());
            batchRank.add(batchListBean.getBatchRank());
        }
        stringName = batchname.toArray(new String[batchname.size()]);
        objectscore = batchscore.toArray(new Object[batchscore.size()]);
        objectClassrank = classRank.toArray(new Object[classRank.size()]);
        objectgradeRank=gradeRank.toArray(new Object[gradeRank.size()]);
        objectbatchRank=batchRank.toArray(new Object[batchRank.size()]);
        switch (type) {
            case "总成绩":

                AAChartModel aaChartModel = new AAChartModel()
                .chartType(AAChartType.Line)
                .xAxisVisible(true)
                .yAxisVisible(true)
                .title("")
                .yAxisTitle("")
                .backgroundColor("#ffffff")
                .yAxisLineWidth((float) 1)
                .dataLabelsEnabled(true)
                .colorsTheme(new Object[]{"#7AC5FF"})
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("总成绩")
                                .data(objectscore),

                })
                .categories(stringName);
                AAChartView.callBack=this;
                aaChartModel.markerSymbolStyle(AAChartSymbolStyleType.BorderBlank);
                AAChartView.aa_drawChartWithChartModel(aaChartModel);
                break;
            case "班级排名":
                AAChartModel aaChartModel1 = new AAChartModel()
                        .chartType(AAChartType.Line)
                        .xAxisVisible(true)
                        .yAxisVisible(true)
                        .title("")
                        .yAxisTitle("")
                        .backgroundColor("#ffffff")
                        .yAxisLineWidth((float) 1)
                        .dataLabelsEnabled(true)
                        .colorsTheme(new Object[]{"#7AC5FF"})
                        .series(new AASeriesElement[]{
                                new AASeriesElement()
                                        .name("班级排名")
                                        .data(objectClassrank),

                        })
                        .categories(stringName);
                AAChartView.callBack=this;
                aaChartModel1.markerSymbolStyle(AAChartSymbolStyleType.BorderBlank);
                AAChartView.aa_drawChartWithChartModel(aaChartModel1);
                break;
            case "年级排名":
                AAChartModel gradeChartModel = new AAChartModel()
                        .chartType(AAChartType.Line)
                        .xAxisVisible(true)
                        .yAxisVisible(true)
                        .title("")
                        .yAxisTitle("")
                        .backgroundColor("#ffffff")
                        .yAxisLineWidth((float) 1)
                        .dataLabelsEnabled(true)
                        .colorsTheme(new Object[]{"#7AC5FF"})
                        .series(new AASeriesElement[]{
                                new AASeriesElement()
                                        .name("年级排名")
                                        .data(objectgradeRank),

                        })
                        .categories(stringName);
                AAChartView.callBack=this;
                gradeChartModel.markerSymbolStyle(AAChartSymbolStyleType.BorderBlank);
                AAChartView.aa_drawChartWithChartModel(gradeChartModel);
                break;
            case "联考排名":
                AAChartModel batchChartModel = new AAChartModel()
                        .chartType(AAChartType.Line)
                        .xAxisVisible(true)
                        .yAxisVisible(true)
                        .title("")
                        .yAxisTitle("")
                        .backgroundColor("#ffffff")
                        .yAxisLineWidth((float) 1)
                        .dataLabelsEnabled(true)
                        .colorsTheme(new Object[]{"#7AC5FF"})
                        .series(new AASeriesElement[]{
                                new AASeriesElement()
                                        .name("联考排名")
                                        .data(objectbatchRank),

                        })
                        .categories(stringName);
                AAChartView.callBack=this;
                batchChartModel.markerSymbolStyle(AAChartSymbolStyleType.BorderBlank);
                AAChartView.aa_drawChartWithChartModel(batchChartModel);
                break;
            default:
                break;
        }

    }

    @Override
    public void chartViewDidFinishedLoad(com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartView aaChartView) {

    }

    @Override
    public void chartViewMoveOverEventMessage(com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartView aaChartView, AAMoveOverEventMessageModel messageModel) {

    }

}
