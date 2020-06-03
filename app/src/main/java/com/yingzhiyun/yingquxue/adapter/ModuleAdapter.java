package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.JiaocaiActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class ModuleAdapter extends BaseAdapter<ModuleBean> {

    private final Context context;

    public ModuleAdapter(List<ModuleBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_module;
    }

    @Override
    public void addAll(List<ModuleBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ModuleBean moduleBean, int position) {
            holder.setPic(R.id.ite_module_src,moduleBean.getImage());
            holder.setText(R.id.ite_module_title,moduleBean.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(SharedPreferenceUtils.getisLogin()){
                        //点击轮播图
                        if(moduleBean.getId()==19){
                            Intent intent = new Intent(context, EntranceActivity.class);

                            context.startActivity(intent);
                            return;
                        }
                        Intent intent = new Intent(context, JiaocaiActivity.class);
                        intent.putExtra("bean",moduleBean);
                        context.startActivity(intent);

                    }else{
                        Intent intent = new Intent(context, PwdLoginActivity.class);

                        context.startActivity(intent);
                    }

                }
            });
    }
}
