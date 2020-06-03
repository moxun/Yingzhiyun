package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MyAliveActivity;
import com.yingzhiyun.yingquxue.activity.classfiy.ClassZiyuanActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.CourseActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyExamActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TiRecordActivirty;
import com.yingzhiyun.yingquxue.activity.tiku.WrongtitleActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class VipModleAdapter  extends BaseAdapter<ModuleBean> {

    private final Context context;

    public VipModleAdapter(List<ModuleBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_vipmodle;
    }

    @Override
    public void addAll(List<ModuleBean> list, int page) {

    }

    @Override
    public void createHolder(BaseAdapter.ViewHolder holder, ModuleBean moduleBean, int position) {
        holder.setPic(R.id.ite_module_src,moduleBean.getImage());
        holder.setText(R.id.ite_module_title,moduleBean.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferenceUtils.getisLogin()){
                    if(position==0){
                        Intent intent =new  Intent(context, CourseActivity.class).putExtra("isVip", true);
                       context. startActivity(intent);
                    }else if(position==1){
                        context. startActivity(new Intent(context, ClassZiyuanActivity.class));
                    }else if(position==2){

                    }else if(position==3){

                    }
                }else{
                    context.startActivity(new Intent(context, PwdLoginActivity.class));
                }

            }
        });
    }
}

