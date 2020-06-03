package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.TiRecordMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TestPaperListjson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TestpagperinfoJson;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.tiku.AnalysisActivity;
import com.yingzhiyun.yingquxue.adapter.TiRecordAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.TiRecordPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TiRecordFragment extends BaseFragment<TiRecordMvp.TiRecord_View, TiRecordPresenter<TiRecordMvp.TiRecord_View>>
        implements TiRecordMvp.TiRecord_View {
    private final int subject;
    @BindView(R.id.total_size)
    TextView totalSize;
    @BindView(R.id.totalright_size)
    TextView totalrightSize;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.rela_back)
    RelativeLayout relaBack;
    @BindView(R.id.recy_tirecord)
    PullLoadMoreRecyclerView recyTirecord;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private  int page=1;
    private List<ZutijiluBean.ResultBean.DetailBean> detailBeans=new ArrayList<>();
    private TiRecordAdapter tiRecordAdapter;

    public TiRecordFragment(int subjectId) {
        super();
        this.subject=subjectId;
    }

    @Override
    public void setTiRecordlist(AllsubjectBean wronglist) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setjiLu(ZutijiluBean myTiBean) {
        ZutijiluBean.ResultBean result = myTiBean.getResult();

        if (myTiBean.getStatus() == 200) {
            recyTirecord.setPullLoadMoreCompleted();
            totalSize.setText(result.getFrequency() + "");
            totalrightSize.setText(result.getTotalRight() + "");

            String s = msToM(result.getTotalTime());
            if (result.getFrequency() == 0) {
                time.setText("00:00");
            } else {
                time.setText(s);
            }

            if (page == 1) {
                if (result.getDetail().size() > 0) {
                    detailBeans.clear();
                    linearModle.setVisibility(View.GONE);
                    recyTirecord.setVisibility(View.VISIBLE);

                    detailBeans.addAll(result.getDetail());
                    tiRecordAdapter.notifyDataSetChanged();
                } else {
                    linearModle.setVisibility(View.VISIBLE);
                    recyTirecord.setVisibility(View.GONE);
                }
            } else {
                if (result.getDetail().size() > 0) {

                    detailBeans.addAll(result.getDetail());
                    tiRecordAdapter.notifyDataSetChanged();
                } else {
                    recyTirecord.setPullRefreshEnable(false);
                }
            }


        } else {

            ToastUtil.makeShortText(context, "身份过期");
            startActivity(PwdLoginActivity.class);
        }
    }

    /**
     * 毫秒转分
     *
     * @param ms
     * @return
     */
    public static String msToM(int ms) {
        int seconds = ms / 1000;
        int minutes = seconds / 60;
        seconds = seconds % 60;

        String m = null;
        String s = null;

        if (minutes == 0 && seconds == 0)
            seconds = 1;

        if (minutes < 10)
            m = "0" + minutes;
        else
            m = "" + minutes;

        if (seconds < 10)
            s = "0" + seconds;
        else
            s = "" + seconds;

        return m + ":" + s;
    }
    @Override
    public void setuserTestPaper(ExamineBean examineBean) {
        if (examineBean.getStatus() == 200) {
            startActivity(new Intent(context, AnalysisActivity.class).putExtra("bean", examineBean).putExtra("type", "all"));
        }
    }

    @Override
    protected TiRecordPresenter<TiRecordMvp.TiRecord_View> createPresenter() {
        return new TiRecordPresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_tirecord;
    }

    @Override
    protected void initData() throws JSONException {

        tiRecordAdapter = new TiRecordAdapter(detailBeans, context);
        recyTirecord.setLinearLayout();
        recyTirecord.setAdapter(tiRecordAdapter);
        tiRecordAdapter.OnsetOnClickListener(new TiRecordAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(ZutijiluBean.ResultBean.DetailBean musicBean) {
                Log.d("--------", "setOnClickListener: "+new Gson().toJson(new TestpagperinfoJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), musicBean.getId() + "", MyApp.version, "Android")));
                presenter.getuserTestPaper(new Gson().toJson(new TestpagperinfoJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), musicBean.getId() + "", MyApp.version, "Android")));
            }
        });
        recyTirecord.setPullRefreshEnable(false);
        recyTirecord.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                page++;
                presenter.getjiLu(new Gson().toJson(new TestPaperListjson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), subject, page, MyApp.version, "Android")));
            }
        });
        presenter.getjiLu(new Gson().toJson(new TestPaperListjson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), subject, page, MyApp.version, "Android")));
    }
}
