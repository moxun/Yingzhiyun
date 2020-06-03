package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.Mvp.SaveAnswer;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.LiberalartZutiActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ZuTiActivity;
import com.yingzhiyun.yingquxue.adapter.ChooseAdapter;
import com.yingzhiyun.yingquxue.adapter.WenKeChooseAdapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;

import org.json.JSONException;

import java.util.Objects;

import butterknife.BindView;

public class WenkeClectFragment extends SimpleFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.now_position)
    TextView nowPosition;
    @BindView(R.id.cloose_content)
    FlexibleRichTextView clooseContent;
    @BindView(R.id.recy_select)
    RecyclerView recySelect;
    private WenKeChooseAdapter wenKeChooseAdapter;
    private SaveAnswer saveAnswer;
    private String type;


    @Override
    public int createLayoutId() {
        return R.layout.fraggment_celect;
    }


    public  WenkeClectFragment(){
        super();
    }
    private  TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean stemBeanListBean;
    private  int anInt;
    private  String title1;
    private  int allsize;
    private  ViewPager view;

    public WenkeClectFragment(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean stemBeanListBean, int position, String title, int size, ViewPager viewPager) {
        super();
        this.stemBeanListBean = stemBeanListBean;
        anInt = position;
        title1 = title;
        allsize = size;
        view =viewPager;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //...
        saveAnswer = (ZuTiActivity) context;
    }
    @Override
    protected void initData() throws JSONException {
        type = stemBeanListBean.getType();
        title.setText(title1);
        nowPosition.setText(anInt+"");
        size.setText("/"+allsize);
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
        Log.d("moxun", "initData: "+stringBuilder.toString());
        clooseContent.setText(stringBuilder.toString());
        wenKeChooseAdapter = new WenKeChooseAdapter(getContext(), stemBeanListBean.getOptionsList(),type);
        recySelect.setLayoutManager(new LinearLayoutManager(getContext()));
        recySelect.setAdapter(wenKeChooseAdapter);




        wenKeChooseAdapter.setOnItemListener(new WenKeChooseAdapter.OnItemListener() {
        @Override
        public void onClick(View v, int pos, TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean.OptionsListBean projectc,String string) {
            if(type.equals("RadioSelect")){
                wenKeChooseAdapter.setDefSelect(pos);
                saveAnswer.sendWenMessage(stemBeanListBean,projectc.getValue(),anInt);
                view.setCurrentItem(anInt);
            }else {

                saveAnswer.sendWenMessage(stemBeanListBean,string,anInt);
            }



        }
    });
    }
}
