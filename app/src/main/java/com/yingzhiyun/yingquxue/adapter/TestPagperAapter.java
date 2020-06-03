package com.yingzhiyun.yingquxue.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.R;

import java.util.List;

public class TestPagperAapter extends BaseQuickAdapter<TestPaperListBean.ResultBean, BaseViewHolder> {

    public  List<TestPaperListBean.ResultBean> resultBeans;

    public TestPagperAapter(int layoutResId, @Nullable List<TestPaperListBean.ResultBean> data) {
        super(layoutResId, data);
        resultBeans =data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TestPaperListBean.ResultBean item) {
        helper.setText(R.id.item_test_title,item.getTitle());
        helper.setText(R.id.item_test_scope,item.getScope());

    }
}
