package com.yingzhiyun.yingquxue.Fragment.classfiy;

import android.util.Log;
import android.widget.ImageView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.SubjectinfoMvp;
import com.yingzhiyun.yingquxue.OkBean.SubjectAdapterBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectInfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.SubjectAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.SubjectinfoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SubjectinfoFragment extends BaseFragment<SubjectinfoMvp.Subjectinfo_View, SubjectinfoPresenter<SubjectinfoMvp.Subjectinfo_View>> implements SubjectinfoMvp.Subjectinfo_View {

    private final int anInt;

    @BindView(R.id.recy_subjectinfo)
    RecyclerView recySubjectinfo;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    private List<SubjectAdapterBean> subjectAdapterBeans;
    private SubjectAdapter subjectAdapter;

    public SubjectinfoFragment(int subjectId) {
        anInt = subjectId;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragmen_subjectinfo;
    }

    @Override
    protected void initData() {
        subjectAdapterBeans = new ArrayList<>();
        subjectAdapter = new SubjectAdapter(subjectAdapterBeans,mActivity);
        recySubjectinfo.setNestedScrollingEnabled(false);
        recySubjectinfo.setLayoutManager(new LinearLayoutManager(getContext()));
        recySubjectinfo.setAdapter(subjectAdapter);
        presenter.getSubjectinfo(anInt+"");

    }

    @Override
    public void setSubjectinfo(SubjectInfoBean subjectInfoBean) {
        List<SubjectInfoBean.ResultBean> result = subjectInfoBean.getResult();
        if(result!=null){
            for (int i = 0; i < result.size(); i++) {
                subjectAdapterBeans.add(new SubjectAdapterBean(result.get(i).getTitle(),result.get(i).getDetail()));
            }
        }

        subjectAdapter.notifyDataSetChanged();
    }

    @Override
    protected SubjectinfoPresenter<SubjectinfoMvp.Subjectinfo_View> createPresenter() {
        return new SubjectinfoPresenter<>();
    }


}
