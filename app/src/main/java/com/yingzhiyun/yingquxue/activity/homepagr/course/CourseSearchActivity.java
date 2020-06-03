package com.yingzhiyun.yingquxue.activity.homepagr.course;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.Searchcontentjson;
import com.yingzhiyun.yingquxue.OkBean.daobean.CourseHelper;
import com.yingzhiyun.yingquxue.OkBean.daobean.CourseSearchBean;
import com.yingzhiyun.yingquxue.OkBean.daobean.SearchHistory;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.pay.RecordActivity;
import com.yingzhiyun.yingquxue.adapter.CourseListAdapter;
import com.yingzhiyun.yingquxue.adapter.CourseSearchAdapter;
import com.yingzhiyun.yingquxue.adapter.SearchAdapter;

import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils;
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack;
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;

public class CourseSearchActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.deleteall)
    ImageView deleteall;
    @BindView(R.id.recy_search)
    RecyclerView recySearch;
    @BindView(R.id.rela_history)
    RelativeLayout relaHistory;
    @BindView(R.id.recy_content)
    RecyclerView recyContent;
    private CourseSearchAdapter searchAdapter;
    private String search;
    private boolean isadd;
    private int page = 1;
//    private ArrayList<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> resultBeans;
    private CourseListAdapter searchcontenAdapter;
    private ArrayList<CourseBean.ResultBean> list=new ArrayList<>();
    private CourseListAdapter courseListAdapter;
    @Override
    protected void initData() {

        page=1;
        if(CourseHelper.getInstance().query().size()>0){
            deleteall.setVisibility(View.VISIBLE);
        }else {
            deleteall.setVisibility(View.GONE);
        }
        tvSearch.setLongClickable(true);
        searchAdapter = new CourseSearchAdapter(CourseHelper.getInstance().query());
        recySearch.setLayoutManager(new LinearLayoutManager(this));
        recySearch.setAdapter(searchAdapter);

//        resultBeans = new ArrayList<>();
        searchcontenAdapter = new CourseListAdapter(list,this);

        recyContent.setLayoutManager(new LinearLayoutManager(this));
        recyContent.setAdapter(searchcontenAdapter);
        searchAdapter.OnsetOnClickListener(new CourseSearchAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(CourseSearchBean s) {
                search=s.getTitle();
                getList(search);
            }
        });

        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
//如果actionId是搜索的id，则进行下一步的操作
                    serarch();
                }
                return false; }
        });


    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.iv_search, R.id.deleteall, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.iv_search:
                serarch();
                break;
            case R.id.deleteall:
                deleteall.setVisibility(View.GONE);
                CourseHelper.getInstance().deleteAll();
                searchAdapter.list.clear();
                searchAdapter.notifyDataSetChanged();
                break;
            case R.id.search:
                serarch();
                break;
        }
    }

    private void serarch() {
        page = 1;
        String search = tvSearch.getText().toString().trim();
        if (StringUtils.isEmpty(search)) {

            ToastUtil.makeLongText(this,"请输入搜索内容");
            return;
        }
        boolean isadd = CourseHelper.getInstance().queryLikeId(search);
        if (isadd) {
            CourseHelper.getInstance().insert(new CourseSearchBean(null, search));
            searchAdapter.list.add(new CourseSearchBean(null, search));
            searchAdapter.notifyDataSetChanged();

        }
        getList(search);
        tvSearch.setText("");
        deleteall.setVisibility(View.VISIBLE);
    }


    private void getList(String content) {

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());

            jsonObject.put("title", content);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString()
                .url(FristServer.URL + "courseList")
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<CourseBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(CourseSearchActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(CourseBean response) throws org.json.JSONException {
                        List<CourseBean.ResultBean> resultBeans = response.getResult();
                        list.clear();

                        if (resultBeans.size() > 0) {
                            relaHistory.setVisibility(View.GONE);
                            recyContent.setVisibility(View.VISIBLE);
                            list.addAll(resultBeans);
                            searchcontenAdapter.notifyDataSetChanged();
                        }else{
                            relaHistory.setVisibility(View.VISIBLE);
                            recyContent.setVisibility(View.GONE);
                            ToastUtil.makeLongText(CourseSearchActivity.this,"暂无内容");
                        }
                    }
                }));
    }
}
