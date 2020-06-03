package com.yingzhiyun.yingquxue.activity.tiku;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.yingzhiyun.yingquxue.Fragment.tiku.TopicTypeFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.WenZhangFragment;
import com.yingzhiyun.yingquxue.Mvp.SaveAnswer;
import com.yingzhiyun.yingquxue.Mvp.TestPaperInfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.CollectionTiJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TestpagperinfoJson;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.TiFragmentADapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.TestPagperInfoPresenter;
import com.yingzhiyun.yingquxue.units.SerializableHashMap;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiberalartZutiActivity extends BaseActicity<TestPaperInfoMvp.TestPaperinfo_View, TestPagperInfoPresenter<TestPaperInfoMvp.TestPaperinfo_View>>
        implements TestPaperInfoMvp.TestPaperinfo_View, SaveAnswer {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.time)
    Chronometer time;
    @BindView(R.id.readerViewPager)
    ViewPager readerViewPager;
    @BindView(R.id.answer_card)
    ImageView answerCard;
    @BindView(R.id.favouter)
    ImageView favouter;
    private int id;
    private boolean isLastPage = false;
    private boolean isDragPage = false;
    private boolean canJumpPage = true;
    private List<TestPagperInfo.ResultBean.DaTiBeanListBean> daTiBeanList;

    private ArrayList<Fragment> fragments;
    private TiFragmentADapter tiFragmentADapter;
    int position = 0;
    int page = -1;
    int count = 1;
    int childpage = 0;

    private LinkedHashMap<Integer, String> integerStringLinkedHashMap = new LinkedHashMap<>();
    public static LiberalartZutiActivity instance = null;
    private DataChange dataChange;
    public static int bean = 0;
    private List<TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean> colldaTiBeanList;
    boolean iscollection=false;
    private int id4ItemBank;
    private int collect;

    @Override
    public void setTestPaperinfo(TestPagperInfo testPaperinfo) {
        if(testPaperinfo.getStatus()==200){
            fragments = new ArrayList<>();
            daTiBeanList = testPaperinfo.getResult().getDaTiBeanList();
            for (int i = 0; i < daTiBeanList.size(); i++) {
                fragments.add(new TopicTypeFragment(testPaperinfo.getResult().getDaTiBeanList().get(i)));
                colldaTiBeanList.add(new TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean());
                for (int j = 0; j < daTiBeanList.get(i).getStemBeanList().size(); j++) {
                    position++;
                    colldaTiBeanList.add(daTiBeanList.get(i).getStemBeanList().get(j));
                    fragments.add(new WenZhangFragment(daTiBeanList.get(i).getStemBeanList().get(j), testPaperinfo.getResult().getTitle(), testPaperinfo.getResult().getTotalSize(), readerViewPager, position));
                }
            }
            tiFragmentADapter = new TiFragmentADapter(getSupportFragmentManager(), fragments);
            readerViewPager.setAdapter(tiFragmentADapter);
            readerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (isLastPage && isDragPage && positionOffsetPixels == 0) {
                        if (canJumpPage) {
                            canJumpPage = false;
                            SerializableHashMap myMap = new SerializableHashMap();
                            myMap.setMap(integerStringLinkedHashMap);//将hashmap数据添加到封装的myMap中
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("map", myMap);
                            startActivity(new Intent(LiberalartZutiActivity.this, WenExamineActivity.class).putExtra("id", id).putExtras(bundle).putExtra("time", time.getText().toString()));
                        }
                    }

                }

                @Override
                public void onPageSelected(int position) {
                    if (count == 1) {
                        time.start();
                        count++;
                    }
                    isLastPage = position == fragments.size() - 1;

                    if(!(fragments.get(position) instanceof TopicTypeFragment)){
                        favouter.setEnabled(true);
                        collect =position;
                        id4ItemBank = colldaTiBeanList.get(position).getId4ItemBank();
                        if(colldaTiBeanList.get(position).isCollection()){
                            iscollection=true;
                            favouter.setImageResource(R.mipmap.icon_shoucang);
                        }else{
                            iscollection=false;
                            favouter.setImageResource(R.mipmap.favorite);
                        }
                    }else{
                        favouter.setEnabled(false);
                        favouter.setImageResource(R.mipmap.favorite);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    isDragPage = state == 1;
                }
            });
        }else{
            ToastUtil.makeLongText(this,testPaperinfo.getHint());
        }

    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {
        ToastUtil.makeLongText(this,collectionti.getHint());
    }

    @Override
    protected TestPagperInfoPresenter<TestPaperInfoMvp.TestPaperinfo_View> createPresenter() {
        return new TestPagperInfoPresenter<>();
    }

    @Override
    protected void initData() {

        favouter.setEnabled(false);
        instance = this;
        colldaTiBeanList=new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        mPresentser.getTestPagperInfo(new Gson().toJson(new TestpagperinfoJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), id + "", MyApp.version,"Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_zuti;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        canJumpPage = true;
    }

    @OnClick({R.id.finish, R.id.answer_card, R.id.favouter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.answer_card:
                SerializableHashMap myMap = new SerializableHashMap();
                myMap.setMap(integerStringLinkedHashMap);//将hashmap数据添加到封装的myMap中
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", myMap);
                startActivity(new Intent(LiberalartZutiActivity.this, DatiKaActivity.class).putExtra("id", id).putExtras(bundle).putExtra("time", time.getText().toString()).putExtra("type", "wen"));
                break;
            case R.id.favouter:
                if(iscollection){
                    iscollection=false;
                    favouter.setImageResource(R.mipmap.favorite);
                    colldaTiBeanList.get(collect).setCollection(false);
                }else {
                    iscollection=true;
                    favouter.setImageResource(R.mipmap.icon_shoucang);
                    colldaTiBeanList.get(collect).setCollection(true);
                }
                mPresentser.getCollectionti(new Gson().toJson(new CollectionTiJson(MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken(),id+"",id4ItemBank+"")));
                break;
        }
    }

    public interface DataChange {
        void setDataChange(int th);
    }

    public void setData(DataChange dataChange) {
        this.dataChange = dataChange;
    }


    @Override
    public void sendMathMessage(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean msg, String key, int position, String type) {

    }

    @Override
    public void backIntent(int th) {
        page = -1;
        childpage = 0;
        for (int i = 0; i < daTiBeanList.size(); i++) {
            page++;
            for (int j = 0; j < daTiBeanList.get(i).getStemBeanList().size(); j++) {
                page++;
                childpage = 0;
                for (int k = 0; k < daTiBeanList.get(i).getStemBeanList().get(j).getStemList().size(); k++) {
                    childpage++;
                    if (daTiBeanList.get(i).getStemBeanList().get(j).getStemList().get(k).getTh() == th) {
                        readerViewPager.setCurrentItem(page);
                        dataChange.setDataChange(childpage);
                        bean = childpage;
                    }
                }
            }
        }
    }

    @Override
    public void sendWenMessage(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean msg, String key, int anint) {
        integerStringLinkedHashMap.put(msg.getTh(), key);

    }

}
