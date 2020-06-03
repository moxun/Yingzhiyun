package com.yingzhiyun.yingquxue.activity.tiku;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yingzhiyun.yingquxue.Fragment.tiku.ExamineShowFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.WenEXamineShowFragment;
import com.yingzhiyun.yingquxue.Mvp.WrongMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.CollectionTiJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.DeleteWrongJson;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.DeleteFragmentAdapter;
import com.yingzhiyun.yingquxue.adapter.TiFragmentADapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.presenter.WrongPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamineShowActivity extends BaseActicity<WrongMvp.Wrong_View, WrongPresenter<WrongMvp.Wrong_View>> implements WrongMvp.Wrong_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.answer_card)
    ImageView answerCard;
    @BindView(R.id.moxun)
    ImageView favouter;
    @BindView(R.id.readerViewPager)
    ViewPager readerViewPager;
    private ArrayList<Fragment> fragments;
    private TiFragmentADapter tiFragmentADapter;
    private int id4ItemBank;
    private int i;
    private String type;

    @Override
    protected void initData() {
        List<MyTiBean.ResultBean> resultBeans = (List<MyTiBean.ResultBean>) getIntent().getSerializableExtra("list");
        type = getIntent().getStringExtra("type");
        int position = getIntent().getIntExtra("position", 0);
        int id = getIntent().getIntExtra("id", 0);
        Log.d("moxun", "initData: "+resultBeans.size());
        answerCard.setVisibility(View.GONE);
        toolTitle.setText("详情");

        favouter.setImageResource(R.mipmap.delectall);

        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < resultBeans.size(); i++) {
            if(resultBeans.get(i).getType().equals("RadioSelect")){
                fragments.add(new ExamineShowFragment(resultBeans.get(i),type));
            }else{
                fragments.add(new WenEXamineShowFragment(resultBeans.get(i),type));
            }

        }
        tiFragmentADapter = new TiFragmentADapter(getSupportFragmentManager(), fragments);
        readerViewPager.setAdapter(tiFragmentADapter);
        readerViewPager.setCurrentItem(position);
        id4ItemBank = resultBeans.get(0).getId4ItemBank();
        readerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                i = position;
                id4ItemBank = resultBeans.get(position).getId4ItemBank();

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



    @OnClick({R.id.finish, R.id.moxun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.moxun:
                if(type.equals("collect")){
                    mPresentser.getCollectionti(new Gson().toJson(new CollectionTiJson(MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken(),SharedPreferenceUtils.getsubject_id()+"",id4ItemBank+"")));
                }else{
                    Log.d("moxun", "onViewClicked: "+id4ItemBank);
                    mPresentser.getdeleteWrong(new Gson().toJson(new DeleteWrongJson(SharedPreferenceUtils.getUserid(), MyApp.version,"Android",SharedPreferenceUtils.getToken(),id4ItemBank)));
                }


                break;
        }
    }

    @Override
    public void setWronglist(WrongtitleBean wronglist) {

    }

    @Override
    public void setWrong(MyTiBean myTiBean) {

    }

    @Override
    public void setdeleteWrong(CollectionTiBean collectionTiBean) {
        if(collectionTiBean.getStatus()==200){
            delPage();
        }

        ToastUtil.makeLongText(this,collectionTiBean.getHint());
    }

    @Override
    public void setCollection(MyTiBean myTiBean) {

    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {
        delPage();
        ToastUtil.makeLongText(this,"题目已经删除");
    }

    @Override
    public void setTiRecordlist(WrongtitleBean wronglist) {

    }

    @Override
    protected WrongPresenter<WrongMvp.Wrong_View> createPresenter() {
        return new WrongPresenter<>();
    }
    /**
     * 删除当前页面
     */
    public void delPage(){
        int position = readerViewPager.getCurrentItem();//获取当前页面位置
        if(fragments.size()!=0){
            fragments.remove(position);//删除一项数据源中的数据
            if(fragments.size()==0){
                finish();
            }

        }else{
            finish();
        }


    }


}
