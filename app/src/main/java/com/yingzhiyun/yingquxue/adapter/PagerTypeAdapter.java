package com.yingzhiyun.yingquxue.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;

import java.util.List;

public class PagerTypeAdapter extends BaseQuickAdapter<TestPagperInfo.ResultBean.TestPaperOutlineBeanBean.ContentBean, BaseViewHolder> {
    public PagerTypeAdapter(int layoutResId, @Nullable List<TestPagperInfo.ResultBean.TestPaperOutlineBeanBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestPagperInfo.ResultBean.TestPaperOutlineBeanBean.ContentBean item) {
        helper.setText(R.id.item_name,item.getTitle());
        if(item.getTotalScore()==null){
            helper.setText(R.id.item_score,"共"+item.getSize()+"题");
        }else {

            helper.setText(R.id.item_score,"共"+item.getSize()+"题,"+item.getTotalScore()+"分");
        }

    }
}
