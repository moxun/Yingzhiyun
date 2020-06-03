package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.Mvp.SaveAnswer;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.ZuTiActivity;
import com.yingzhiyun.yingquxue.adapter.ChooseAdapter;
import com.yingzhiyun.yingquxue.adapter.WenKeChooseAdapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;

public class CelectFragment extends SimpleFragment {
    private final int anInt;
    private final String title1;
    private final int sum;
    private final TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean stemBeanListBean;
    private final String type;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.cloose_content)
    FlexibleRichTextView clooseContent;
    @BindView(R.id.recy_select)
    RecyclerView recySelect;
    @BindView(R.id.now_position)
    TextView nowPosition;
    private ChooseAdapter chooseAdapter;
    private WenKeChooseAdapter wenKeChooseAdapter;
    private ZuTiActivity SaveA;
    private SaveAnswer saveAnswer;
    public static boolean iscollection=false;
    public CelectFragment(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean stemBeanListBean, int position, String title, int totalSize,String type) {
        super();
        this.stemBeanListBean =stemBeanListBean;
        anInt = position;
        title1 = title;
        this.sum = totalSize;
        this.type=type;
    }
    @Override
    public int createLayoutId() {
        return R.layout.fraggment_celect;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        saveAnswer = (ZuTiActivity) context;
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() throws JSONException {
        iscollection=stemBeanListBean.isCollection();
        title.setText(title1);
        nowPosition.setText(anInt+"");
        size.setText("/"+sum);
        Tokenizer.setImageLabels("[img]\\u[/img]", "<img height=\\h width=\\w>\\u</img>");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <stemBeanListBean.getStemContents().size() ; i++)
            if (stemBeanListBean.getStemContents().get(i).getContentType().equals("text")) {
                stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
            } else if (stemBeanListBean.getStemContents().get(i).getContentType().equals("latex")) {
                stringBuilder.append("$$");
                String replace = stemBeanListBean.getStemContents().get(i).getContent();
                stringBuilder.append(replace);
                stringBuilder.append("$$");
            } else {
                stringBuilder.append("<img height=");
                String inputText1 = stemBeanListBean.getStemContents().get(i).getHeight();
                String inputText2 = stemBeanListBean.getStemContents().get(i).getHeight();
                int num1 = Integer.valueOf(inputText1);
                int num2 = Integer.valueOf(inputText2);
                num1 = num1 + num2;

                String inputText = stemBeanListBean.getStemContents().get(i).getHeight();
                String inputText3 = stemBeanListBean.getStemContents().get(i).getWidth();
                int num = Integer.valueOf(inputText);
                int num3 = Integer.valueOf(inputText3);
                num = num + num3;
                stringBuilder.append(num1);
                int b = Integer.parseInt(stemBeanListBean.getStemContents().get(i).getWidth() + stemBeanListBean.getStemContents().get(i).getWidth());
                stringBuilder.append(" width=" + num + ">");
                stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
                stringBuilder.append("</img>");
            }

        String replace = stringBuilder.toString();
        Log.d("moxun", "initData: "+replace);
        clooseContent.setText(replace);
        chooseAdapter = new ChooseAdapter(getContext(), stemBeanListBean.getOptionsList(),type);
        recySelect.setLayoutManager(new LinearLayoutManager(getContext()));
        recySelect.setAdapter(chooseAdapter);
        chooseAdapter.setOnItemListener(new ChooseAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos, TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.OptionsListBean projectc,String s) {
                if(type.equals("RadioSelect")){
                    chooseAdapter.setDefSelect(pos);
                    saveAnswer.sendMathMessage(stemBeanListBean,projectc.getValue(),anInt,type);
                }else {
                    saveAnswer.sendMathMessage(stemBeanListBean,s,anInt,type);
                }

            }
        });
    }
}
