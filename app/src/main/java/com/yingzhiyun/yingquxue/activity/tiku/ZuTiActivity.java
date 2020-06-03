package com.yingzhiyun.yingquxue.activity.tiku;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.tiku.CelectFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.GapFragment;
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

import butterknife.BindView;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class ZuTiActivity extends BaseActicity<TestPaperInfoMvp.TestPaperinfo_View, TestPagperInfoPresenter<TestPaperInfoMvp.TestPaperinfo_View>>
        implements TestPaperInfoMvp.TestPaperinfo_View, SaveAnswer {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.readerViewPager)
    ViewPager readerViewPager;
    @BindView(R.id.time)
    Chronometer time;
    @BindView(R.id.answer_card)
    ImageView answerCard;
    @BindView(R.id.favouter)
    ImageView favouter;
    private int id;
    private boolean isLastPage = false;
    private boolean isDragPage = false;
    private boolean canJumpPage = true;

    private List<TestPagperInfo.ResultBean.DaTiBeanListBean> daTiBeanList=new ArrayList<>();
    private ArrayList<Fragment> fragments;
    TiFragmentADapter tiFragmentADapter;
    int position = 0;
    int count = 1;
    private LinkedHashMap<Integer, String> integerStringLinkedHashMap;
    int thid;
    private LiberalartZutiActivity.DataChange dataChange;
    private int page = 0, cument = 0;
    public static ZuTiActivity instance = null;
    private TestPagperInfo bean;
    private ArrayList<TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean> stemBeanListBeans;
    private boolean iscollection;
    private int id4ItemBank;
    private int collect;
    private String juantype;
    private ArrayList<TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean> stemBeanListBeans1;
    private TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean stemBeanListBean;
    private ArrayList<String>  iochoose=new ArrayList<>();
    @Override
    protected void initData() {
        stemBeanListBeans = new ArrayList<>();
        instance = this;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        thid = intent.getIntExtra("thid", 0);
        bean = (TestPagperInfo) intent.getSerializableExtra("bean");
        juantype = intent.getStringExtra("juantype");
        integerStringLinkedHashMap = new LinkedHashMap<>();
        fragments = new ArrayList<>();
        daTiBeanList.clear();
        daTiBeanList = bean.getResult().getDaTiBeanList();
        stemBeanListBeans1 = new ArrayList<>();
        stemBeanListBeans1.clear();

        for (int i = 0; i < daTiBeanList.size(); i++) {
            fragments.add(new TopicTypeFragment(bean.getResult().getDaTiBeanList().get(i)));
            stemBeanListBeans.add(new TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean());
            for (int j = 0; j < daTiBeanList.get(i).getStemBeanList().size(); j++) {
                position++;
                if (daTiBeanList.get(i).getStemBeanList().get(j).getType().equals("RadioSelect")||daTiBeanList.get(i).getStemBeanList().get(j).getType().equals("MultiSelect")) {
                    stemBeanListBean = daTiBeanList.get(i).getStemBeanList().get(j);
                    stemBeanListBeans1.add(stemBeanListBean);
                    this.stemBeanListBeans.add(daTiBeanList.get(i).getStemBeanList().get(j));
                    fragments.add(new CelectFragment(daTiBeanList.get(i).getStemBeanList().get(j), position, bean.getResult().getTitle(), bean.getResult().getTotalSize(),daTiBeanList.get(i).getStemBeanList().get(j).getType()));
                } else if(daTiBeanList.get(i).getStemBeanList().get(j).getType().equals("OneToManyR")||daTiBeanList.get(i).getStemBeanList().get(j).getType().equals("OneToManyQ")){
                    this.stemBeanListBeans.add(daTiBeanList.get(i).getStemBeanList().get(j));
                    fragments.add(new WenZhangFragment(daTiBeanList.get(i).getStemBeanList().get(j), bean.getResult().getTitle(), bean.getResult().getTotalSize(), readerViewPager, position));
                }else if(daTiBeanList.get(i).getStemBeanList().get(j).getType().equals("OneToOne")){
                    this.stemBeanListBeans.add(daTiBeanList.get(i).getStemBeanList().get(j));
                    fragments.add(new GapFragment(daTiBeanList.get(i).getStemBeanList().get(j), bean.getResult().getTotalSize(), bean.getResult().getTitle(),position));
                }
            }
        }
        readerViewPager.setOffscreenPageLimit(position);
        tiFragmentADapter = new TiFragmentADapter(getSupportFragmentManager(), fragments);
        readerViewPager.setAdapter(tiFragmentADapter);
        readerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (isLastPage && isDragPage && positionOffsetPixels == 0) {   //当前页是最后一页，并且是拖动状态，并且像素偏移量为0
                    if (canJumpPage) {

                        canJumpPage = false;
                        SerializableHashMap myMap = new SerializableHashMap();
                        myMap.setMap(integerStringLinkedHashMap);//将hashmap数据添加到封装的myMap中
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("map", myMap);
                        String chronometerSeconds = getChronometerSeconds(time);
                        int num1 = Integer.parseInt(chronometerSeconds);
                        int num2 = Integer.parseInt("1000");
                        int num3 = num1*num2;


                        startActivity(new Intent(ZuTiActivity.this, DatiKaActivity.class).putExtra("id",  bean.getResult().getId()).putExtras(bundle).putExtra("time", num3+"").putExtra("type","math").putExtra("juantype",juantype).putExtra("listsize",stemBeanListBeans1.size()).putExtra("maplist",integerStringLinkedHashMap.size()));
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
                    collect = position;
                    id4ItemBank = stemBeanListBeans.get(position).getId4ItemBank();
                    Log.d("moxun", "onPageSelected: "+stemBeanListBeans.get(position).isCollection());
                    if(stemBeanListBeans.get(position).isCollection()){
                        iscollection =true;

                        favouter.setImageResource(R.mipmap.icon_shoucang);
                    }else{

                        iscollection =false;
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
    public void setTestPaperinfo(TestPagperInfo testPaperinfo) {


    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {
        ToastUtil.makeLongText(this,collectionti.getHint());
    }
    public interface DataChange {
        void setDataChange(int th);
    }

    public void setData(LiberalartZutiActivity.DataChange dataChange) {
        this.dataChange = dataChange;
    }
    /**
     *
     * @param cmt  Chronometer控件
     * @return 小时+分钟+秒数  的所有秒数
     */
    public  static String getChronometerSeconds(Chronometer cmt) {
        int totalss = 0;
        String string = cmt.getText().toString();
        if(string.length()==7){

            String[] split = string.split(":");
            String string2 = split[0];
            int hour = Integer.parseInt(string2);
            int Hours =hour*3600;
            String string3 = split[1];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[2]);
            totalss = Hours+Mins+SS;
            return String.valueOf(totalss);
        }

        else if(string.length()==5){

            String[] split = string.split(":");
            String string3 = split[0];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[1]);

            totalss =Mins+SS;
            return String.valueOf(totalss);
        }
        return String.valueOf(totalss);


    }

    @Override
    protected TestPagperInfoPresenter<TestPaperInfoMvp.TestPaperinfo_View> createPresenter() {
        return new TestPagperInfoPresenter<>();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        canJumpPage = true;
    }
    @Override
    public void sendMathMessage(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean msg, String key, int anint,String type) {
        if(type.equals("RadioSelect")){
            integerStringLinkedHashMap.put(msg.getTh(), key);
            readerViewPager.setCurrentItem(anint + 1);
        }else {


            integerStringLinkedHashMap.put(msg.getTh(), key);
        }

    }
    @Override
    public void backIntent(int th) {
        page=0;
        for (int i = 0; i < daTiBeanList.size(); i++) {
            page++;
            for (int j = 0; j < daTiBeanList.get(i).getStemBeanList().size(); j++) {
                page++;
                if (th == daTiBeanList.get(i).getStemBeanList().get(j).getTh()) {
                    cument = page;
                }
            }
        }
        if(readerViewPager!=null){
            readerViewPager.setCurrentItem(cument - 1);
        }

    }
    @Override
    public void sendWenMessage(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean msg, String key, int positon) {
        integerStringLinkedHashMap.put(msg.getTh(), key);
        Log.d("------------", "sendWenMessage: "+key);
    }
    @OnClick({R.id.finish, R.id.answer_card, R.id.favouter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                time.stop();
                finish();
                break;
            case R.id.answer_card:
                SerializableHashMap myMap = new SerializableHashMap();
                myMap.setMap(integerStringLinkedHashMap);//将hashmap数据添加到封装的myMap中
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", myMap);
                startActivity(new Intent(ZuTiActivity.this, DatiKaActivity.class).putExtra("id",  bean.getResult().getId()).putExtras(bundle).putExtra("time", time.getText().toString()).putExtra("type","math").putExtra("juantype",juantype));
                break;
            case R.id.favouter:
                if(iscollection){
                    iscollection=false;
                    favouter.setImageResource(R.mipmap.favorite);
                    stemBeanListBeans.get(collect).setCollection(false);
                }else {
                    iscollection=true;
                    stemBeanListBeans.get(collect).setCollection(true);

                    favouter.setImageResource(R.mipmap.icon_shoucang);
                }
                mPresentser.getCollectionti(new Gson().toJson(new CollectionTiJson(MyApp.version,"Android",SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken(),SharedPreferenceUtils.getsubject_id()+"",id4ItemBank+"")));
                break;
        }
    }
}
