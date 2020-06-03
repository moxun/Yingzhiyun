package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.Mvp.SaveAnswer;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.tiku.LiberalartZutiActivity;
import com.yingzhiyun.yingquxue.activity.tiku.WenExamineActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ZuTiActivity;
import com.yingzhiyun.yingquxue.adapter.TiFragmentADapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;
import com.yingzhiyun.yingquxue.units.SerializableHashMap;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;

import butterknife.BindView;

import static me.nereo.multi_image_selector.MultiImageSelectorFragment.TAG;

public class WenZhangFragment extends SimpleFragment  {

    private final TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean stemBeanListBean;
    public final String string;
    private final int anInt;
    private final ViewPager view;
    private final int nowposition;

    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.handler)
    ImageButton handler;
    @BindView(R.id.viewpager)
    ViewPager readerViewPager;
    private int page = 0, cument = 0;
    private TiFragmentADapter tiFragmentADapter;
    int position = 0;
    int count=1;
    private boolean isLastPage = false;
    private boolean isDragPage = false;
    private boolean canJumpPage = true;
    private ArrayList<Fragment> fragments1;
    private int a;

    public WenZhangFragment(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean stemBeanListBean, String title, int totalSize,ViewPager viewPager,int position) {
        super();
        this.stemBeanListBean = stemBeanListBean;
        string = title;
        anInt = totalSize;
        view =viewPager;
        nowposition =position;

    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_wenzhang;
    }


    @Override
    protected void initData() throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        Tokenizer.setImageLabels("[img]\\u[/img]", "<img height=\\h width=\\w>\\u</img>");
        for (int i = 0; i <stemBeanListBean.getStemContents().size() ; i++) {
            if(stemBeanListBean.getStemContents().get(i).getContentType().equals("text")){
                stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
            }else if (stemBeanListBean.getStemContents().get(i).getContentType().equals("latex")){
                stringBuilder.append("$$");
                stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
                stringBuilder.append("$$");
            }else{
                stringBuilder.append("<img height=");
                String inputText1=stemBeanListBean.getStemContents().get(i).getHeight();
                String inputText2=stemBeanListBean.getStemContents().get(i).getHeight();
                int num1= Integer.valueOf(inputText1);
                int num2= Integer.valueOf(inputText2);
                num1=num1+num2;

                String inputText=stemBeanListBean.getStemContents().get(i).getHeight();
                String inputText3=stemBeanListBean.getStemContents().get(i).getWidth();
                int num= Integer.valueOf(inputText);
                int num3= Integer.valueOf(inputText3);
                num=num+num3;
                stringBuilder.append(num1);
                int b= Integer.parseInt(stemBeanListBean.getStemContents().get(i).getWidth()+stemBeanListBean.getStemContents().get(i).getWidth());
                stringBuilder.append(" width="+num+">");
                stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
                stringBuilder.append("</img>");
            }
        }
        String s = Matcher.quoteReplacement(stringBuilder.toString());
        content.setText(s.replaceAll("\\\\n","").replaceAll("\\\\r",""));

        fragments1 = new ArrayList<>();

        for (int i = 0; i < stemBeanListBean.getStemList().size(); i++) {
                position++;
                fragments1.add(new WenkeClectFragment(stemBeanListBean.getStemList().get(i),position,string,stemBeanListBean.getStemList().size(),readerViewPager));

        }
        readerViewPager.setOffscreenPageLimit(position);
        tiFragmentADapter = new TiFragmentADapter(getChildFragmentManager(), fragments1);
        readerViewPager.setAdapter(tiFragmentADapter);
        readerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }




}
