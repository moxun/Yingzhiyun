package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.expand.ExpandGroupItemEntity;
import com.yingzhiyun.yingquxue.base.adapter.expand.RecyclerExpandBaseAdapter;


public class PatrolGroupAdapter extends RecyclerExpandBaseAdapter<String, AllsubjectBean.ResultBean.DetailBean, RecyclerView.ViewHolder> {


	private setOnClickListener mSetOnClickListener;

	/**
	 * 悬浮标题栏被点击的时候，展开收起切换功能
	 */
	public void switchExpand(int adapterPosition) {
		int groupIndex = mIndexMap.get(adapterPosition).getGroupIndex();
		ExpandGroupItemEntity entity = mDataList.get(groupIndex);
		entity.setExpand(!entity.isExpand());
		notifyDataSetChanged();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == VIEW_TYPE_ITEM_TIME) {
			TitleItemHolder holder = new TitleItemHolder(
				LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_fasubject, parent, false));
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ExpandGroupItemEntity entity = (ExpandGroupItemEntity) v.getTag();
					entity.setExpand(!entity.isExpand());
					notifyDataSetChanged();
				}
			});
			return holder;
		} else {
			return new SubItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_subject, parent, false));
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreatePinnedViewHolder(ViewGroup parent, int viewType) {
		TitleItemHolder holder = (TitleItemHolder) super.onCreatePinnedViewHolder(parent, viewType);

		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (getItemViewType(position) == VIEW_TYPE_ITEM_TIME) {
			int groupIndex = mIndexMap.get(position).getGroupIndex();
			TitleItemHolder itemHolder = (TitleItemHolder) holder;
			itemHolder.itemView.setTag(mDataList.get(groupIndex));
			itemHolder.mTextTime.setText(mDataList.get(groupIndex).getParent());
			if(mDataList.get(groupIndex).getParent().equals("全部科目")){
				((TitleItemHolder) holder).imageView.setVisibility(View.GONE);
			}
		} else {
			SubItemHolder subHolder = (SubItemHolder) holder;
			int groupIndex = mIndexMap.get(position).getGroupIndex();
			int childIndex = mIndexMap.get(position).getChildIndex();
			AllsubjectBean.ResultBean.DetailBean subItem = mDataList.get(groupIndex).getChildList().get(childIndex);
			subHolder.mTextUsers.setText(subItem.getName());
			subHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mSetOnClickListener.setOnClickListener(subItem,mDataList.get(groupIndex).getParent()+subItem.getName());
				}
			});
		}
	}

	@Override
	public void onBindPinnedViewHolder(RecyclerView.ViewHolder holder, int position) {
		super.onBindPinnedViewHolder(holder, position);
		TitleItemHolder itemHolder = (TitleItemHolder) holder;

	}



	static class TitleItemHolder extends RecyclerView.ViewHolder {


		TextView mTextTime;
		ImageView imageView;

		TitleItemHolder(View itemView) {
			super(itemView);

			mTextTime = itemView.findViewById(R.id.item_pop_content);
			imageView = itemView.findViewById(R.id.deatile);
		}
	}

	static class SubItemHolder extends RecyclerView.ViewHolder {


		TextView mTextUsers;


		SubItemHolder(View itemView) {
			super(itemView);

			mTextUsers = itemView.findViewById(R.id.item_pop_content);

		}
	}
	public  interface  setOnClickListener{
		void setOnClickListener(AllsubjectBean.ResultBean.DetailBean musicBean,String name);


	}

	public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
		mSetOnClickListener = setOnClickListener;
	}
}
