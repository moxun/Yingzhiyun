package com.yingzhiyun.yingquxue.activity.homepagr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.ZjietestMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BookinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.KnowledgeJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TeachingShaiJson;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.BookinfoAdapter;
import com.yingzhiyun.yingquxue.adapter.SelectAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ZjieTestPresenter;
import com.yingzhiyun.yingquxue.units.ScreenUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeachingActivity extends BaseActicity<ZjietestMvp.Zjietest_View, ZjieTestPresenter<ZjietestMvp.Zjietest_View>> implements ZjietestMvp.Zjietest_View {
    @BindView(R.id.tv_senior)
    TextView tvSenior;
    @BindView(R.id.tv_junior)
    TextView tvJunior;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.text_verison)
    TextView textVerison;
    @BindView(R.id.choose_grade)
    ImageView chooseGrade;
    @BindView(R.id.linear_verison)
    RelativeLayout linearVerison;
    @BindView(R.id.text_subject)
    TextView textSubject;
    @BindView(R.id.linear_subject)
    RelativeLayout linearSubject;
    @BindView(R.id.text_grade)
    TextView textGrade;
    @BindView(R.id.choose_subject)
    ImageView chooseSubject;
    @BindView(R.id.linear_grade)
    RelativeLayout linearGrade;
    @BindView(R.id.choose)
    LinearLayout choose;
    @BindView(R.id.main_div_line)
    View mainDivLine;
    @BindView(R.id.recy_ziyuan)
    PullLoadMoreRecyclerView recyZiyuan;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.main_darkview)
    View mainDarkview;
    @BindView(R.id.choose_version)
    ImageView chooseVersion;
    @BindView(R.id.finish)
    ImageView finish;
    private RecyclerView recyclerView;
    private PopupWindow popupWindow;

    private Animation animIn, animOut;
    private ArrayList<BooklistBean.ResultBean> bookLists;
    private BookinfoAdapter celecthowAdapter;
    private ArrayList<String> jsonstrings;
    private ArrayList<SelectedOptionsBean.ResultBean> bookVersionList;
    private ArrayList<SelectedOptionsBean.ResultBean> subjectList;
    private ArrayList<SelectedOptionsBean.ResultBean> bookTypeList;
    private SelectAdapter bookVersionAdapter;
    private SelectAdapter subjectAdapter;
    private SelectAdapter bookTypeAdapter;
    private int booktypeid, bookversion, subjectid, schooltype;
    private TeachingShaixuanBean.ResultBean result;

    @Override
    protected void initData() throws ParseException {
        animIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        animOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);

        bookLists = new ArrayList<>();
        jsonstrings = new ArrayList<>();
        jsonstrings.add("bookVersion");
        jsonstrings.add("subject");
        jsonstrings.add("bookType");
        mPresentser.getfilterItem(new Gson().toJson(new TeachingShaiJson(jsonstrings)));
        bookVersionList = new ArrayList<SelectedOptionsBean.ResultBean>();
        subjectList = new ArrayList<SelectedOptionsBean.ResultBean>();
        bookTypeList = new ArrayList<SelectedOptionsBean.ResultBean>();
        bookVersionAdapter = new SelectAdapter(bookVersionList);
        subjectAdapter = new SelectAdapter(subjectList);
        bookTypeAdapter = new SelectAdapter(bookTypeList);
        initPopup();
        celecthowAdapter = new BookinfoAdapter(bookLists);
        recyZiyuan.setGridLayout(3);
        ;
        recyZiyuan.setAdapter(celecthowAdapter);
        recyZiyuan.setPushRefreshEnable(false);
        recyZiyuan.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                getList();
            }

            @Override
            public void onLoadMore() {

            }
        });

        celecthowAdapter.setOnItemListener(new BookinfoAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos, BooklistBean.ResultBean booklistBean) {
                JSONObject jsonObject = new JSONObject();
                Log.d("id", "onClick: " + booklistBean.getId());
                try {
                    jsonObject.put("bookId", booklistBean.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPresentser.getBookinfo(jsonObject.toString());
            }
        });
    }

    @Override
    public int choseeClor() {
        return R.color.white;
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
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mainDarkview.startAnimation(animOut);
                mainDarkview.setVisibility(View.GONE);
                textVerison.setTextColor(getResources().getColor(R.color.black));
                textGrade.setTextColor(getResources().getColor(R.color.black));
                textSubject.setTextColor(getResources().getColor(R.color.black));
                chooseGrade.setImageResource(R.mipmap.blackdetail);
                chooseSubject.setImageResource(R.mipmap.blackdetail);
                chooseVersion.setImageResource(R.mipmap.blackdetail);
            }
        });
        bookVersionAdapter.OnsetOnClickListener(new SelectAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(SelectedOptionsBean.ResultBean resultBean) {
                popupWindow.dismiss();
                bookversion = resultBean.getId();
                textVerison.setText(resultBean.getName());
                getList();
            }
        });
        bookTypeAdapter.OnsetOnClickListener(new SelectAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(SelectedOptionsBean.ResultBean resultBean) {
                popupWindow.dismiss();
                booktypeid = resultBean.getId();
                textSubject.setText(resultBean.getName());
                getList();
            }
        });
        subjectAdapter.OnsetOnClickListener(new SelectAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(SelectedOptionsBean.ResultBean resultBean) {
                popupWindow.dismiss();
                subjectid = resultBean.getId();
                textGrade.setText(resultBean.getName());
                getList();
            }
        });
    }

    @OnClick({R.id.tv_senior, R.id.tv_junior, R.id.linear_verison, R.id.linear_subject, R.id.linear_grade})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_senior:
                tvSenior.setBackgroundResource(R.drawable.chooseleft);
                tvJunior.setBackgroundResource(R.drawable.righttuoyuan);
                tvSenior.setTextColor(Color.parseColor("#ffffff"));
                tvJunior.setTextColor(Color.parseColor("#1091E9"));
                textGrade.setText(result.getSubject().getHeightSchool().getDetail().get(0).getName());
                subjectid = result.getSubject().getHeightSchool().getDetail().get(0).getId();
                schooltype = 1;
                getList();
                break;
            case R.id.tv_junior:
                tvSenior.setBackgroundResource(R.drawable.lefttuoyuan);
                tvJunior.setBackgroundResource(R.drawable.chosseright);
                tvSenior.setTextColor(Color.parseColor("#1091E9"));
                tvJunior.setTextColor(Color.parseColor("#ffffff"));
                textGrade.setText(result.getSubject().getMiddleSchool().getDetail().get(0).getName());
                subjectid = result.getSubject().getMiddleSchool().getDetail().get(0).getId();
                schooltype = 2;
                getList();
                break;
            case R.id.linear_verison:
                textVerison.setTextColor(getResources().getColor(R.color.mainColor));
                chooseVersion.setImageResource(R.mipmap.topdeatile);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(bookVersionAdapter);
                bookVersionList.clear();
                bookVersionList.addAll(result.getBookVersion());
                bookVersionAdapter.notifyDataSetChanged();
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
                textSubject.setTextColor(getResources().getColor(R.color.mainColor));
                chooseSubject.setImageResource(R.mipmap.topdeatile);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(bookTypeAdapter);
                bookTypeList.clear();
                bookTypeList.addAll(result.getBookType());
                bookTypeAdapter.notifyDataSetChanged();
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
            case R.id.linear_grade:
                textGrade.setTextColor(getResources().getColor(R.color.mainColor));
                chooseGrade.setImageResource(R.mipmap.topdeatile);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(subjectAdapter);
                subjectList.clear();
                if (schooltype == 1) {
                    subjectList.addAll(result.getSubject().getHeightSchool().getDetail());
                } else {
                    subjectList.addAll(result.getSubject().getMiddleSchool().getDetail());
                }

                subjectAdapter.notifyDataSetChanged();
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
        }
    }

    @Override
    public void setChapterList(ChapterListBean chapterListBean, String title) {

    }

    @Override
    public void setBookList(BooklistBean booklistBean) {
        bookLists.clear();
        if (booklistBean.getStatus() == 200) {
            if (booklistBean.getResult().size() > 0) {
                linearModle.setVisibility(View.GONE);
                recyZiyuan.setVisibility(View.VISIBLE);
                bookLists.addAll(booklistBean.getResult());
                celecthowAdapter.notifyDataSetChanged();
            } else {
                linearModle.setVisibility(View.VISIBLE);
                recyZiyuan.setVisibility(View.GONE);
            }
        } else if (booklistBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "账号已在别处登录");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setKnowPointer(KnowPointerBean knowPointer) {

    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {

        if (teachingShaixuanBean.getStatus() == 200) {
            result = teachingShaixuanBean.getResult();


            textGrade.setText(teachingShaixuanBean.getResult().getSubject().getHeightSchool().getDetail().get(0).getName());
            textSubject.setText(teachingShaixuanBean.getResult().getBookType().get(0).getName());
            textVerison.setText(teachingShaixuanBean.getResult().getBookVersion().get(0).getName());
            subjectid = teachingShaixuanBean.getResult().getSubject().getHeightSchool().getDetail().get(0).getId();
            booktypeid = teachingShaixuanBean.getResult().getBookType().get(0).getId();
            bookversion = teachingShaixuanBean.getResult().getBookVersion().get(0).getId();
            schooltype = 1;
            getList();
        }
    }

    @Override
    public void setbookinfo(BookinfoBean bookinfoBean) {
        if (bookinfoBean.getStatus() == 200) {
            if (bookinfoBean.getResult() != null) {
                startActivity(new Intent(TeachingActivity.this, WordActivity.class).putExtra("id", 0).putExtra("filepath", bookinfoBean.getResult().getFile_path()).putExtra("shoucang", false).putExtra("title", bookinfoBean.getResult().getTitle()).putExtra("isvip",false));
            } else {
                ToastUtil.makeShortText(this, bookinfoBean.getHint());
            }
        }
    }

    @Override
    protected ZjieTestPresenter<ZjietestMvp.Zjietest_View> createPresenter() {
        return new ZjieTestPresenter<>();
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_teaching;
    }

    public void getList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("subject_id", subjectid);
            jsonObject.put("bookType", booktypeid);
            jsonObject.put("bookVersion", bookversion);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresentser.getBookList(jsonObject.toString());
        recyZiyuan.setPullLoadMoreCompleted();
    }



    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
