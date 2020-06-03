package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.TiFragmentADapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;

public class WenAnalsisFragment extends SimpleFragment {

    private final ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean stemBeanListBean;
    public final String string;
    private final int anInt;
    private final  String type;
    private final int nowposition;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.handler)
    ImageButton handler;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> fragments1;
    private TiFragmentADapter tiFragmentADapter;
    private ArrayList<Fragment> wrongfragments=new ArrayList<>();
    int position = 0;

    public WenAnalsisFragment(ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean stemBeanListBean, String title, int totalSize, int position,String type) {
        super();
        this.stemBeanListBean = stemBeanListBean;
        string = title;
        anInt = totalSize;
        this.type=type;
        nowposition = position;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_wenzhang;
    }

    @Override
    protected void initData() throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stemBeanListBean.getStemContents().size(); i++) {
            if (stemBeanListBean.getStemContents().get(i).getContentType().equals("text")) {
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

                stringBuilder.append(" width="+num+">");
                stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
                stringBuilder.append("</img>");
            }
        }

        content.setText(stringBuilder.toString().replace("\\n", "\n").replace("\\r", "\r"));

        fragments1 = new ArrayList<>();
        for (int i = 0; i < stemBeanListBean.getStemList().size(); i++) {

                position++;
                fragments1.add(new WenAnalsisCelect(stemBeanListBean.getStemList().get(i),position,string,stemBeanListBean.getStemList().size(),stemBeanListBean.getStemList().get(i).getType()));
                if(stemBeanListBean.getStemList().get(i).getStatus()!=null){
                    if(!stemBeanListBean.getStemList().get(i).getStatus().equals("true")){
                        wrongfragments.add(new WenAnalsisCelect(stemBeanListBean.getStemList().get(i),position,string,stemBeanListBean.getStemList().size(),"celect"));
                    }
                }



        }
        if(type.equals("all")){
            tiFragmentADapter = new TiFragmentADapter(getChildFragmentManager(), fragments1);
        }else{
            tiFragmentADapter = new TiFragmentADapter(getChildFragmentManager(), wrongfragments);
        }

        viewpager.setAdapter(tiFragmentADapter);
    }
}
