package com.yingzhiyun.yingquxue.activity.tiku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.ZjietestMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BookinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ChapterListJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.KnowledgeJson;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.SampleAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ZjieTestPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZjieTestActivity extends BaseActicity<ZjietestMvp.Zjietest_View, ZjieTestPresenter<ZjietestMvp.Zjietest_View>> implements ZjietestMvp.Zjietest_View {

    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.book_title)
    TextView bookTitle;
    @BindView(R.id.choose_book)
    TextView chooseBook;
    @BindView(R.id.refresh_layout)
    NestedScrollView refreshLayout;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.rcy_chapter)
    RecyclerView rcyChapter;
    private int id;
    private SampleAdapter sampleAdapter;
    private ArrayList<ChapterListBean.ResultBean> resultBeans;
    private List<BooklistBean.ResultBean> result=new ArrayList<>();

    @Override
    public void setChapterList(ChapterListBean chapterListBean,String string) {
        if(chapterListBean.getResult().size()>0){
            resultBeans.addAll(chapterListBean.getResult());
            sampleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setBookList(BooklistBean booklistBean) {
        if (booklistBean.getStatus() == 200) {
            if(booklistBean.getResult().size()>0){
                bookTitle.setText(booklistBean.getResult().get(0).getTitle());
                result = booklistBean.getResult();
                if (booklistBean.getResult().size() > 0) {
                    mPresentser.getChapterList(new Gson().toJson(new ChapterListJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken()
                            , booklistBean.getResult().get(0).getId() + "", MyApp.version,"Android")),"lol");
                }
            }else{
                bookTitle.setText("暂无课本");
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
        id = getIntent().getIntExtra("id", 0);
        mPresentser.getBookList(new Gson().toJson(new KnowledgeJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), id + "", MyApp.version,"Android")));
        resultBeans = new ArrayList<>();
        sampleAdapter = new SampleAdapter(resultBeans);
        rcyChapter.setLayoutManager(new LinearLayoutManager(this));
        rcyChapter.setAdapter(sampleAdapter);
        sampleAdapter.OnsetOnClickListener(new SampleAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(ChapterListBean.ResultBean.DetailBean musicBean) {
                if(musicBean.getTestPaperId()!=0){


                        startActivity(new Intent(ZjieTestActivity.this, ZuTiActivity.class).putExtra("id",musicBean.getTestPaperId()).putExtra("juantype","testPaperCheck"));

                }else{
                    ToastUtil.makeLongText(ZjieTestActivity.this,"暂时没有试卷");
                }

            }
        });
    }
    @Override
    public int createLayoutID() {
        return R.layout.acrivity_zjietest;
    }
    @Override
    public int choseeClor() {
        return R.color.white;
    }
    @OnClick({R.id.finish, R.id.choose_book})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.choose_book:
                Intent intent = new Intent(this,ChooseBookActivity.class);
                intent.putExtra("list", (Serializable) result);
                startActivityForResult(intent,0);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&data!=null){
            Bundle bundle = data.getExtras();
            if(bundle!=null){
                BooklistBean.ResultBean bean= (BooklistBean.ResultBean) bundle.getSerializable("bean");
                resultBeans.clear();
                mPresentser.getChapterList(new Gson().toJson(new ChapterListJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken()
                        , bean.getId() + "", MyApp.version,"Android")),"lol");
                bookTitle.setText(bean.getTitle());
                sampleAdapter.notifyDataSetChanged();
            }
        }
    }
}
