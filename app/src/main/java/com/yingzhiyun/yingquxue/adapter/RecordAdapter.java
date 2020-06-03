package com.yingzhiyun.yingquxue.adapter;

        import android.view.View;

        import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
        import com.yingzhiyun.yingquxue.OkBean.localbean.TestBean;
        import com.yingzhiyun.yingquxue.R;
        import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

        import java.util.List;

public class RecordAdapter extends BaseAdapter<TestBean> {

    private setOnClickListener mSetOnClickListener;

    public RecordAdapter(List<TestBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_record;
    }

    @Override
    public void addAll(List<TestBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, TestBean testAdapter, int position) {
        holder.setText(R.id.item_title,testAdapter.getTitle());
        holder.setPic(R.id.item_image_record,testAdapter.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(testAdapter);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(TestBean testBean);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
