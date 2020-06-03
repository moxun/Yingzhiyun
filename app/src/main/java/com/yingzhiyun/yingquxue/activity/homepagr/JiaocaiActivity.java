package com.yingzhiyun.yingquxue.activity.homepagr;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.SearchSchoolJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.SelectJsonBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ZiyuanJsonBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZhiyePeixunJson;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.mine.SearchSchoolActivity;
import com.yingzhiyun.yingquxue.adapter.FirstListADapter;
import com.yingzhiyun.yingquxue.adapter.MoreVideoAdapter;
import com.yingzhiyun.yingquxue.adapter.SelectAdapter;
import com.yingzhiyun.yingquxue.adapter.ThreeListAdapter;
import com.yingzhiyun.yingquxue.adapter.TwoLiatAdapter;
import com.yingzhiyun.yingquxue.adapter.VocationalAdapter;
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.model.FirstClassItem;
import com.yingzhiyun.yingquxue.model.SecondClassItem;
import com.yingzhiyun.yingquxue.model.ThreeClassItem;
import com.yingzhiyun.yingquxue.presenter.SelectedOptionsPresenter;
import com.yingzhiyun.yingquxue.units.ScreenUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class JiaocaiActivity extends BaseActicity<SelectedOptionsMvp.SelectedOptions_View, SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View>> implements SelectedOptionsMvp.SelectedOptions_View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    @BindView(R.id.choose)
    LinearLayout choose;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.serach_Lin)
    LinearLayout serachLin;
    private LayoutInflater mInflater;
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.text_stage)
    TextView textStage;
    @BindView(R.id.choose_stage)
    ImageView chooseStage;
    @BindView(R.id.text_grade)
    TextView textGrade;
    @BindView(R.id.choose_grade)
    ImageView chooseGrade;
    @BindView(R.id.linear_grade)
    LinearLayout linearGrade;
    @BindView(R.id.text_subject)
    TextView textSubject;
    @BindView(R.id.choose_subject)
    ImageView chooseSubject;
    @BindView(R.id.linear_subject)
    LinearLayout linearSubject;


    @BindView(R.id.recy_ziyuan)
    PullLoadMoreRecyclerView recyZiyuan;
    @BindView(R.id.main_darkview)
    View mainDarkview;
    //弹出PopupWindow时，背景变暗的动画
    private Animation animIn, animOut;
    private HomePagerBean.ResultBean.MenuBean moduleBean;
    private PopupWindow popupWindow;
    private List<SelectedOptionsBean.ResultBean> resultBeans;
    private List<SelectedOptionsBean.ResultBean> resultsujbects;
    private SelectAdapter selectAdapter;
    private List<List<SelectedOptionsBean.ResultBean>> result;
    private PopupWindow popupWindow1;
    private RecyclerView recyclerView;
    private SelectAdapter subjectAdapter;
    private int gradeID, subjectID = 0;
    private int stageId;
    private ArrayList<ZiyuanBean.ResultBean> normalList;
    private ArrayList<ZiyuanBean.ResultBean> videoList;
    private ZiyuanAdapter ziyuanAdapter;
    private MoreVideoAdapter moreSelectionAdapter;
    private PopupWindow popupLevel;
    private RecyclerView pop_listview_first;
    private RecyclerView pop_listview_two;
    private RecyclerView pop_listview_three;
    private ArrayList<skillTypeListBean.ResultBean.TypeBean> firstClassItems;
    private ArrayList<skillTypeListBean.ResultBean.TypeBean.DetailBeanX> secondClassItems;
    private ArrayList<skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean> threeClassItems;
    private FirstListADapter firstListADapter;
    private TwoLiatAdapter twoLiatAdapter;
    private ThreeListAdapter threeListAdapter;
    private ArrayList<skillCourseListBeam.ResultBean> vocationalList;
    private VocationalAdapter vocationalAdapter;
    int page = 1;
    private skillTypeListBean skillbean;
    private int skillTypeId;
    private int rankingType;

    @Override
    protected void initData() {
        refreshLayout.setOnRefreshListener(this);
        mInflater = getLayoutInflater();
        resultBeans = new ArrayList<>();
        resultsujbects = new ArrayList<>();
        normalList = new ArrayList<>();
        videoList = new ArrayList<>();
        vocationalList = new ArrayList<>();
        selectAdapter = new SelectAdapter(this.resultBeans);
        subjectAdapter = new SelectAdapter(resultsujbects);

        initLevel();
        initPopup();


        mPresentser.getskillTypeList(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid()+"", SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        moduleBean = (HomePagerBean.ResultBean.MenuBean) getIntent().getSerializableExtra("bean");
        assert moduleBean != null;

        ziyuanAdapter = new ZiyuanAdapter(normalList, this);
        if (moduleBean.getId() == 18) {
            choose.setVisibility(View.GONE);
            textStage.setVisibility(View.GONE);
            ivSearch.setVisibility(View.VISIBLE);
            ivSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", moduleBean.getId());
                    bundle.putString("type", "ziyuan");
                    startActivity(OnceSearchActivity.class, bundle);
                }
            });
            chooseStage.setVisibility(View.GONE);
            moreSelectionAdapter = new MoreVideoAdapter(videoList, this);
            recyZiyuan.setLinearLayout();
            recyZiyuan.setAdapter(moreSelectionAdapter);
        } else if (moduleBean.getId() == 17) {

            textStage.setVisibility(View.GONE);
            chooseStage.setVisibility(View.GONE);
            vocationalAdapter = new VocationalAdapter(vocationalList, this);
            recyZiyuan.setLinearLayout();
            recyZiyuan.setAdapter(vocationalAdapter);

        } else {

            recyZiyuan.setLinearLayout();
            recyZiyuan.setAdapter(ziyuanAdapter);
        }
        recyZiyuan.setPullRefreshEnable(false);
        recyZiyuan.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                page++;
                mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
                recyZiyuan.setPullLoadMoreCompleted();
            }
        });
        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//如果actionId是搜索的id，则进行下一步的操作
                    page = 1;
                    if (!StringUtils.isEmpty(tvSearch.getText().toString())) {
                        mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), tvSearch.getText().toString(), MyApp.version,"Android")));
                    } else {
                        mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), "", MyApp.version,"Android")));
                    }

                }
                return false;
            }
        });
        animIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        animOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);
        stageId = 2;
        Log.d("----------", "initData: "+new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        mPresentser.getSelect(new Gson().toJson(new SelectJsonBean(moduleBean.getId(), stageId, MyApp.version,"Android")));
        mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        showLoading();
    }



    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @Override
    protected SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View> createPresenter() {
        return new SelectedOptionsPresenter<>();
    }


    @Override
    public void onRefresh() {
        page = 1;
        normalList.clear();
        videoList.clear();
        vocationalList.clear();
        Log.d("mo", "onRefresh: " + gradeID + stageId);
        if (moduleBean.getId() == 17) {
            mPresentser.getskillCourseList(new Gson().toJson(new ZhiyePeixunJson(skillTypeId, rankingType, MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken())));
        } else {
            mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        }

        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setSelectedOptions(SelectedOptionsBean selectedOptions) {
        result = selectedOptions.getResult();
    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {

        if (ziyuan.getStatus() == 200) {
            if (page == 1) {
                normalList.clear();
                videoList.clear();

                if (ziyuan.getResult().size() > 0) {
                    linearModle.setVisibility(View.GONE);
                    recyZiyuan.setVisibility(View.VISIBLE);
                    if (moduleBean.getId() == 18) {
                        videoList.addAll(ziyuan.getResult());
                        moreSelectionAdapter.notifyDataSetChanged();
                    } else {
                        normalList.addAll(ziyuan.getResult());
                        ziyuanAdapter.notifyDataSetChanged();
                    }

                } else {
                    linearModle.setVisibility(View.VISIBLE);
                    recyZiyuan.setVisibility(View.GONE);
                }
            } else {
                Log.d("moxub", "setZiyuan: " + page + "JDJj" + ziyuan.getResult().size());
                if (ziyuan.getResult().size() > 0) {

                    if (moduleBean.getId() == 18) {
                        videoList.addAll(ziyuan.getResult());
                        moreSelectionAdapter.notifyDataSetChanged();
                    } else {
                        normalList.addAll(ziyuan.getResult());
                        ziyuanAdapter.notifyDataSetChanged();
                    }

                } else {
                    ToastUtil.makeShortText(this, "没有更多数据了");
                    recyZiyuan.setPushRefreshEnable(false);
                }
            }
        }else  if(ziyuan.getStatus()==511){
            finish();
            startActivity(PwdLoginActivity.class);
        }


    }

    @Override
    public void setCoursewareList(ZiyuanBean coursewareList) {

    }

    @Override
    public void setskillTypeList(skillTypeListBean skillTypeListBean) {
        if (skillTypeListBean.getStatus() == 200) {
            skillbean = skillTypeListBean;
            firstClassItems.addAll(skillTypeListBean.getResult().getType());
            firstListADapter.notifyDataSetChanged();
            secondClassItems.addAll(skillTypeListBean.getResult().getType().get(0).getDetail());
            threeListAdapter.notifyDataSetChanged();
            threeClassItems.addAll(skillTypeListBean.getResult().getType().get(0).getDetail().get(0).getDetail());
            threeListAdapter.notifyDataSetChanged();
            if (moduleBean.getId() == 17) {
                skillTypeId = skillTypeListBean.getResult().getType().get(0).getDetail().get(0).getId();
                rankingType = skillTypeListBean.getResult().getRanking().get(0).getId();
                mPresentser.getskillCourseList(new Gson().toJson(new ZhiyePeixunJson(skillTypeId, rankingType, MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken())));
                textGrade.setText(skillTypeListBean.getResult().getRanking().get(0).getTitle());
                textSubject.setText("职业考证");
            }

        }else if (skillTypeListBean.getStatus()==511){
            finish();
            ToastUtil.makeShortText(this,"账号在别处登录");
            startActivity(PwdLoginActivity.class);
        }

    }

    @Override
    public void setskillCourseList(skillCourseListBeam skillCourseListBeam) {
        if (skillCourseListBeam.getStatus() == 200) {
            Log.d("moxun", "setskillCourseList: " + skillCourseListBeam.getResult().size());
            vocationalList.clear();
            if (skillCourseListBeam.getResult().size() > 0) {
                linearModle.setVisibility(View.GONE);
                recyZiyuan.setVisibility(View.VISIBLE);

                vocationalList.addAll(skillCourseListBeam.getResult());
                vocationalAdapter.notifyDataSetChanged();

            } else {
                linearModle.setVisibility(View.VISIBLE);
                recyZiyuan.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void setfolderListOptions(FolderListOptionsBean folderListOptionsBean) {

    }

    @Override
    public void setfolderDetail(BashiinfoBean bashiinfoBean) {

    }


    @OnClick({R.id.finish, R.id.text_stage, R.id.choose_stage, R.id.linear_grade, R.id.linear_subject})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.text_stage:
                showSohwnpop();
                break;
            case R.id.choose_stage:
                showSohwnpop();
                break;
            case R.id.linear_grade:

                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(selectAdapter);
                resultBeans.clear();
                if (moduleBean.getId() == 17) {
                    if(skillbean!=null){
                        for (int i = 0; i < skillbean.getResult().getRanking().size(); i++) {
                            resultBeans.add(new SelectedOptionsBean.ResultBean(skillbean.getResult().getRanking().get(i).getId(), skillbean.getResult().getRanking().get(i).getTitle()));
                        }
                    }



                } else {
                    resultBeans.addAll(result.get(0));
                }

                selectAdapter.notifyDataSetChanged();
                textGrade.setTextColor(getResources().getColor(R.color.mainColor));
                chooseGrade.setImageResource(R.mipmap.topdeatile);
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAsDropDown(findViewById(R.id.main_div_line));
                    popupWindow.setAnimationStyle(-1);
                    //背景变暗
                    mainDarkview.startAnimation(animIn);
                    mainDarkview.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.linear_subject:
                if (moduleBean.getId() != 17) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(subjectAdapter);
                    resultsujbects.clear();
                    resultsujbects.addAll(result.get(1));
                    Log.d("moxun", "onViewClicked: " + resultsujbects);
                    subjectAdapter.notifyDataSetChanged();
                    textSubject.setTextColor(getResources().getColor(R.color.mainColor));
                    chooseSubject.setImageResource(R.mipmap.topdeatile);
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {
                        popupWindow.showAsDropDown(findViewById(R.id.main_div_line));
                        popupWindow.setAnimationStyle(-1);
                        //背景变暗
                        mainDarkview.startAnimation(animIn);
                        mainDarkview.setVisibility(View.VISIBLE);
                    }
                } else {

                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {
                        popupLevel.showAsDropDown(findViewById(R.id.main_div_line));
                        popupLevel.setAnimationStyle(-1);
                        //背景变暗
                        mainDarkview.startAnimation(animIn);
                        mainDarkview.setVisibility(View.VISIBLE);
                    }
                }

                break;
        }
    }

    private void initPopup() {
        popupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        recyclerView = view.findViewById(R.id.pop_listview_left);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.setFocusable(true);

//        popupWindow.setHeight(ScreenUtils.getScreenH(this) * 2 / 3);
        popupWindow.setWidth(ScreenUtils.getScreenW(this));

        selectAdapter.OnsetOnClickListener(new SelectAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(SelectedOptionsBean.ResultBean resultBean) {
                popupWindow.dismiss();
                textGrade.setText(resultBean.getTitle());
                if (moduleBean.getId() != 17) {
                    page = 1;
                    gradeID = resultBean.getId();

                    mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(1, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
                } else {
                    rankingType = resultBean.getId();
                    mPresentser.getskillCourseList(new Gson().toJson(new ZhiyePeixunJson(skillTypeId, rankingType, MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken())));
                }


            }
        });

        subjectAdapter.OnsetOnClickListener(new SelectAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(SelectedOptionsBean.ResultBean resultBean) {
                popupWindow.dismiss();
                textSubject.setText(resultBean.getTitle());
                subjectID = resultBean.getId();
                page = 1;
                mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(1, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mainDarkview.startAnimation(animOut);
                mainDarkview.setVisibility(View.GONE);
                textGrade.setTextColor(getResources().getColor(R.color.black));
                textSubject.setTextColor(getResources().getColor(R.color.black));
                chooseGrade.setImageResource(R.mipmap.blackdetail);
                chooseSubject.setImageResource(R.mipmap.blackdetail);
            }
        });


    }

    public void showSohwnpop() {
        chooseStage.setImageResource(R.mipmap.topdeatile);
        ArrayList<String> titles = new ArrayList<String>();
        titles.add("初中");
        titles.add("高中");

        View view = mInflater.inflate(R.layout.pop_selectgrade, null);
        TextView chu = view.findViewById(R.id.chu);
        TextView hingh = view.findViewById(R.id.hingh);
        popupWindow1 = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredHeight();
        int[] location = new int[2];
        // 允许点击外部消失
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
        // 获得位置
        textStage.getLocationOnScreen(location);
        popupWindow1.setAnimationStyle(R.style.mypopwindow_anim_style);
//				popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
        popupWindow1.showAsDropDown(textStage);

        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                chooseStage.setImageResource(R.mipmap.blackdetail);
            }
        });
        chu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textStage.setText("初中");
                stageId = 1;
                page = 1;
                gradeID=7;
                subjectID=0;
                textGrade.setText("七年级");
                textSubject.setText("全部科目");
                mPresentser.getSelect(new Gson().toJson(new SelectJsonBean(moduleBean.getId(), stageId, MyApp.version,"Android")));
                mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
                popupWindow1.dismiss();
            }
        });
        hingh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textStage.setText("高中");
                stageId = 2;
                page = 1;
                gradeID=10;
                subjectID=0;
                textGrade.setText("高一");
                textSubject.setText("全部科目");
                mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, subjectID, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
                mPresentser.getSelect(new Gson().toJson(new SelectJsonBean(moduleBean.getId(), stageId, MyApp.version,"Android")));
                popupWindow1.dismiss();
            }
        });
    }

    private void initLevel() {
        popupLevel = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_threerecy, null);
        pop_listview_first = view.findViewById(R.id.pop_listview_first);
        pop_listview_two = view.findViewById(R.id.pop_listview_two);
        pop_listview_three = view.findViewById(R.id.pop_listview_three);
        firstClassItems = new ArrayList<>();
        secondClassItems = new ArrayList<>();
        threeClassItems = new ArrayList<>();

        firstListADapter = new FirstListADapter(firstClassItems, this);
        twoLiatAdapter = new TwoLiatAdapter(secondClassItems, this);
        threeListAdapter = new ThreeListAdapter(threeClassItems);
        pop_listview_first.setLayoutManager(new LinearLayoutManager(this));
        pop_listview_two.setLayoutManager(new LinearLayoutManager(this));
        pop_listview_three.setLayoutManager(new LinearLayoutManager(this));

        pop_listview_first.setAdapter(firstListADapter);
        pop_listview_two.setAdapter(twoLiatAdapter);
        pop_listview_three.setAdapter(threeListAdapter);

        popupLevel.setContentView(view);
        popupLevel.setBackgroundDrawable(new PaintDrawable());
        popupLevel.setFocusable(true);

