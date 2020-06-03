package com.yingzhiyun.yingquxue.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.homepager.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

public class YueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BODY_TYPE = 00002;
    private static final int FOOT_TYPE = 00003;
    private final Context context;
    private int defItem = -1;
    private int footCount = 1;//尾部个数，后续可以自己拓展
    private LayoutInflater mLayoutInflater;
    public List<Integer> listData = new ArrayList<>();

    private OnItemListener onItemListener;
    private int ppp=0;

    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }
    public YueAdapter(Context context, List<Integer> listData) {
        //this.context=context;
        mLayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.context =context;
    }

    private int getBodySize() {
        return listData.size();
    }



    private boolean isFoot(int position) {
        return footCount != 0 && (position >= (getBodySize() ));
    }

    @Override
    public int getItemViewType(int position) {
        if (isFoot(position)) {
            return FOOT_TYPE;
        } else {
            return BODY_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BODY_TYPE:
                return new BodyViewHolder(mLayoutInflater.inflate(R.layout.item_blance, parent, false));
            case FOOT_TYPE:
                return new FootViewHolder(mLayoutInflater.inflate(R.layout.item_other_balance, parent, false));
            default:
                return null;
        }

    }

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility", "NewApi"})
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (defItem == position) {
            //选中状态
            if (holder instanceof BodyViewHolder) {
                ((BodyViewHolder) holder).money.setTextColor(Color.parseColor("#1091E9"));
                ((BodyViewHolder) holder).back.setBackgroundResource(R.mipmap.icon_blance_choose);
            }else {
                ((FootViewHolder) holder).edmoney.setTextColor(Color.parseColor("#1091E9"));
                ((FootViewHolder) holder).relativeLayout.setBackgroundResource(R.mipmap.icon_blance_choose);
            }


        } else {
            if (holder instanceof BodyViewHolder) {
                ((BodyViewHolder) holder).money.setTextColor(Color.parseColor("#000000"));
                ((BodyViewHolder) holder).back.setBackgroundResource(R.mipmap.icon_blance_nochoose);
            }else {
                ((FootViewHolder) holder).edmoney.setTextColor(Color.parseColor("#000000"));
                ((FootViewHolder) holder).relativeLayout.setBackgroundResource(R.mipmap.icon_blance_nochoose);
            }

        }
        if (holder instanceof BodyViewHolder) {
            ((BodyViewHolder) holder).money.setText(listData.get(position)+"元");
            ((BodyViewHolder) holder).qubi.setText(listData.get(position)+"趣学币");
            ((BodyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemListener.onClick(position,listData.get(position));
                }
            });

        } else if (holder instanceof FootViewHolder) {

            ((FootViewHolder) holder).edmoney.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().startsWith("0") ) {
                        ((FootViewHolder) holder).edmoney.setText("1");
                        ((FootViewHolder) holder).edmoney.setSelection(1);
                    }
                    if(!s.toString().equals("")){
                        onItemListener.OnEdClick(Integer.parseInt(((FootViewHolder) holder).edmoney.getText().toString()));
                    }

                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((FootViewHolder) holder).edmoney.setFocusable(true);
                    ((FootViewHolder) holder).edmoney.setFocusableInTouchMode(true);
                    ((FootViewHolder) holder).edmoney.requestFocus();
                    onItemListener.onClick(position,0);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return  getBodySize() + footCount;
    }

    private static class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class BodyViewHolder extends RecyclerView.ViewHolder {

        private final TextView money;
        private final TextView qubi;
        private final LinearLayout back;

        public BodyViewHolder(View itemView) {
            super(itemView);

            money = itemView.findViewById(R.id.money);
            qubi = itemView.findViewById(R.id.qubi);
            back = itemView.findViewById(R.id.back);

        }
    }

    private static class FootViewHolder extends RecyclerView.ViewHolder {
        private final EditText edmoney;
        private final RelativeLayout relativeLayout;


        public FootViewHolder(View itemView) {
            super(itemView);
            edmoney = itemView.findViewById(R.id.edmoney);
            relativeLayout = itemView.findViewById(R.id.back);

        }
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick( int pos, Integer s);
        void OnEdClick(Integer s);
    }
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
