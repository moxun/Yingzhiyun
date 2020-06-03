package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.examineshowAdapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;

import org.json.JSONException;

import butterknife.BindView;

public class WenAnalsisShow extends SimpleFragment {
    private final String string;
    public MyTiBean.ResultBean.StemListBean stemBeanListBean;
    @BindView(R.id.wrong_time)
    TextView wrongTime;
    @BindView(R.id.cloose_content)
    FlexibleRichTextView clooseContent;
    @BindView(R.id.recy_select)
    RecyclerView recySelect;
    @BindView(R.id.huida)
    TextView huida;
    @BindView(R.id.right_key)
    FlexibleRichTextView rightKey;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.tv_answer)
    FlexibleRichTextView tvAnswer;
    @BindView(R.id.analysis)
    FlexibleRichTextView analysis;
    @BindView(R.id.text_type)
    TextView textType;
    private String value;
    private examineshowAdapter analysisCeAdapter;

    public WenAnalsisShow(MyTiBean.ResultBean.StemListBean stemListBean, String lol) {
        super();
        stemBeanListBean = stemListBean;
        string = lol;
    }


    @Override
    public int createLayoutId() {
        return R.layout.fragment_examineshow;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() throws JSONException {
        if (stemBeanListBean != null) {

            StringBuilder stringtt = new StringBuilder();
            for (int i = 0; i < stemBeanListBean.getStemContents().size(); i++) {

                if(stemBeanListBean.getStemContents().get(i).getContentType().equals("text")){
                    stringtt.append(stemBeanListBean.getStemContents().get(i).getContent());
                }else if (stemBeanListBean.getStemContents().get(i).getContentType().equals("latex")){
                    stringtt.append("$$");
                    stringtt.append(stemBeanListBean.getStemContents().get(i).getContent());
                    stringtt.append("$$");
                }else{
                    stringtt.append("<img height=");
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
                    stringtt.append(num1);
                    int b= Integer.parseInt(stemBeanListBean.getStemContents().get(i).getWidth()+stemBeanListBean.getStemContents().get(i).getWidth());
                    stringtt.append(" width="+num+">");
                    stringtt.append(stemBeanListBean.getStemContents().get(i).getContent());
                    stringtt.append("</img>");
                }
            }

            clooseContent.setText(stringtt.toString());
            huida.setText("回答错误");
            huida.setTextColor(Color.parseColor("#DB253B"));


            for (int i = 0; i < stemBeanListBean.getOptionsList().size(); i++) {
                if (stemBeanListBean.getOptionsList().get(i).isUserSelected()) {
                    value = stemBeanListBean.getOptionsList().get(i).getValue();
                }
            }
            if (value != null) {
                rightKey.setText("本题的正确答案是：" + stemBeanListBean.getRightKey().get(0).getContent() + "您的答案是" + value);
            } else {
                rightKey.setText("本题的正确答案是：" + stemBeanListBean.getRightKey().get(0).getContent());
            }
            if (string.equals("collect")) {
                textType.setText("收藏");
                huida.setVisibility(View.GONE);
            }

            Tokenizer.setImageLabels("[img]\\u[/img]", "<img height=\\h width=\\w>\\u</img>");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < stemBeanListBean.getAnalysis().size(); i++) {
                if (stemBeanListBean.getAnalysis().get(i).getContentType().equals("text")) {
                    stringBuilder.append(stemBeanListBean.getAnalysis().get(i).getContent());
                } else if(stemBeanListBean.getAnalysis().get(i).getContentType().equals("latex")){
                    stringBuilder.append("$$");
                    stringBuilder.append(stemBeanListBean.getAnalysis().get(i).getContent());
                    stringBuilder.append("$$");
                }else{
                    stringBuilder.append("<img height=");
                    String inputText1=stemBeanListBean.getAnalysis().get(i).getHeight();
                    String inputText2=stemBeanListBean.getAnalysis().get(i).getHeight();
                    int num1= Integer.valueOf(inputText1);
                    int num2= Integer.valueOf(inputText2);
                    num1=num1+num2;

                    String inputText=stemBeanListBean.getAnalysis().get(i).getHeight();
                    String inputText3=stemBeanListBean.getAnalysis().get(i).getWidth();
                    int num= Integer.valueOf(inputText);
                    int num3= Integer.valueOf(inputText3);
                    num=num+num3;
                    stringBuilder.append(num1);
                    int b= Integer.parseInt(stemBeanListBean.getAnalysis().get(i).getWidth()+stemBeanListBean.getAnalysis().get(i).getWidth());
                    stringBuilder.append(" width="+num+">");
                    stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
                    stringBuilder.append("</img>");
                }
            }
            analysis.setText(stringBuilder.toString());
            if (stemBeanListBean.getOptionsList().size() > 0) {

                analysisCeAdapter = new examineshowAdapter(getContext(), stemBeanListBean.getOptionsList());
                recySelect.setLayoutManager(new LinearLayoutManager(getContext()));
                recySelect.setAdapter(analysisCeAdapter);
            }
        }

    }
}