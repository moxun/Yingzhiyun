package com.yingzhiyun.yingquxue.activity.tiku;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.ZjietestMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BookinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.KnowledgeJson;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.CelectBookAdapter;
import com.yingzhiyun.yingquxue.adapter.CelecthowAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ZjieTestPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CelectBookActivity  extends BaseActicity<ZjietestMvp.Zjietest_View, ZjieTestPresenter<ZjietestMvp.Zjietest_View>> implements ZjietestMvp.Zjietest_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.recy_book)
    RecyclerView recyBook;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    private int id;
    public static CelectBookActivity instance = null;
    private ArrayList<BooklistBean.ResultBean> resultBeans;
    private CelectBookAdapter celecthowAdapter;
    private  ArrayList<BooklistBean.ResultBean> chooseBooks=new ArrayList<>();
    @Override
    public void setChapterList(ChapterListBean chapterListBean,String ll) {
    }
    @Override
    public void setBookList(BooklistBean booklistBean) {
        if(booklistBean.getStatus()==200){
            for (int i = 0; i < booklistBean.getResult().size(); i++) {
                booklistBean.getResult().get(i).setChoose(false);
                resultBeans.add(booklistBean.getResult().get(i));
                celecthowAdapter.notifyDataSetChanged();
            }
        }else if(booklistBean.getStatus()==511){
            finish();
            ToastUtil.makeShortText(this,"账号已在别处登录");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setKnowPointer(KnowPointerBean knowPointer) {

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
        id = getIntent().getIntExtra("id", 0);
        resultBeans = new ArrayList<>();
        recyBook.setLayoutManager(new GridLayoutManager(this,2));
        celecthowAdapter = new CelectBookAdapter(resultBeans);
        recyBook.setAdapter(celecthowAdapter);
        mPresentser.getBookList(new Gson().toJson(new KnowledgeJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), id + "", MyApp.version,"Android")));

        Log.d("------", "initData: "+new Gson().toJson(new KnowledgeJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), id + "", MyApp.version,"Android")));
        celecthowAdapter.setOnItemListener(new CelectBookAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos, BooklistBean.ResultBean booklistBean) {
                if(booklistBean.isChoose()){
                    celecthowAdapter.resultBeans.get(pos).setChoose(false);
                }else{
                    celecthowAdapter.resultBeans.get(pos).setChoose(true);
                }
                celecthowAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public int createLayoutID() {
        return R.layout.activity_celectbook;
    }
    @Override
    public int choseeClor() {
        return R.color.white;
    }
    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:
                chooseBooks.clear();
                for (int i = 0; i < celecthowAdapter.resultBeans.size(); i++) {

                    if(celecthowAdapter.resultBeans.get(i).isChoose()){

                        chooseBooks.add(celecthowAdapter.resultBeans.get(i));
                    }
                }
                if(chooseBooks.size()>0){
                    Bundle b = new Bundle();
                    b.putSerializable("list", chooseBooks);
                    b.putInt("id",id);
                    startActivity(Chooseknowledge.class,b);
                }else {
                    ToastUtil.makeShortText(this,"请至少选择一本书");
                }
                break;
        }
    }
}
