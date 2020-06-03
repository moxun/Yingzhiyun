package com.yingzhiyun.yingquxue.Fragment.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.MyCollectJson;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.BashiinfoActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.BashiAdapter;
import com.yingzhiyun.yingquxue.adapter.MoreVideoAdapter;
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.VideoinPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MydownloadFragment extends BaseFragment<VideoinfoMvp.Videoinfo_View, VideoinPresenter<VideoinfoMvp.Videoinfo_View>> implements VideoinfoMvp.Videoinfo_View {
    @BindView(R.id.recy_intera)
    RecyclerView recyIntera;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private ArrayList<ZiyuanBean.ResultBean> filelsit;
    private ZiyuanAdapter fileAdapter;
    private List<ZiyuanBean.ResultBean> folderlist;
    private BashiAdapter bashiAdapter;
    private int type;
    private ZiyuanBean.ResultBean listBean;
    private TextView shoucang;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_recyle;
    }

    @Override
    protected void initData() throws JSONException {
        type = getArguments().getInt("type");
        filelsit=new ArrayList<>();
        folderlist = new ArrayList<>();

        fileAdapter = new ZiyuanAdapter(filelsit, getContext());
        recyIntera.setLayoutManager(new LinearLayoutManager(getContext()));


        bashiAdapter = new BashiAdapter(folderlist);
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

        if (type == 0) {
            presenter.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                    SharedPreferenceUtils.getToken(), "file", MyApp.version,"Android")));
            recyIntera.setAdapter(fileAdapter);
        } else {
            presenter.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                    SharedPreferenceUtils.getToken(), "folder", MyApp.version,"Android")));
            recyIntera.setAdapter(bashiAdapter);
        }


    }
    @Override
    protected void Errorcool(String msg) {
        super.showLoading(msg);
        mActivity.finish();
        startActivity(PwdLoginActivity.class);
        ToastUtil.makeShortText(context,msg);
    }
    @Override
    protected void showLoading(String msg) {
        super.showLoading(msg);

        if(listBean.isFolderCollection()){
            shoucang.setText("收藏");
            shoucang.setBackgroundResource(R.drawable.shoucang);
            listBean.setFolderCollection(false);
            ToastUtil.makeShortText(context,"取消收藏成功");
        }   else {
            shoucang.setText("已收藏");
            shoucang.setBackgroundResource(R.drawable.yiguanzhu);
            listBean.setFolderCollection(true);
            ToastUtil.makeShortText(context,"收藏成功");
        }
        if (type == 0) {
            presenter.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                    SharedPreferenceUtils.getToken(), "file", MyApp.version,"Android")));

        } else {
            presenter.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                    SharedPreferenceUtils.getToken(), "folder", MyApp.version,"Android")));

        }
    }
    @Override
    public void setVideoinfo(VideoinfoBean videoinfo) {

    }

    @Override
    public void setCollectVideo(CollectBean collectVideo) {

    }

    @Override
    public void onResume() {
        super.onResume();
        filelsit.clear();
        folderlist.clear();
        if (type == 0) {
            presenter.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                    SharedPreferenceUtils.getToken(), "file", MyApp.version,"Android")));

        } else {
            presenter.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                    SharedPreferenceUtils.getToken(), "folder", MyApp.version,"Android")));

        }
    }

    @Override
    public void setMyCollect(ZiyuanBean collectVideo) {
        folderlist.clear();
        filelsit.clear();
        if(collectVideo.getResult()!=null){
            if (collectVideo.getResult().size() > 0) {
                linearModle.setVisibility(View.GONE);
                recyIntera.setVisibility(View.VISIBLE);
                if(type==0){
                    filelsit.addAll(collectVideo.getResult());
                    fileAdapter.notifyDataSetChanged();
                }else{
                    Log.d("moxun", "setMyCollect: ");
                    folderlist.addAll(collectVideo.getResult());
                    bashiAdapter.notifyDataSetChanged();
                }

            } else {
                linearModle.setVisibility(View.VISIBLE);
                recyIntera.setVisibility(View.GONE);
            }
        }else{
            ToastUtil.makeLongText(getContext(),collectVideo.getHint());
        }

    }

    @Override
    protected VideoinPresenter<VideoinfoMvp.Videoinfo_View> createPresenter() {
        return new VideoinPresenter<>();
    }
}
