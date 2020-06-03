package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.annotation.SuppressLint;
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

public class ExamineShowFragment extends SimpleFragment {
    private final MyTiBean.ResultBean resultBean;
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
    private final String lol;


    public ExamineShowFragment(MyTiBean.ResultBean resultBean, String type) {
        super();
        this.resultBean = resultBean;

        lol = type;

    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_examineshow;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() throws JSONException {
        Tokenizer.setImageLabels("[img]\\u[/img]", "<img height=\\h width=\\w>\\u</img>");
        wrongTime.setText(resultBean.getAddTime());
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < resultBean.getStemContents().size(); i++) {
            if (resultBean.getStemContents().get(i).getContentType().equals("text")) {
                string.append(resultBean.getStemContents().get(i).getContent());
            }else if (resultBean.getStemContents().get(i).getContentType().equals("latex")){
                string.append("$$");
                string.append(resultBean.getStemContents().get(i).getContent());
                string.append("$$");
            }else{
                string.append("<img height=");
                String inputText1=resultBean.getStemContents().get(i).getHeight();
                String inputText2=resultBean.getStemContents().get(i).getHeight();
                int num1= Integer.valueOf(inputText1);
                int num2= Integer.valueOf(inputText2);
                num1=num1+num2;

                String inputText=resultBean.getStemContents().get(i).getHeight();
                String inputText3=resultBean.getStemContents().get(i).getWidth();
                int num= Integer.valueOf(inputText);
                int num3= Integer.valueOf(inputText3);
                num=num+num3;
                string.append(num1);

                string.append(" width="+num+">");
                string.append(resultBean.getStemContents().get(i).getContent());
                string.append("</img>");
            }
        }
        clooseContent.setText(string.toString().replace("\\n", "\n").replace("\\r", "\r"));
        for (int i = 0; i < resultBean.getOptionsList().size(); i++) {
            if (resultBean.getOptionsList().get(i).isUserSelected()) {
                value = resultBean.getOptionsList().get(i).getValue();
            }
        }
       StringBuilder rightkry=new StringBuilder();
        for (int i = 0; i < resultBean.getRightKey().size(); i++) {
            if (resultBean.getRightKey().get(i).getContentType().equals("text")) {
                rightkry.append(resultBean.getRightKey().get(i).getContent());
            } else {
                rightkry.append("$$");
                rightkry.append(resultBean.getRightKey().get(i).getContent());
                rightkry.append("$$");
            }
        }
        if (value != null) {
            rightKey.setText("本题的正确答案是：" + rightkry.toString() + "您的答案是" + value);
        } else {
            rightKey.setText("本题的正确答案是：" + rightkry.toString());
        }
        if (lol.equals("collect")) {
            textType.setText("收藏");
            huida.setVisibility(View.GONE);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < resultBean.getAnalysis().size(); i++) {
            if (resultBean.getAnalysis().get(i).getContentType().equals("text")) {
                stringBuilder.append(resultBean.getAnalysis().get(i).getContent());
            } else if(resultBean.getAnalysis().get(i).getContentType().equals("latex")){
                stringBuilder.append("$$");
                stringBuilder.append(resultBean.getAnalysis().get(i).getContent());
                stringBuilder.append("$$");
            }else{
                stringBuilder.append("<img height=");
                String inputText1=resultBean.getAnalysis().get(i).getHeight();
                String inputText2=resultBean.getAnalysis().get(i).getHeight();
                int num1= Integer.valueOf(inputText1);
                int num2= Integer.valueOf(inputText2);
                num1=num1+num2;

                String inputText=resultBean.getAnalysis().get(i).getHeight();
                String inputText3=resultBean.getAnalysis().get(i).getWidth();
                int num= Integer.valueOf(inputText);
                int num3= Integer.valueOf(inputText3);
                num=num+num3;
                stringBuilder.append(num1);

                stringBuilder.append(" width="+num+">");
                stringBuilder.append(resultBean.getAnalysis().get(i).getContent());
                stringBuilder.append("</img>");
            }
        }
        analysis.setText(stringBuilder.toString());
        if (resultBean.getOptionsList().size() > 0) {
            analysisCeAdapter = new examineshowAdapter(getContext(), resultBean.getOptionsList());
            recySelect.setLayoutManager(new LinearLayoutManager(getContext()));
            recySelect.setAdapter(analysisCeAdapter);
        }
    }
}
