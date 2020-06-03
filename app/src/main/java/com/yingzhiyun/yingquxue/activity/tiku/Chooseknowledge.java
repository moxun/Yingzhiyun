package com.yingzhiyun.yingquxue.activity.tiku;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.ZjietestMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BookinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ChapterListJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.KnowPointerJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.KnowledgeJson;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.MyExtendableListViewAdapter;

import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ZjieTestPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Chooseknowledge extends BaseActicity<ZjietestMvp.Zjietest_View, ZjieTestPresenter<ZjietestMvp.Zjietest_View>> implements ZjietestMvp.Zjietest_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.expend_list)
    RecyclerView expendList;
    @BindView(R.id.right_choose)
    TextView rightChoose;
    private ArrayList<KnowPointerBean.ResultBean> chapterListBeans;
    private MyExtendableListViewAdapter myExtendableListViewAdapter;
    private ArrayList<BooklistBean.ResultBean> chooseBooks;
    private ArrayList<Integer> integers;
    public static Chooseknowledge instance = null;
    public  ArrayList<KnowPointerBean.ResultBean.ListBeanXX.ListBeanX> listBeans = new ArrayList<>();
    private int id;

    @Override
    public void setChapterList(ChapterListBean chapterListBean,String title) {

    }

    @Override
    public void setBookList(BooklistBean booklistBean) {

    }

    @Override
    public void setKnowPointer(KnowPointerBean knowPointer) {

        if(knowPointer.getStatus()==200){
            chapterListBeans.addAll(knowPointer.getResult());

            myExtendableListViewAdapter.notifyDataSetChanged();
        }else{
            ToastUtil.makeLongText(this,knowPointer.getHint());
        }


    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {

    }

    @Override
    public void setbookinfo(BookinfoBean bookinfoBean) {

    }

    @Override
    protected ZjieTestPresenter<ZjietestMvp.Zjietest_View> createPresenter() {
        return new ZjieTestPresenter<>();
    }

    @Override
    protected void initData() {
        instance=this;
        chooseBooks = (ArrayList<BooklistBean.ResultBean>) getIntent().getExtras().getSerializable("list");
        id = getIntent().getExtras().getInt("id");
        integers = new ArrayList<>();
        for (int i = 0; i < chooseBooks.size(); i++) {
            integers.add(chooseBooks.get(i).getId());
        }
        chapterListBeans = new ArrayList<>();
        expendList.setLayoutManager(new LinearLayoutManager(this));
        myExtendableListViewAdapter = new  MyExtendableListViewAdapter(chapterListBeans,this);
        expendList.setAdapter(myExtendableListViewAdapter);
//        Log.d("------------", "initData: "+new Gson().toJson(new KnowPointerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken()
//                , integers, MyApp.version,"Android")));
            mPresentser.get(new Gson().toJson(new KnowPointerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken()
                    , integers, MyApp.version,"Android")));



    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_knowledge;
    }
    @Override
    public int choseeClor() {
        return R.color.white;
    }
    @OnClick({R.id.finish, R.id.right_choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.right_choose:
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",listBeans);
                bundle.putInt("id",id);
                if(SharedPreferenceUtils.getisLogin()){
                    if(listBeans.size()==0){
                        ToastUtil.makeShortText(this,"请至少选择一个知识点");
                    }else {
                        startActivity(ZujuanActivity.class,bundle);
                    }

                }else{
                    startActivity(PwdLoginActivity.class);
                }
                
                break;
        }
    }
}
