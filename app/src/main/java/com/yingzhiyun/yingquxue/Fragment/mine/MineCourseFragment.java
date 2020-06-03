package com.yingzhiyun.yingquxue.Fragment.mine;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.MyCollectJson;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.BashiAdapter;
import com.yingzhiyun.yingquxue.adapter.BashiinfoAdapter;
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.VideoinPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MineCourseFragment extends BaseFragment<VideoinfoMvp.Videoinfo_View, VideoinPresenter<VideoinfoMvp.Videoinfo_View>> implements VideoinfoMvp.Videoinfo_View {
    @BindView(R.id.recy_book)
    RecyclerView recyBook;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private ArrayList<ZiyuanBean.ResultBean> fileList=new ArrayList<>();
    private ArrayList<ZiyuanBean.ResultBean> folserList=new ArrayList<>();
    private ZiyuanAdapter videoAdapter;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_mycourse;
    }

    @Override
    protected void initData() {
        videoAdapter = new ZiyuanAdapter(fileList, context);
        BashiAdapter bashiAdapter = new BashiAdapter(folserList);
        presenter.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                SharedPreferenceUtils.getToken(), "video", MyApp.version, "Android")));
    }

    @Override
    public void setVideoinfo(VideoinfoBean videoinfo) {

    }

    @Override
    public void setCollectVideo(CollectBean collectVideo) {

    }

    @Override
    public void setMyCollect(ZiyuanBean collectVideo) {
        if(collectVideo.getStatus()==200){
            if (collectVideo.getResult().size() > 0) {
                linearModle.setVisibility(View.GONE);
                recyBook.setVisibility(View.VISIBLE);
                fileList.addAll(collectVideo.getResult());
                videoAdapter.notifyDataSetChanged();

            } else {

                recyBook.setVisibility(View.GONE);
            }
        }else if(collectVideo.getStatus()==511){
            mActivity.finish();
            startActivity(PwdLoginActivity.class);
        }
    }


    @Override
    protected VideoinPresenter<VideoinfoMvp.Videoinfo_View> createPresenter() {
        return new VideoinPresenter<>();
    }
}
