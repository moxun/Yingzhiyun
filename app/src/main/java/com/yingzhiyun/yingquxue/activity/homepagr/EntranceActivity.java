package com.yingzhiyun.yingquxue.activity.homepagr;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ZiyuanJsonBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.BashiAdapter;
import com.yingzhiyun.yingquxue.adapter.ChooseStateAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.SelectedOptionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntranceActivity extends BaseActicity<SelectedOptionsMvp.SelectedOptions_View, SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View>> implements SelectedOptionsMvp.SelectedOptions_View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.text_stage)
    TextView textStage;
    @BindView(R.id.choose_stage)
    ImageView chooseStage;
    @BindView(R.id.recy_kao)
    RecyclerView recyKao;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.serach_Lin)
    LinearLayout serachLin;
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private PopupWindow popupWindow1;
    private LayoutInflater mInflater;
    private ChooseStateAdapter chooseStateAdapter;
    private RecyclerView chu;
    private ArrayList<ZiyuanBean.ResultBean> resultBeans;
    private BashiAdapter bashiAdapter;
    private int page = 0;
    private int gradeID;
    private HomePagerBean.ResultBean.MenuBean moduleBean;
    private ZiyuanBean.ResultBean ListBean;
    private ZiyuanBean.ResultBean listBean;
    private TextView shoucang;
    private JSONObject jsonObject;

    @Override
    protected void initData() throws ParseException {
        refreshLayout.setOnRefreshListener(this);
        moduleBean = (HomePagerBean.ResultBean.MenuBean) getIntent().getSerializableExtra("bean");

        mInflater = getLayoutInflater();

        resultBeans = new ArrayList<>();
        bashiAdapter = new BashiAdapter(resultBeans);
        recyKao.setLayoutManager(new LinearLayoutManager(this));
        recyKao.setAdapter(bashiAdapter);
        bashiAdapter.OnsetOnClickListener(new BashiAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(ZiyuanBean.ResultBean resultBean, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", resultBean.getId());
                bundle.putString("title", resultBean.getTitle());
                bundle.putBoolean("is",resultBean.isFolderCollection());
                startActivity(BashiinfoActivity.class, bundle);

            }

            @Override
            public void setCollListener(ZiyuanBean.ResultBean resultBean, int position, TextView textView) {
                listBean = resultBean;
                shoucang = textView;
                CollictionList(resultBean.getId());
            }
        });

        if(moduleBean.getType().equals("folderList-noOpt")){
            textStage.setVisibility(View.GONE);
            chooseStage.setVisibility(View.GONE);
            jsonObject = new JSONObject();
            try {
                jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
                jsonObject.put("token", SharedPreferenceUtils.getToken());
                jsonObject.put("indexListTypeId",moduleBean.getId());

                jsonObject.put("pageNum", page);
                jsonObject.put("version", MyApp.version);
                jsonObject.put("device", "Android");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mPresentser.getZiyuan(jsonObject.toString());
        }else {
            showSohwnpop();
            mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, 0, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
            mPresentser.getfolderListOptions();
        }

        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//如果actionId是搜索的id，则进行下一步的操作
                    if(tvSearch.getText().toString()!=null){
                        serarch();
                        hideKeyboard(tvSearch);
                    }else {
                        ToastUtil.makeShortText(EntranceActivity.this,"请输入搜索内容");
                    }

                }
                return false;
            }
        });
    }

    private void serarch() {
        resultBeans.clear();
        if(moduleBean.getType().equals("folderList-noOpt")){

            mPresentser.getZiyuan(jsonObject.toString());
        }else {

            mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, 0, SharedPreferenceUtils.getToken(), tvSearch.getText().toString(),MyApp.version, "Android")));
        }

    }

    @Override
    protected void showLoading(String msg) {
        super.showLoading(msg);
        if(listBean.isFolderCollection()){
            shoucang.setText("收藏");
            shoucang.setBackgroundResource(R.drawable.shoucang);
            listBean.setFolderCollection(false);
            ToastUtil.makeShortText(this,"取消收藏成功");
        }   else {
            shoucang.setText("已收藏");
            shoucang.setBackgroundResource(R.drawable.yiguanzhu);
            listBean.setFolderCollection(true);
            ToastUtil.makeShortText(this,"收藏成功");
        }
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_entrance;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    public void onRefresh() {
        tvSearch.setText("");
        resultBeans.clear();


        if(moduleBean.getType().equals("folderList-noOpt")){

            mPresentser.getZiyuan(jsonObject.toString());
        }else {
            mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, 0, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
        }
    }



    @Override
    public void setSelectedOptions(SelectedOptionsBean selectedOptions) {

    }
    /**
     * 隐藏软键盘
     *
     *
     * @param view    :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {
        if(refreshLayout!=null){
            refreshLayout.setRefreshing(false);
        }
        resultBeans.clear();
        if (ziyuan.getStatus() == 200) {
            if (ziyuan.getResult().size() != 0) {
                recyKao.setVisibility(View.VISIBLE);
                linearModle.setVisibility(View.GONE);
                resultBeans.addAll(ziyuan.getResult());
                bashiAdapter.notifyDataSetChanged();
            } else {
                recyKao.setVisibility(View.GONE);
                linearModle.setVisibility(View.VISIBLE);
            }

        }else if(ziyuan.getStatus()==511){
            finish();
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setCoursewareList(ZiyuanBean coursewareList) {

    }

    @Override
    public void setskillTypeList(skillTypeListBean skillTypeListBean) {

    }

    @Override
    public void setskillCourseList(skillCourseListBeam skillCourseListBeam) {

    }

    @Override
    public void setfolderListOptions(FolderListOptionsBean folderListOptionsBean) {
        if (folderListOptionsBean.getStatus() == 200) {
            gradeID = folderListOptionsBean.getResult().get(0).getId();
            resultBeans.clear();
            Log.d("ddddd", new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, 0, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
            mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, 0, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
            textStage.setText(folderListOptionsBean.getResult().get(0).getTitle());
            chooseStateAdapter = new ChooseStateAdapter(folderListOptionsBean.getResult());
            chu.setLayoutManager(new LinearLayoutManager(this));
            chu.setAdapter(chooseStateAdapter);
            chooseStateAdapter.OnsetOnClickListener(new ChooseStateAdapter.setOnClickListener() {
                @Override
                public void setOnClickListener(FolderListOptionsBean.ResultBean resultBean, int position) {
                    popupWindow1.dismiss();
                    chooseStage.setImageResource(R.mipmap.blackdetail);
                    textStage.setText(resultBean.getTitle());
                    gradeID = resultBean.getId();
                    resultBeans.clear();

                    mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean.getId(), SharedPreferenceUtils.getUserid(), gradeID, 0, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
                }
            });
        } else if (folderListOptionsBean.getStatus() == 511) {
            finish();
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setfolderDetail(BashiinfoBean bashiinfoBean) {

    }
    @Override
    protected void Errorcool(String msg) {
        super.showLoading(msg);
        finish();
        startActivity(PwdLoginActivity.class);
        ToastUtil.makeShortText(this,msg);
    }
    @Override
    protected SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View> createPresenter() {
        return new SelectedOptionsPresenter<>();
    }

    public void showSohwnpop() {


        View view = mInflater.inflate(R.layout.pop_choosestate, null);
        chu = view.findViewById(R.id.recy_choose);

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


        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                chooseStage.setImageResource(R.mipmap.blackdetail);
            }
        });

    }

    @OnClick({R.id.back, R.id.text_stage, R.id.choose_stage,R.id.serach_Lin, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.text_stage:
                chooseStage.setImageResource(R.mipmap.topdeatile);
                popupWindow1.showAsDropDown(textStage);
                break;
            case R.id.choose_stage:
                chooseStage.setImageResource(R.mipmap.topdeatile);
                popupWindow1.showAsDropDown(textStage);
                break;

                case R.id.serach_Lin:
                    break;
                case R.id.iv_search:
                    if(tvSearch.getText().toString()!=null){
                        serarch();
                    }else {
                        ToastUtil.makeShortText(EntranceActivity.this,"请输入搜索内容");
                    }
                    break;
            }
        }
    }






