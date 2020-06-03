package com.yingzhiyun.yingquxue.Fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.QueryscoreActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.BashiinfoActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.CourseActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.JiaocaiActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MoreSelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.PracticetestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.SelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.TeachingActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.mine.MoreTeacherActivity;
import com.yingzhiyun.yingquxue.adapter.BashiAdapter;
import com.yingzhiyun.yingquxue.adapter.SchoolAdapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;

import java.io.Serializable;
import java.text.Bidi;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeViewpagerFragment extends SimpleFragment {

    @BindView(R.id.more_school)
    TextView moreSchool;
    @BindView(R.id.recy_School)
    RecyclerView recySchool;

    @BindView(R.id.text_title)
    TextView textTitle;
    private ArrayList<ZiyuanBean.ResultBean> listBeans;
    private ArrayList<ZiyuanBean.ResultBean> list;
    private BashiAdapter schoolAdapter;
    private String title;
    private int id;
    private String type;
    private ZiyuanBean.ResultBean listBean;
    private TextView shoucang;


    @Override
    public int createLayoutId() {
        return R.layout.feagment_homeviewpager;
    }

    @Override
    protected void initData() throws JSONException {
        Bundle arguments = getArguments();
        title = arguments.getString("title");
        id = arguments.getInt("id");
        type = arguments.getString("type");
        list = (ArrayList<ZiyuanBean.ResultBean>) arguments.getSerializable("bean");

        textTitle.setText(title);
        listBeans = new ArrayList<>();
        assert title != null;
        if(list!=null){
            listBeans.addAll(list);
        }
//        if(title.equals("名校联考测评")){
//            iconOne.setImageResource(R.mipmap.icon_right_hint);
//            iconTwo.setImageResource(R.mipmap.icon_gray_yuan);
//            iconThree.setImageResource(R.mipmap.icon_gray_yuan);
//        }else if(title.equals("高考真题")){
//            iconTwo.setImageResource(R.mipmap.icon_right_hint);
//            iconOne.setImageResource(R.mipmap.icon_gray_yuan);
//            iconThree.setImageResource(R.mipmap.icon_gray_yuan);
//        }else{
//            iconThree.setImageResource(R.mipmap.icon_right_hint);
//            iconOne.setImageResource(R.mipmap.icon_gray_yuan);
//            iconTwo.setImageResource(R.mipmap.icon_gray_yuan);
//        }

        recySchool.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("moxun", "initData: "+listBeans.size());

        schoolAdapter = new BashiAdapter(this.listBeans);

        schoolAdapter.OnsetOnClickListener(new BashiAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(ZiyuanBean.ResultBean resultBean, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", resultBean.getId());
                bundle.putString("title", resultBean.getTitle());
                bundle.putBoolean("is",resultBean.isFolderCollection());
                startActivity(BashiinfoActivity.class, bundle);
            }

            @Override
            public void setCollListener(ZiyuanBean.ResultBean resultBean, int position, TextView textView) {
                listBean = resultBean;
                shoucang = textView;
                CollictionList(resultBean.getId());
            }
        });
        recySchool.setAdapter(schoolAdapter);
        recySchool.setNestedScrollingEnabled(false);

    }
    @Override
    protected void Errorcool(String msg) {
        super.showLoading(msg);
        mActivity.finish();
        startActivity(PwdLoginActivity.class);
        ToastUtil.makeShortText(context,msg);
    }
    @Override
    protected void showLoading(String msg) {
        super.showLoading(msg);

        if(listBean.isFolderCollection()){
            shoucang.setText("收藏");
            shoucang.setBackgroundResource(R.drawable.shoucang);
            listBean.setFolderCollection(false);
            ToastUtil.makeShortText(context,"取消收藏成功");
        }   else {
            shoucang.setText("已收藏");
            shoucang.setBackgroundResource(R.drawable.yiguanzhu);
            listBean.setFolderCollection(true);
            ToastUtil.makeShortText(context,"收藏成功");
        }
    }
    @OnClick(R.id.more_school)
    public void onViewClicked() {
        if(SharedPreferenceUtils.getisLogin()){
            //点击轮播图
//            if(type.equals("list")){
//                Intent intent = new Intent(context, JiaocaiActivity.class);
//                intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("", id,"","" ));
//                context.startActivity(intent);
//            }else if(type.equals("folderList")){
//                Intent intent = new Intent(context, EntranceActivity.class);
//                intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("", id,"","" ));
//                context.startActivity(intent);
//            }
            if(type .equals("list")){
                Intent intent = new Intent(context, JiaocaiActivity.class);
                intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean( "",id,"",type ));
                startActivity(intent);
            }else if(type.equals("course")){
                startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",id));
            }else if(type.equals("courseList")){
                Intent intent = new Intent(context, MoreTeacherActivity.class);
                intent.putExtra("bean", 26);
                intent.putExtra("name", "名师辅导");
            }else if(type.equals("collectionList")){
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                startActivity(MoreSelectionActivity.class, bundle);
            }else if(type.equals("courseModel")){
                Intent intent = new Intent(context, CourseActivity.class).putExtra("isVip",false);

                context.startActivity(intent);
            }else if(type.equals("vocationalTraining")){
                Intent intent = new Intent(context, JiaocaiActivity.class);
                intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("",17,"职业培训","vocationalTraining"));
                this.startActivity(intent);
            }else if(type.equals("folderList")) {
                Intent intent = new Intent(context, EntranceActivity.class);
                intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("",id,"",type));
                this.startActivity(intent);
            }else if(type.equals("marking")){
                Intent intent = new Intent(context, QueryscoreActivity.class);

                this.startActivity(intent);
            }else  if(type.equals("scoreQuery")){
                Intent intent = new Intent(context, QueryscoreActivity.class);

                this.startActivity(intent);
            }else  if(type.equals("img")){

            }else  if(type.equals("bookList") ){
                Intent intent = new Intent(context, TeachingActivity.class);

                startActivity(intent);
            }else if(type.equals("onlineTest")){
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("type",2);

                startActivity(intent);
            }
        }else{
            startActivity(PwdLoginActivity.class);
        }


    }
}