//        popupWindow.setHeight(ScreenUtils.getScreenH(this) * 2 / 3);
        popupLevel.setWidth(ScreenUtils.getScreenW(this));

        firstListADapter.OnsetOnClickListener(new FirstListADapter.setOnClickListener() {
            @Override
            public void setOnClickListener(skillTypeListBean.ResultBean.TypeBean musicBean, int position) {
                //二级数据
                List<skillTypeListBean.ResultBean.TypeBean.DetailBeanX> list2 = musicBean.getDetail();
                //如果没有二级类，则直接跳转
                if (list2 == null || list2.size() == 0) {
                    popupLevel.dismiss();
                    textSubject.setText(musicBean.getTitle());
                    return;
                }


                //如果上次点击的就是这一个item，则不进行任何操作
                if (firstListADapter.getSelectedPosition() == position) {
                    return;
                }

                //根据左侧一级分类选中情况，更新背景色
                firstListADapter.setSelectedPosition(position);
                firstListADapter.notifyDataSetChanged();

                updateSecondListView(list2, twoLiatAdapter);
            }


        });

        twoLiatAdapter.OnsetOnClickListener(new TwoLiatAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(skillTypeListBean.ResultBean.TypeBean.DetailBeanX musicBean, int position) {
                List<skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean> name = musicBean.getDetail();

                if (name == null || name.size() == 0) {
                    popupLevel.dismiss();
                    skillTypeId = musicBean.getId();
                    Log.d("moun", "setOnClickListener: " + skillTypeId + "ppp" + rankingType);
                    mPresentser.getskillCourseList(new Gson().toJson(new ZhiyePeixunJson(skillTypeId, rankingType, MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken())));
                    textSubject.setText(musicBean.getTitle());
                    return;
                }
                //如果上次点击的就是这一个item，则不进行任何操作

                if (twoLiatAdapter.getSelectedPosition() == position) {
                    return;
                }

                //根据左侧一级分类选中情况，更新背景色
                twoLiatAdapter.setSelectedPosition(position);
                twoLiatAdapter.notifyDataSetChanged();
                threeClassItems.clear();
                threeClassItems.addAll(name);
                threeListAdapter.notifyDataSetChanged();
            }


        });
        threeListAdapter.OnsetOnClickListener(new ThreeListAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean musicBean, int position) {
                popupLevel.dismiss();
                textSubject.setText(musicBean.getTitle());
                skillTypeId = musicBean.getId();

                mPresentser.getskillCourseList(new Gson().toJson(new ZhiyePeixunJson(skillTypeId, rankingType, MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken())));
            }


        });

        popupLevel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mainDarkview.startAnimation(animOut);
                mainDarkview.setVisibility(View.GONE);
                textGrade.setTextColor(getResources().getColor(R.color.black));
                textSubject.setTextColor(getResources().getColor(R.color.black));
                chooseGrade.setImageResource(R.mipmap.blackdetail);
                chooseSubject.setImageResource(R.mipmap.blackdetail);
            }
        });


    }

    //刷新右侧ListView
    private void updateSecondListView(List<skillTypeListBean.ResultBean.TypeBean.DetailBeanX> list2,
                                      TwoLiatAdapter secondAdapter) {
        secondClassItems.clear();
        secondClassItems.addAll(list2);
        secondAdapter.notifyDataSetChanged();

        List<skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean> name = list2.get(0).getDetail();
        threeClassItems.clear();
        threeClassItems.addAll(name);
        threeListAdapter.notifyDataSetChanged();
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_jiaocai;
    }
}
