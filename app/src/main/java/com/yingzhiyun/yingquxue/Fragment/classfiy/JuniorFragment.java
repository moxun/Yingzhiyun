package com.yingzhiyun.yingquxue.Fragment.classfiy;

import android.os.Bundle;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.yingzhiyun.yingquxue.Mvp.ClassifyMvp;
import com.yingzhiyun.yingquxue.OkBean.CoursewareListBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.ClassifyPresenter;
import com.yingzhiyun.yingquxue.units.NoScrollViewPager;
import com.yingzhiyun.yingquxue.units.VerticalPager;

import java.util.ArrayList;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class JuniorFragment extends BaseFragment<ClassifyMvp.Classify_View, ClassifyPresenter<ClassifyMvp.Classify_View>> implements ClassifyMvp.Classify_View {
    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout navigationTabLayout;
    @BindView(R.id.navigation_RecyclerView)
    NoScrollViewPager navigationRecyclerView;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private vpsp vpsp;
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //TODO now visible to user
            presenter.getSubject("1");
        } else {
            //TODO now invisible to user
            presenter.getSubject("1");
        }
    }
    @Override
    public void setSubject(SubjectBean subject) {
        strings.clear();
        fragments.clear();
        for (int i = 0; i < subject.getResult().size(); i++) {
            strings.add(subject.getResult().get(i).getName());
            fragments.add(new SubjectinfoFragment(subject.getResult().get(i).getSubjectId()));

        }
        vpsp = new vpsp(getChildFragmentManager(),fragments,strings);
        navigationRecyclerView.setAdapter(vpsp);
        //进行关联
        navigationTabLayout.setupWithViewPager(navigationRecyclerView);

        navigationTabLayout.setTabAdapter(new TabAdapter() {

            @Override
            public int getCount() {
                return strings == null ? 0 : strings.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return  new TabView.TabTitle.Builder().setContent(strings.get(position)).
                        setTextColor(ContextCompat.getColor(mActivity, R.color.black),
                        ContextCompat.getColor(mActivity, R.color.black))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });


    }

    @Override
    public void setCoursewareList(ZiyuanBean coursewareList) {

    }

    //    自定义适配器
    class vpsp extends FragmentPagerAdapter {


        private final ArrayList<Fragment> fragments;
        private final ArrayList<String> strings;

        public vpsp(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> strings) {
            super(fm);
            this.fragments =fragments;
            this.strings =strings;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return strings.get(position);
        }
        //动态创建fragment对象并返回

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }
        //返回数量

        @Override
        public int getCount() {
            return fragments.size();
        }


    }

    @Override
    protected ClassifyPresenter<ClassifyMvp.Classify_View> createPresenter() {
        return new ClassifyPresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_junior;
    }

    @Override
    protected void initData() {
        strings = new ArrayList<>();
        fragments = new ArrayList<>();
        presenter.getSubject("1");
        navigationRecyclerView.setNoScroll(true);
    }
}
