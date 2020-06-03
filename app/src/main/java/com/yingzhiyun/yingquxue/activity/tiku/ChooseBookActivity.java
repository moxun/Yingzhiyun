package com.yingzhiyun.yingquxue.activity.tiku;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.ChooseBookAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseBookActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.recy_book)
    RecyclerView recyBook;
    private ChooseBookAdapter chooseBookAdapter;

    @Override
    protected void initData() {
        List<BooklistBean.ResultBean> list = (List<BooklistBean.ResultBean>) getIntent().getSerializableExtra("list");
        chooseBookAdapter = new ChooseBookAdapter(list);
        recyBook.setLayoutManager(new GridLayoutManager(this,3));
        recyBook.setAdapter(chooseBookAdapter);
        chooseBookAdapter.OnsetOnClickListener(new ChooseBookAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(BooklistBean.ResultBean musicBean) {
                Intent intent =getIntent();
                //这里使用bundle绷带来传输数据
                Bundle bundle =new Bundle();
                //传输的内容仍然是键值对的形式
                bundle.putSerializable("bean",musicBean);//回发的消息,hello world from secondActivity!
                intent.putExtras(bundle);
                setResult(0,intent);
                finish();

            }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_choosebook;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
