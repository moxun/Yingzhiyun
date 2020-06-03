package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;

import org.json.JSONException;

import butterknife.BindView;

public class TopicTypeFragment extends SimpleFragment {


    public final TestPagperInfo.ResultBean.DaTiBeanListBean daTiBeanListBean;
    @BindView(R.id.topic_title)
    TextView topicTitle;
    @BindView(R.id.topiv_info)
    TextView topivInfo;
    private StringBuilder stringBuilder;

    public TopicTypeFragment(TestPagperInfo.ResultBean.DaTiBeanListBean daTiBeanListBean) {
        super();
        this.daTiBeanListBean =daTiBeanListBean;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_topictype;
    }

    @Override
    protected void initData() throws JSONException {
        topicTitle.setText(daTiBeanListBean.getTitle());
        stringBuilder = new StringBuilder();
        stringBuilder.append("本题共").append(daTiBeanListBean.getStemBeanList().size()).append("题")
                .append(",一共").append(daTiBeanListBean.getTotalScore()).append("分。");
        topivInfo.setText(stringBuilder.toString());
    }
}
