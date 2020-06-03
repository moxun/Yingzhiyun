package com.yingzhiyun.yingquxue.activity.tiku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yingzhiyun.yingquxue.Fragment.tiku.WenAnalsisFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.WenZhangFragment;
import com.yingzhiyun.yingquxue.Mvp.ExamineMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.CollectionTiJson;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.TiFragmentADapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ExaminePresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WenAnalysisActivity extends BaseActicity<ExamineMvp.Examine_View, ExaminePresenter<ExamineMvp.Examine_View>>
        implements ExamineMvp.Examine_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.answer_card)
    ImageView answerCard;
    @BindView(R.id.favouter)
    ImageView favouter;
    @BindView(R.id.readerViewPager)
    ViewPager readerViewPager;
    private ArrayList<Fragment> fragments;
    private ArrayList<Fragment> wrongfragments;
    private TiFragmentADapter tiFragmentADapter;
    ExamineBean bean;
    private List<ExamineBean.ResultBean.DaTiBeanListBean> daTiBeanList;
    int position = 0;
    private String type;
    private List<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean> colldaTiBeanList=new ArrayList<>();
    boolean iscollection=false;
    private int id4ItemBank;
    @Override
    public void setExamineBean(ExamineBean examineBean) {

    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {

    }

    @Override
    protected ExaminePresenter<ExamineMvp.Examine_View> createPresenter() {
        return new ExaminePresenter<>();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        bean = (ExamineBean) intent.getSerializableExtra("bean");
        type = intent.getStringExtra("type");
        fragments = new ArrayList<>();
        daTiBeanList = bean.getResult().getDaTiBeanList();

        fragments = new ArrayList<>();
        daTiBeanList = bean.getResult().getDaTiBeanList();
        for (int i = 0; i < daTiBeanList.size(); i++) {

            for (int j = 0; j < daTiBeanList.get(i).getStemBeanList().size(); j++) {
                position++;
                colldaTiBeanList.add(daTiBeanList.get(i).getStemBeanList().get(j));
                fragments.add(new WenAnalsisFragment(daTiBeanList.get(i).getStemBeanList().get(j), bean.getResult().getTitle(), bean.getResult().getTotalSize(), position,type));
            }
        }

        tiFragmentADapter = new TiFragmentADapter(getSupportFragmentManager(), fragments);
        readerViewPager.setAdapter(tiFragmentADapter);
        readerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                id4ItemBank = colldaTiBeanList.get(position).getId4ItemBank();
                if(colldaTiBeanList.get(position).isCollection()){
                    iscollection=true;
                    favouter.setImageResource(R.mipmap.icon_shoucang);
                }else{
                    iscollection=false;
                    favouter.setImageResource(R.mipmap.favorite);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_analysis;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.finish, R.id.answer_card, R.id.favouter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.answer_card:
                break;
            case R.id.favouter:
                if(iscollection){
                    iscollection=false;
                    favouter.setImageResource(R.mipmap.favorite);

                }else {
                    iscollection=true;
                    favouter.setImageResource(R.mipmap.icon_shoucang);
                }
                mPresentser.getCollectionti(new Gson().toJson(new CollectionTiJson(MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken(),SharedPreferenceUtils.getsubject_id()+"",id4ItemBank+"")));
                break;
        }
    }
}
