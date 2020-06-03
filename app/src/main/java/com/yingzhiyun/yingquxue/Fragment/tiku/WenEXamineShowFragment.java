package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.DeleteFragmentAdapter;
import com.yingzhiyun.yingquxue.adapter.TiFragmentADapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;

public class WenEXamineShowFragment extends SimpleFragment {
    private final MyTiBean.ResultBean stemBeanListBean;
    private final String lol;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.handler)
    ImageButton handler;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private TiFragmentADapter tiFragmentADapter;
    private int id4ItemBank;
    private int i;

    public WenEXamineShowFragment(MyTiBean.ResultBean resultBean, String type) {
        super();
        this.stemBeanListBean =resultBean;
        lol = type;
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
        Log.d("-----------", "initData: "+stringBuilder.toString().replace("\\n", "\n").replace("\\r", "\r"));
        content.setText(stringBuilder.toString().replace("\\n", "\n").replace("\\r", "\r"));
        fragments = new ArrayList<>();
        for (int i = 0; i < stemBeanListBean.getStemList().size(); i++) {
            fragments.add(new WenAnalsisShow(stemBeanListBean.getStemList().get(i),lol));
        }
        tiFragmentADapter = new TiFragmentADapter(getChildFragmentManager(), fragments);
        viewpager.setAdapter(tiFragmentADapter);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                i = position;


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
