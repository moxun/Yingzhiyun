package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.AnalysisCeAdapter;
import com.yingzhiyun.yingquxue.adapter.WenAnalysisCe;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;

import org.json.JSONException;

import butterknife.BindView;

public class WenAnalsisCelect extends SimpleFragment {
    private final ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean stemBeanListBean;
    private final int nowposition;
    private final String string;
    private final int allsize;
    private final String string1;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.now_position)
    TextView nowPosition;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.relayout)
    RelativeLayout relayout;
    @BindView(R.id.cloose_content)
    FlexibleRichTextView clooseContent;
    @BindView(R.id.recy_select)
    RecyclerView recySelect;
    @BindView(R.id.fenge)
    View fenge;
    @BindView(R.id.huida)
    TextView huida;
    @BindView(R.id.right_key)
   FlexibleRichTextView rightKey;
    @BindView(R.id.re_Choose)
    RelativeLayout reChoose;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.tv_answer)
    FlexibleRichTextView tvAnswer;
    @BindView(R.id.re_tiankong)
    RelativeLayout reTiankong;
    @BindView(R.id.analysis)
    FlexibleRichTextView analysis;
    private WenAnalysisCe analysisCeAdapter;

    public WenAnalsisCelect(ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean stemListBean, int position, String string, int size,String type) {
        super();
        this.stemBeanListBean = stemListBean;
        nowposition = position;
        this.string = string;
        allsize = size;
        string1 =type;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_analysis;
    }

    @Override
    protected void initData() throws JSONException {
        title.setText(string);
        nowPosition.setText(nowposition + "");
        size.setText("/" + allsize);
        Tokenizer.setImageLabels("[img]\\u[/img]", "<img height=\\h width=\\w>\\u</img>");
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < stemBeanListBean.getStemContents().size(); i++) {
            if (stemBeanListBean.getStemContents().get(i).getContentType().equals("text")) {
                string.append(stemBeanListBean.getStemContents().get(i).getContent());
            } else if(stemBeanListBean.getStemContents().get(i).getContentType().equals("latex")){
                string.append("$$");
                string.append(stemBeanListBean.getStemContents().get(i).getContent());
                string.append("$$");
            }else{
                string.append("<img height=");
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
                string.append(num1);

                string.append(" width="+num+">");
                string.append(stemBeanListBean.getStemContents().get(i).getContent());
                string.append("</img>");
            }
        }
        clooseContent.setText(string.toString());
        if (string1.equals("RadioSelect")||string1.equals("MultiSelect")) {
            reChoose.setVisibility(View.VISIBLE);
            reTiankong.setVisibility(View.GONE);
            if(stemBeanListBean.getStatus()!=null){
                if (stemBeanListBean.getStatus().equals("true")) {
                    huida.setText("回答正确");
                    huida.setTextColor(Color.parseColor("#666666"));
                    relayout.setBackgroundColor(getResources().getColor(R.color.ColorRight));
                } else {
                    huida.setText("回答错误");
                    huida.setTextColor(Color.parseColor("#DB253B"));
                    relayout.setBackgroundColor(getResources().getColor(R.color.ColorWrong));
                }
                rightKey.setText(stemBeanListBean.getMsg());
            }


        } else {
            reChoose.setVisibility(View.GONE);
            reTiankong.setVisibility(View.VISIBLE);
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < stemBeanListBean.getRightKey().size(); i++) {
                if (stemBeanListBean.getRightKey().get(i).getContentType().equals("text")) {
                    answer.append(stemBeanListBean.getRightKey().get(i).getContent());
                } else if(stemBeanListBean.getRightKey().get(i).getContentType().equals("latex")){
                    answer.append("$$");
                    answer.append(stemBeanListBean.getRightKey().get(i).getContent());
                    answer.append("$$");
                }else{
                    answer.append("<img height=");
                    String inputText1=stemBeanListBean.getRightKey().get(i).getHeight();
                    String inputText2=stemBeanListBean.getRightKey().get(i).getHeight();
                    int num1= Integer.valueOf(inputText1);
                    int num2= Integer.valueOf(inputText2);
                    num1=num1+num2;

                    String inputText=stemBeanListBean.getRightKey().get(i).getHeight();
                    String inputText3=stemBeanListBean.getRightKey().get(i).getWidth();
                    int num= Integer.valueOf(inputText);
                    int num3= Integer.valueOf(inputText3);
                    num=num+num3;
                    answer.append(num1);

                    answer.append(" width="+num+">");
                    answer.append(stemBeanListBean.getRightKey().get(i).getContent());
                    answer.append("</img>");
                }
            }
            tvAnswer.setText(answer.toString());
        }


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

                stringBuilder.append(" width="+num+">");
                stringBuilder.append(stemBeanListBean.getAnalysis().get(i).getContent());
                stringBuilder.append("</img>");
            }
        }
        analysis.setText(stringBuilder.toString());
        if (stemBeanListBean.getOptionsList().size() > 0) {
            analysisCeAdapter = new WenAnalysisCe(getContext(), stemBeanListBean.getOptionsList());
            recySelect.setLayoutManager(new LinearLayoutManager(getContext()));
            recySelect.setAdapter(analysisCeAdapter);
        }
    }
}
