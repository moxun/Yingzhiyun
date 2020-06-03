package com.yingzhiyun.yingquxue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.JsonBean.BankCombinationJson;
import com.yingzhiyun.yingquxue.OkBean.KnowledgeBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static me.nereo.multi_image_selector.MultiImageSelectorFragment.TAG;

public class QTYPEADAPTER extends BaseAdapter<KnowledgeBean.ResultBean.QuestionTypeBean> {

    private final Context context;
    private final List<KnowledgeBean.ResultBean.QuestionTypeBean> questionTypeBeans;

    private TextView editText;
    public  ArrayList<BankCombinationJson.DetailBean> detailBeans;
    private final ArrayList<View> views;
    public ArrayList<ArrayList<View>> listview = new ArrayList<>();
    private String text;
    private final ArrayList<TagFlowLayout> tagFlowLayouts;
    private int id;

    private String name;
    private int ol;

    public QTYPEADAPTER(List<KnowledgeBean.ResultBean.QuestionTypeBean> dataList, Context context) {
        super(dataList);
        this.context = context;
        questionTypeBeans =dataList;
        detailBeans = new ArrayList<>();
        views = new ArrayList<>();
        tagFlowLayouts = new ArrayList<>();
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_qtypes;
    }

    @Override
    public void addAll(List<KnowledgeBean.ResultBean.QuestionTypeBean> list, int page) {

    }


    @SuppressLint("LongLogTag")
    @Override
    public void createHolder(ViewHolder holder, KnowledgeBean.ResultBean.QuestionTypeBean questionTypeBean, int position) {
        id = questionTypeBean.getId();
        name = questionTypeBean.getName();
        ol = position;
        holder.setText(R.id.question_title, questionTypeBean.getName());
        TagFlowLayout tagFlowLayout = holder.itemView.findViewById(R.id.qe_flowlayout);
        tagFlowLayout.setFocusableInTouchMode(false); //设置不需要焦点
        tagFlowLayout.requestFocus();
        List<KnowledgeBean.ResultBean.QuestionTypeBean.ArrayBean> array = questionTypeBean.getArray();
        if(array.size()<4){
            array.add(new KnowledgeBean.ResultBean.QuestionTypeBean.ArrayBean(0));
        }



        tagFlowLayout.setAdapter(new TagAdapter<KnowledgeBean.ResultBean.QuestionTypeBean.ArrayBean>(array) {




            @Override
            public View getView(FlowLayout parent, int position, KnowledgeBean.ResultBean.QuestionTypeBean.ArrayBean questionTypeBean) {

                editText = (EditText) LayoutInflater.from(context).inflate(R.layout.item_edit,
                        tagFlowLayout, false);
                if (position < 3) {
                    editText.setText(questionTypeBean.getValue() + "");
                    editText.setCursorVisible(false);
                    editText.setFocusable(false);
                    editText.setFocusableInTouchMode(false);
                    editText.requestFocus();
                    editText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setSelectedList(position);
                        }
                    });
                    
                    views.add(editText);
                } else if(position==3){
                    editText = (EditText) LayoutInflater.from(context).inflate(R.layout.item_edit,
                            tagFlowLayout, false);
                    editText.setCursorVisible(false);
                    editText.setHint("自定义");
                    editText.setFocusable(true);
                    editText.setFocusableInTouchMode(true);
                    editText.requestFocus();
                    Log.d(TAG, "getView: ");
                    editText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setSelectedList(position);
                        }
                    });
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            Log.d(TAG, "afterTextChanged: "+questionTypeBeans.size());
                            if(!editable.toString().equals("")){
                                questionTypeBean.setValue(Integer.parseInt(editable.toString()));
                            }

                            array.set(3,questionTypeBean);
                            questionTypeBeans.set(ol,new KnowledgeBean.ResultBean.QuestionTypeBean(name,id,array));
                        }
                    });

                    views.add(editText);

                    return editText;
                }


                return editText;
            }
        });
        listview.add(views);

            tagFlowLayouts.add(tagFlowLayout);


//        Set<Integer> selectedList = tagFlowLayout.getSelectedList();
//        if (selectedList.size() > 0) {
//            Iterator<Integer> it = selectedList.iterator();
//            while (it.hasNext()) {
//                Integer next = it.next();
//
//                TextView view = (TextView) views.get(next);
//                text = view.getText().toString();
//                detailBeans.add(new BankCombinationJson.DetailBean(questionTypeBean.getId()+"",text));
//
//            }
//
//        }

    }

    @SuppressLint("LongLogTag")
    public void getId() {
        detailBeans.clear();
        for (int i = 0; i < tagFlowLayouts.size(); i++) {
            if(tagFlowLayouts.get(i).getSelectedList().size()>0){
                Integer next = tagFlowLayouts.get(i).getSelectedList().iterator().next();
                int value = questionTypeBeans.get(i).getArray().get(next).getValue();

                if(value!=0){
                    detailBeans.add(new BankCombinationJson.DetailBean(questionTypeBeans.get(i).getId()+"",value+""));
                }

            }

        }

    }


}
