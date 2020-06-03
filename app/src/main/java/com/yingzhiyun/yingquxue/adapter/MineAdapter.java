package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MyAliveActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyExamActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyLiveActivity;
import com.yingzhiyun.yingquxue.activity.mine.MycourseActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyinteractiveActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TiRecordActivirty;
import com.yingzhiyun.yingquxue.activity.tiku.WrongListActivity;
import com.yingzhiyun.yingquxue.activity.tiku.WrongtitleActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class MineAdapter extends BaseAdapter<ModuleBean> {

    private final Context context;

    public MineAdapter(List<ModuleBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_mine;
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
                    if(position==0){
                        context.startActivity(new Intent(context, MyAliveActivity.class));
                    }else if(position==1){
                        context.startActivity(new Intent(context, WrongtitleActivity.class).putExtra("id", 0));
                    }else if(position==2){
                        context.startActivity(new Intent(context, MyExamActivity.class));
                    }else if(position==3){
                        context.startActivity(new Intent(context, TiRecordActivirty.class));
                    }
                }else{
                    context.startActivity(new Intent(context, PwdLoginActivity.class));
                }

            }
        });
    }
}
