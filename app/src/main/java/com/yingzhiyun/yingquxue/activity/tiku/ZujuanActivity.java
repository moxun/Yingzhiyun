package com.yingzhiyun.yingquxue.activity.tiku;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.ZujianMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.BankCombinationJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.KnowledgeJson;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.KnowledgeBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.OkBean.TestPaperBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.QTYPEADAPTER;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ZujuanPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class ZujuanActivity extends BaseActicity<ZujianMvp.Zujian_View, ZujuanPresenter<ZujianMvp.Zujian_View>> implements ZujianMvp.Zujian_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tv_know)
    TextView tvKnow;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;

    @BindView(R.id.recy_qtypes)
    RecyclerView recyQtypes;
    @BindView(R.id.right_choose)
    TextView rightChoose;
    private ArrayList<KnowledgeBean.ResultBean.QuestionTypeBean> questionTypeBeans;
    private QTYPEADAPTER qtypeadapter;
    private ArrayList<Integer> integers;
    private List<KnowledgeBean.ResultBean.KnowledgePointsListBean> knowledgePointsList;
    private int id;
    private ArrayList<String> strings;
    private ArrayList<KnowPointerBean.ResultBean.ListBeanXX.ListBeanX> chooseBooks;
    private int type;
    public static ZujuanActivity instance = null;
    private String type1;

    @Override
    public void setKnowledge(KnowledgeBean knowledge) {
        Log.d("mxun", "setKnowledge: " + knowledge.getResult().getQuestionType().size());
        if (knowledge.getStatus() == 200) {
            if (knowledge.getResult().getKnowledgePointsList() != null) {
                knowledgePointsList = knowledge.getResult().getKnowledgePointsList();

            }

            questionTypeBeans.addAll(knowledge.getResult().getQuestionType());
            qtypeadapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setTestPaper(TestPagperInfo testPaper) {
        rightChoose.setEnabled(true);
        if (testPaper.getResult().getDaTiBeanList() != null) {

            startActivity(new Intent(ZujuanActivity.this, TestPagperinfoActivity.class).putExtra("bean", testPaper).putExtra("juantype", "userTestPaperCheck"));
        } else {
            ToastUtil.makeLongText(this, "暂无试卷");
        }

    }

    @Override
    protected ZujuanPresenter<ZujianMvp.Zujian_View> createPresenter() {
        return new ZujuanPresenter<>();
    }

    @Override
    protected void initData() {
        instance = this;
        id = getIntent().getExtras().getInt("id");
        type1 = getIntent().getExtras().getString("type");
        chooseBooks = (ArrayList<KnowPointerBean.ResultBean.ListBeanXX.ListBeanX>) getIntent().getExtras().getSerializable("list");
        Log.d("moxun", new Gson().toJson(new KnowledgeJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), id + "", MyApp.version, "Android")));

        mPresentser.getKnowledge(new Gson().toJson(new KnowledgeJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), id + "", MyApp.version, "Android")));
        questionTypeBeans = new ArrayList<>();
        qtypeadapter = new QTYPEADAPTER(questionTypeBeans, this);
        recyQtypes.setLayoutManager(new LinearLayoutManager(this));

        recyQtypes.setItemViewCacheSize(20);
        recyQtypes.setAdapter(qtypeadapter);
        integers = new ArrayList<>();
        strings = new ArrayList<>();
        strings.add("易");
        strings.add("中");
        strings.add("高");
        idFlowlayout.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_flow,
                        idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_zujuan;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @OnClick({R.id.finish, R.id.right_choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.right_choose:
                qtypeadapter.getId();
                integers.clear();
                if (chooseBooks.size() > 0) {
                    for (int i = 0; i < chooseBooks.size(); i++) {
                        Log.d("moxun", "onViewClicked: "+chooseBooks.get(i).getId());
                        integers.add(chooseBooks.get(i).getId());
                    }
                }
                if (type1 != null) {
                    Set<Integer> selectedList = idFlowlayout.getSelectedList();
                    if (selectedList.size() > 0) {
                        Integer next = selectedList.iterator().next();

                        type = next + 1;
                        rightChoose.setEnabled(false);
                        Log.d("moxun", "onViewClicked: "+new Gson().toJson(new BankCombinationJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), type + "", id + "", qtypeadapter.detailBeans, integers, MyApp.version, "Android")));
                        mPresentser.getTestPaper(new Gson().toJson(new BankCombinationJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), type + "", id + "", qtypeadapter.detailBeans, integers, MyApp.version, "Android")));
                    } else {
                        ToastUtil.makeShortText(this, "请选择难易程度");
                    }
                } else {
                    if (qtypeadapter.detailBeans.size() > 0) {

                        Set<Integer> selectedList = idFlowlayout.getSelectedList();
                        if (selectedList.size() > 0) {
                            Integer next = selectedList.iterator().next();

                            type = next + 1;
                            rightChoose.setEnabled(false);
                            Log.d("moxun", "onViewClicked: "+new Gson().toJson(new BankCombinationJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), type + "", id + "", qtypeadapter.detailBeans, integers, MyApp.version, "Android")));
                            mPresentser.getTestPaper(new Gson().toJson(new BankCombinationJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), type + "", id + "", qtypeadapter.detailBeans, integers, MyApp.version, "Android")));

                        } else {
                            ToastUtil.makeShortText(this, "请选择难易程度");
                        }

                    } else {
                        ToastUtil.makeShortText(this, "请至少选择一种题型");
                    }
                }



                break;
        }
    }
}
