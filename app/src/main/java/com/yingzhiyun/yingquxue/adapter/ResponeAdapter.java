package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.localbean.TestBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.conversion.Conversionentrance;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.mine.AboutUSActivity;
import com.yingzhiyun.yingquxue.activity.mine.BrowsingActivity;
import com.yingzhiyun.yingquxue.activity.mine.FankuiActivity;
import com.yingzhiyun.yingquxue.activity.mine.MineTeacherActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyAiActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyBetListActivity;
import com.yingzhiyun.yingquxue.activity.mine.MycourseActivity;

import com.yingzhiyun.yingquxue.activity.mine.MyfavtourActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyinteractiveActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyorderActvity;
import com.yingzhiyun.yingquxue.activity.mine.SeetingsActivity;
import com.yingzhiyun.yingquxue.activity.mine.WalletActivity;
import com.yingzhiyun.yingquxue.activity.pay.RechargeActivity;
import com.yingzhiyun.yingquxue.activity.tiku.HudongActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class ResponeAdapter extends BaseAdapter<TestBean> {

    private final Context context;

    public ResponeAdapter(List<TestBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_respone;
    }

    @Override
    public void addAll(List<TestBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, TestBean testBean, int position) {
        holder.setText(R.id.item_title,testBean.getTitle());
        holder.setPic(R.id.item_image_record,testBean.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferenceUtils.getisLogin()){
                    if(testBean.getTitle().equals("我的关注")){

                        context.startActivity(new Intent(context, MineTeacherActivity.class));
                    }else if(testBean.getTitle().equals("我的收藏")){
                        context.startActivity(new Intent(context, MyfavtourActivity.class));
                    }else if(testBean.getTitle().equals("问题反馈")){
                        context.startActivity(new Intent(context, FankuiActivity.class));
                    }else if(testBean.getTitle().equals("关于我们")){
                        Intent intent = new Intent(context, AboutUSActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url","http://www.yingzhiyunwenhua.cn:65400/applicationAboutUs/1");
                        bundle.putString("name","关于我们");
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }else if(testBean.getTitle().equals("设置")){
                        context.startActivity(new Intent(context, SeetingsActivity.class));
                    }else if(testBean.getTitle().equals("浏览记录")){
                        context.startActivity(new Intent(context, BrowsingActivity.class));
                    }else if(testBean.getTitle().equals("我的钱包")){
                        context.startActivity(new Intent(context, WalletActivity.class));
                    }else  if(testBean.getTitle().equals("学习天地")){
                        context.startActivity(new Intent(context, MyinteractiveActivity.class));
                    }else if(testBean.getTitle().equals("我的押题卷")){
                        context.startActivity(new Intent(context, MyBetListActivity.class));
                    }else if(testBean.getTitle().equals("课程兑换")){
                        context.startActivity(new Intent(context, Conversionentrance.class));
                    }
                }else{
                    context.startActivity(new Intent(context, PwdLoginActivity.class));
                }

            }
        });
    }
}
