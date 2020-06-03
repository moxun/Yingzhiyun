package com.yingzhiyun.yingquxue.Fragment.classfiy;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yingzhiyun.yingquxue.Mvp.ClassifyMvp;
import com.yingzhiyun.yingquxue.OkBean.CoursewareListBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.ClassifyPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassFiyFragment extends BaseFragment<ClassifyMvp.Classify_View, ClassifyPresenter<ClassifyMvp.Classify_View>> implements ClassifyMvp.Classify_View {
    @BindView(R.id.tv_senior)
    TextView tvSenior;
    @BindView(R.id.tv_junior)
    TextView tvJunior;
    @BindView(R.id.content_layout)
    FrameLayout contentLayout;
    public SeniorFragment seniorFragment;
    public JuniorFragment juniorFragment;
    private ArrayList<Fragment> fragments;
    private int mIndex = 0;
    @Override
    public int createLayoutId() {
        return R.layout.fragment_classfiy;
    }

    @Override
    protected void initData() {
        seniorFragment = new SeniorFragment();
        juniorFragment = new JuniorFragment();
        fragments = new ArrayList<>();
        fragments.add(seniorFragment);
        fragments.add(juniorFragment);
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.content_layout, seniorFragment).commit();

    }


    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    @Override
    public void setSubject(SubjectBean subject) {

    }

    @Override
    public void setCoursewareList(ZiyuanBean coursewareList) {

    }

    @Override
    protected ClassifyPresenter<ClassifyMvp.Classify_View> createPresenter() {
        return new ClassifyPresenter<>();
    }

    @OnClick({R.id.tv_senior, R.id.tv_junior})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_senior:
                setIndexSelected(0);
                tvSenior.setBackgroundResource(R.drawable.chooseleft);
                tvJunior.setBackgroundResource(R.drawable.righttuoyuan);
                tvSenior.setTextColor(Color.parseColor("#ffffff"));
                tvJunior.setTextColor(Color.parseColor("#1091E9"));
                break;
            case R.id.tv_junior:
                setIndexSelected(1);
                tvSenior.setBackgroundResource(R.drawable.lefttuoyuan);
                tvJunior.setBackgroundResource(R.drawable.chosseright);
                tvSenior.setTextColor(Color.parseColor("#1091E9"));
                tvJunior.setTextColor(Color.parseColor("#ffffff"));
                break;
        }
    }
    private void setIndexSelected(int index) {

        if (mIndex == index) {
            return;
        }


        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(fragments.get(mIndex));
        //判断是否添加
        if (!fragments.get(index).isAdded()) {
            ft.add(R.id.content_layout, fragments.get(index)).show(fragments.get(index));
        } else {
            ft.show(fragments.get(index));
        }

        ft.commit();
        //再次赋值
        mIndex = index;
        Log.d("loll", "setIndexSelected: " + mIndex + index);
    }
}
