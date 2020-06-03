package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.units.PinnedHeaderExpandableListView;

import java.util.ArrayList;
import java.util.List;


public class PinnedHeaderExpandableAdapter extends BaseExpandableListAdapter implements PinnedHeaderExpandableListView.HeaderAdapter {

	private List<AllsubjectBean.ResultBean> groupData;
	private Context context;
	private PinnedHeaderExpandableListView listView;
	private LayoutInflater inflater;
	
	public PinnedHeaderExpandableAdapter(List<AllsubjectBean.ResultBean> groupData
			, Context context, PinnedHeaderExpandableListView listView){
		this.groupData = groupData; 

		this.context = context;
		this.listView = listView;
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groupData.get(groupPosition).getDetail().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
		View view = null;
        if (convertView != null) {  
            view = convertView;  
        } else {  
            view = createChildrenView();  
        }  
        TextView text = (TextView)view.findViewById(R.id.item_pop_content);
        text.setText(groupData.get(groupPosition).getDetail().get(childPosition).getName());
        return view;    
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groupData.get(groupPosition).getDetail().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groupData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
		View view = null;
        if (convertView != null) {  
            view = convertView;  
        } else {  
            view = createGroupView();  
        } 

        
        TextView text = (TextView)view.findViewById(R.id.item_pop_content);
        text.setText(groupData.get(groupPosition).getName());
        return view;  
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	private View createChildrenView() {
		return inflater.inflate(R.layout.item_pop_subject, null);
	}
	
	private View createGroupView() {
		return inflater.inflate(R.layout.item_pop_fasubject, null);
	}

	@Override
	public int getHeaderState(int groupPosition, int childPosition) {
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1
				&& !listView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}
	}

	@Override
	public void configureHeader(View header, int groupPosition,
                                int childPosition, int alpha) {
		String groupData =  this.groupData.get(groupPosition).getName();
		((TextView) header.findViewById(R.id.item_pop_content)).setText(groupData);
		
	}
	
	private SparseIntArray groupStatusMap = new SparseIntArray();
	
	@Override
	public void setGroupClickStatus(int groupPosition, int status) {
		groupStatusMap.put(groupPosition, status);
	}

	@Override
	public int getGroupClickStatus(int groupPosition) {
		if (groupStatusMap.keyAt(groupPosition)>=0) {
			return groupStatusMap.get(groupPosition);
		} else {
			return 0;
		}
	}
}
