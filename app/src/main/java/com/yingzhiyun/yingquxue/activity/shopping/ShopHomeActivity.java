package com.yingzhiyun.yingquxue.activity.shopping;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.ShopAdapter;
import com.yingzhiyun.yingquxue.adapter.ShopModleAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopHomeActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.favouter)
    ImageView favouter;
    @BindView(R.id.answer_card)
    ImageView answerCard;
    @BindView(R.id.recy_modle)
    RecyclerView recyModle;
    @BindView(R.id.recy_shop)
    RecyclerView recyShop;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    private ArrayList<ModuleBean> moduleBeans;

    @Override
    protected void initData() throws ParseException {

        moduleBeans = new ArrayList<>();
        moduleBeans.add(new ModuleBean("文具", R.mipmap.icon_wenju, 0));
        moduleBeans.add(new ModuleBean("周边", R.mipmap.icon_zhoubian, 0));
        moduleBeans.add(new ModuleBean("教辅", R.mipmap.icon_jiaofu, 0));
        moduleBeans.add(new ModuleBean("特惠", R.mipmap.icon_tehui, 0));
        recyModle.setLayoutManager(new GridLayoutManager(this, 4));
        recyModle.setAdapter(new ShopModleAdapter(moduleBeans));

        ArrayList<String> strings = new ArrayList<>();
        strings.add("ssss"); strings.add("ssss"); strings.add("ssss");
        recyShop.setNestedScrollingEnabled(false);

        recyShop.setLayoutManager(new LinearLayoutManager(this));
        recyShop.setAdapter(new ShopAdapter(strings,this));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_shophome;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.favouter, R.id.answer_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.favouter:

                break;
            case R.id.answer_card:
                break;
        }
    }


}
