package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.util.Log;
import android.widget.TextView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;

import org.json.JSONException;

import butterknife.BindView;

public class GapFragment extends SimpleFragment {


    private final int position;
    private final TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean bean;
    private final int anInt;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.now_position)
    TextView nowPosition;
    @BindView(R.id.cloose_content)
    FlexibleRichTextView clooseContent;
    private final String title1;

    public GapFragment(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean stemBeanListBean, int i, String title, int position) {
        super();
        this.bean = stemBeanListBean;
        this.position = i;
        title1 = title;
        anInt =position;
    }


    @Override
    public int createLayoutId() {
        return R.layout.fragment_gap;
    }

    @Override
    protected void initData() throws JSONException {
        title.setText(title1);

        nowPosition.setText(anInt+"");
        size.setText("/"+position);
        StringBuilder stringBuilder = new StringBuilder();
        Tokenizer.setImageLabels("[img]\\u[/img]", "<img height=\\h width=\\w>\\u</img>");
        for (int i = 0; i <bean.getStemContents().size() ; i++) {
            if(bean.getStemContents().get(i).getContentType().equals("text")){
                stringBuilder.append(bean.getStemContents().get(i).getContent());
            }else if (bean.getStemContents().get(i).getContentType().equals("latex")){
                stringBuilder.append("$$");
                stringBuilder.append(bean.getStemContents().get(i).getContent());
                stringBuilder.append("$$");
            }else{
                stringBuilder.append("<img height=");
                String inputText1=bean.getStemContents().get(i).getHeight();
                String inputText2=bean.getStemContents().get(i).getHeight();
                int num1= Integer.valueOf(inputText1);
                int num2= Integer.valueOf(inputText2);
                num1=num1+num2;

                String inputText=bean.getStemContents().get(i).getHeight();
                String inputText3=bean.getStemContents().get(i).getWidth();
                int num= Integer.valueOf(inputText);
                int num3= Integer.valueOf(inputText3);
                num=num+num3;
                stringBuilder.append(num1);
                int b= Integer.parseInt(bean.getStemContents().get(i).getWidth()+bean.getStemContents().get(i).getWidth());
                stringBuilder.append(" width="+num/2+">");
                stringBuilder.append(bean.getStemContents().get(i).getContent());
                stringBuilder.append("</img>");
            }

        }
        Log.d("moxun", "initData: "+stringBuilder.toString());
        clooseContent.setText(stringBuilder.toString().replace("\\n", "\n").replace("\\r", "\r"));
    }
}
