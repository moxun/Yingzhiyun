package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.localbean.TestBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.tiku.CelectBookActivity;
import com.yingzhiyun.yingquxue.activity.tiku.HudongActivity;
import com.yingzhiyun.yingquxue.activity.tiku.MyCollectionTiActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TestPaperListActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TiRecordActivirty;
import com.yingzhiyun.yingquxue.activity.tiku.WrongtitleActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ZjieTestActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ZujuanActivity;
import com.yingzhiyun.yingquxue.adapter.PatrolGroupAdapter;
import com.yingzhiyun.yingquxue.adapter.RecordAdapter;
import com.yingzhiyun.yingquxue.adapter.TestAdapter;
import com.yingzhiyun.yingquxue.base.adapter.expand.ExpandGroupItemEntity;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.TikuPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.view.PinnedHeaderItemDecoration;
import com.yingzhiyun.yingquxue.units.view.PinnedHeaderRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TikuFragment extends BaseFragment<TikuMvp.Tiku_View, TikuPresenter<TikuMvp.Tiku_View>> implements TikuMvp.Tiku_View {

    @BindView(R.id.tv_senior)
    TextView tvSenior;
    @BindView(R.id.tv_junior)
    TextView tvJunior;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.score_tab)
    TabLayout scoreTab;
    @BindView(R.id.recy_text)
    RecyclerView recyText;
    @BindView(R.id.recy_record)
    RecyclerView recyRecord;
    @BindView(R.id.item_image_record)
    ImageView itemImageRecord;
    @BindView(R.id.item_title)
    TextView itemTitle;
    @BindView(R.id.item_jiantou)
    ImageView itemJiantou;
    @BindView(R.id.hudong)
    RelativeLayout hudong;
    @BindView(R.id.toolbar_rela)
    RelativeLayout toolbarRela;
    private int height;
    private int width;
    private ArrayList<TestBean> testBeans;
    private TestAdapter testAdapter;
    private ArrayList<TestBean> recordBeans;
    private LayoutInflater mInflater;
    private List<AllsubjectBean.ResultBean.DetailBean> result;
    private PopupWindow popupWindow;
    private String Mname;
    public  ArrayList<KnowPointerBean.ResultBean.ListBeanXX.ListBeanX> listBeans = new ArrayList<>();
    private RecordAdapter recordAdapter;
    private AllsubjectBean bean;
    private Map<String, List<AllsubjectBean.ResultBean.DetailBean>> datas = new HashMap<>();
    private LinearLayoutManager mLayoutManager;
    private PatrolGroupAdapter mAdapter;
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> highsubject;
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> middlesubject;
    private int schooltype;
    private int subjectId;
    private String substring;
    @Override
    public int createLayoutId() {
        return R.layout.fragment_tiku;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        mInflater = getLayoutInflater();
        highsubject = new ArrayList<>();
        middlesubject = new ArrayList<>();
        presenter.getAllSubject(new Gson().toJson(new HomePagerJson("0", "", MyApp.version, "Android")));
        testBeans = new ArrayList<>();
        testBeans.add(new TestBean(R.mipmap.icon_zj, "章节练习", "配合视频学习效果更佳"));
        testBeans.add(new TestBean(R.mipmap.icon_zujuan, "手动组卷", "练习试题可预测分数哦~"));
        testBeans.add(new TestBean(R.mipmap.icon_moni, "模拟试题", "各高校权威模拟试题"));
        testAdapter = new TestAdapter(testBeans);
        testAdapter.OnsetOnClickListener(new TestAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(TestBean testBean, int position) {
                if (SharedPreferenceUtils.getisLogin()) {
                    if (position == 0) {

                        startActivity(new Intent(getContext(), ZjieTestActivity.class).putExtra("id", subjectId));
                    } else if (position == 1) {
                        if(substring.equals("语文")||substring.equals("英语")){
                            Bundle bundle = new Bundle();
                            bundle.putString("type",substring);
                            bundle.putSerializable("list",listBeans);
                            bundle.putInt("id",subjectId);
                            if(SharedPreferenceUtils.getisLogin()){
                                startActivity(ZujuanActivity.class,bundle);
                            }else{
                                startActivity(PwdLoginActivity.class);
                            }
                        }else {
                            startActivity(new Intent(getContext(), CelectBookActivity.class).putExtra("id", subjectId));
                        }

                    } else {
                        startActivity(new Intent(getContext(), TestPaperListActivity.class).putExtra("id", subjectId).putExtra("type",schooltype));
                    }
                } else {
                    startActivity(PwdLoginActivity.class);
                }
            }
        });
        recyText.setNestedScrollingEnabled(false);
        recyText.setLayoutManager(new LinearLayoutManager(getContext()));
        recyText.setAdapter(testAdapter);

        recordBeans = new ArrayList<>();
        recordBeans.add(new TestBean(R.mipmap.myfavou, "我的收藏", ""));
        recordBeans.add(new TestBean(R.mipmap.mistake, "错题本", ""));
        recordBeans.add(new TestBean(R.mipmap.record, "做题记录", ""));
        recordAdapter = new RecordAdapter(recordBeans);
        recyRecord.setNestedScrollingEnabled(false);
        recyRecord.setLayoutManager(new LinearLayoutManager(getContext()));
        recyRecord.setAdapter(recordAdapter);
        recordAdapter.OnsetOnClickListener(new RecordAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(TestBean testBean) {
                if (SharedPreferenceUtils.getisLogin()) {
                    if (testBean.getTitle().equals("错题本")) {

                        startActivity(new Intent(getContext(), WrongtitleActivity.class).putExtra("id", subjectId));
                    } else if (testBean.getTitle().equals("做题记录")) {
                        startActivity(TiRecordActivirty.class);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", subjectId);
                        startActivity(MyCollectionTiActivity.class, bundle);
                    }
                } else {
                    startActivity(PwdLoginActivity.class);
                }
            }
        });
        scoreTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if (schooltype == 1) {
                    subjectId = highsubject.get(position).getSubjectId();
                    substring=highsubject.get(position).getName();
                    SharedPreferenceUtils.setsubject_id(subjectId);
                } else {
                    subjectId = middlesubject.get(position).getSubjectId();
                    substring=middlesubject.get(position).getName();
                    SharedPreferenceUtils.setsubject_id(subjectId);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

//    private void showPopWindows() {
//        View view = mInflater.inflate(R.layout.layout_popupwindow, null);
//        PinnedHeaderRecyclerView mRecyclerView = view.findViewById(R.id.recy_subject);
//        mRecyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(getContext()));
//        mRecyclerView.addItemDecoration(new PinnedHeaderItemDecoration());
//        mAdapter = new PatrolGroupAdapter();
//        mAdapter.setData(obtainDataList());
//        mRecyclerView.setAdapter(mAdapter);
///**
// * 当标题栏被悬浮的时候的点击功能
// */
//        mRecyclerView.setOnPinnedHeaderClickListener(new PinnedHeaderRecyclerView.OnPinnedHeaderClickListener() {
//            @Override
//            public void onPinnedHeaderClick(int adapterPosition) {
//                mAdapter.switchExpand(adapterPosition);
//                //标题栏被点击之后，滑动到指定位置
//                mLayoutManager.scrollToPositionWithOffset(adapterPosition, 0);
//            }
//        });
//        mAdapter.OnsetOnClickListener(new PatrolGroupAdapter.setOnClickListener() {
//            @Override
//            public void setOnClickListener(AllsubjectBean.ResultBean.DetailBean musicBean, String name) {
//                Mname = name;
//                textSubject.setText(name);
//
//                subjectId = musicBean.getSubjectId();
//                SharedPreferenceUtils.setsubject_id(subjectId);
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        int popupWidth = view.getMeasuredWidth();
//        int popupHeight = view.getMeasuredHeight();
//        int[] location = new int[2];
//        // 允许点击外部消失
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
//        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//        lp.alpha = 0.4f;
//        getActivity().getWindow().setAttributes(lp);
//
//        // 获得位置
//        //设置popupWindow消失时的监听
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            //在dismiss中恢复透明度
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//                lp.alpha = 1f;
//                getActivity().getWindow().setAttributes(lp);
//            }
//        });
//        textSubject.getLocationOnScreen(location);
//        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
////				popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
//        popupWindow.showAsDropDown(textSubject);
//
//
//    }

    @Override
    public void setAllSubject(AllsubjectBean allSubject) {
        bean = allSubject;
        Mname = allSubject.getResult().get(0).getDetail().get(0).getName();

        for (int i = 0; i < allSubject.getResult().size(); i++) {
            if (allSubject.getResult().get(i).getName().equals("初中")) {
                middlesubject.addAll(allSubject.getResult().get(i).getDetail());
            } else {
                highsubject.addAll(allSubject.getResult().get(i).getDetail());
            }
        }


        for (int i = 0; i < highsubject.size(); i++) {
            scoreTab.addTab(scoreTab.newTab().setText(highsubject.get(i).getName()));
        }

        subjectId = highsubject.get(0).getSubjectId();
        substring=highsubject.get(0).getName();
        schooltype = 1;
        SharedPreferenceUtils.setsubject_id(subjectId);

    }

    private List<ExpandGroupItemEntity<String, AllsubjectBean.ResultBean.DetailBean>> obtainDataList() {
        List<ExpandGroupItemEntity<String, AllsubjectBean.ResultBean.DetailBean>> dataList = new ArrayList<>();

        for (int group = 0; group < bean.getResult().size(); group++) {
            ExpandGroupItemEntity<String, AllsubjectBean.ResultBean.DetailBean> groupItem = new ExpandGroupItemEntity<>();
            groupItem.setExpand(false);
            groupItem.setParent(bean.getResult().get(group).getName());
            List<AllsubjectBean.ResultBean.DetailBean> childList = new ArrayList<>();
            for (int child = 0; child < bean.getResult().get(group).getDetail().size(); child++) {
                childList.add(bean.getResult().get(group).getDetail().get(child));

            }
            groupItem.setChildList(childList);
            dataList.add(groupItem);
        }

        return dataList;
    }

    @Override
    protected TikuPresenter<TikuMvp.Tiku_View> createPresenter() {
        return new TikuPresenter<>();
    }

//    @OnClick({R.id.choose_subject, R.id.toolbar_rela})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.choose_subject:
//                if (bean != null) {
//                    showPopWindows();
//                } else {
//                    ToastUtil.makeLongText(getContext(), "暂无数据");
//                }
//
//
//                break;
//
//        }
//    }

    @OnClick(R.id.hudong)
    public void onViewClicked() {
        if (SharedPreferenceUtils.getisLogin()) {
            startActivity(HudongActivity.class);
        } else {
            startActivity(PwdLoginActivity.class);
        }


    }

    @OnClick({R.id.tv_senior, R.id.tv_junior})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_senior:
                tvSenior.setBackgroundResource(R.drawable.chooseleft);
                tvJunior.setBackgroundResource(R.drawable.righttuoyuan);
                tvSenior.setTextColor(Color.parseColor("#ffffff"));
                tvJunior.setTextColor(Color.parseColor("#1091E9"));
                scoreTab.removeAllTabs();
                for (int i = 0; i < highsubject.size(); i++) {
                    scoreTab.addTab(scoreTab.newTab().setText(highsubject.get(i).getName()));
                }
                substring=highsubject.get(0).getName();
                subjectId = highsubject.get(0).getSubjectId();
                SharedPreferenceUtils.setsubject_id(subjectId);
                schooltype = 1;
                break;
            case R.id.tv_junior:
                tvSenior.setBackgroundResource(R.drawable.lefttuoyuan);
                tvJunior.setBackgroundResource(R.drawable.chosseright);
                tvSenior.setTextColor(Color.parseColor("#1091E9"));
                tvJunior.setTextColor(Color.parseColor("#ffffff"));
                scoreTab.removeAllTabs();
                for (int i = 0; i < middlesubject.size(); i++) {
                    scoreTab.addTab(scoreTab.newTab().setText(middlesubject.get(i).getName()));
                }
                substring=middlesubject.get(0).getName();
                subjectId = middlesubject.get(0).getSubjectId();
                SharedPreferenceUtils.setsubject_id(subjectId);
                schooltype = 2;
                break;

        }
    }
}
