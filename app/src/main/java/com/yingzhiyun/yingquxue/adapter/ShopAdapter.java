package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseAdapter<String > {

    private final Context context;

    public ShopAdapter(List<String> dataList, Context context) {
        super(dataList);
        this.context=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String s, int position) {
        RecyclerView recy_shoplist = holder.itemView.findViewById(R.id.recy_shoplist);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ssss"); strings.add("ssss"); strings.add("ssss");strings.add("ssss"); strings.add("ssss"); strings.add("ssss");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        ShopListAdapter shopListAdapter = new ShopListAdapter(strings);
        recy_shoplist.setLayoutManager(gridLayoutManager);
        recy_shoplist.setAdapter(shopListAdapter);
    }
}
