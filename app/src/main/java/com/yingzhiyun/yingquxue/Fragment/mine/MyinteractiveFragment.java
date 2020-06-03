package com.yingzhiyun.yingquxue.Fragment.mine;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.InteractionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HudongIfoBean;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.MyInteractionListJson;
import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.MyInteractiveAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.InteractionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class MyinteractiveFragment extends BaseFragment<InteractionsMvp.Interaction_View, InteractionsPresenter<InteractionsMvp.Interaction_View>> implements
        InteractionsMvp.Interaction_View {

    private final int anInt;
    @BindView(R.id.recy_intera)
    RecyclerView recyIntera;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private ArrayList<MyInteractionListBean.ResultBean> readlist;
    private MyInteractiveAdapter myInteractiveAdapter;
    private ArrayList<MyInteractionListBean.ResultBean> noreadList;
    private MyInteractiveAdapter noreadAdapter;

    public MyinteractiveFragment(int i) {
        super();
        anInt = i;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_recyle;
    }

    @Override
    protected void initData() {
        recyIntera.setLayoutManager(new LinearLayoutManager(getContext()));

        if (anInt == 0) {
            readlist = new ArrayList<>();
            myInteractiveAdapter = new MyInteractiveAdapter(readlist, context);
            recyIntera.setAdapter(myInteractiveAdapter);
            presenter.getMyInteractionList(new Gson().toJson(new MyInteractionListJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), "1", MyApp.version,"Android")));
        } else {
            noreadList = new ArrayList<>();
            noreadAdapter = new MyInteractiveAdapter(noreadList, context);
            recyIntera.setAdapter(noreadAdapter);
            presenter.getMyInteractionList(new Gson().toJson(new MyInteractionListJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), "2", MyApp.version,"Android")));
        }

    }


    @Override
    public void setInteractionList(InteractionsListBean interactionList) {

    }

    @Override
    public void setInteractionBean(HudongIfoBean hudongIfoBean) {

    }

    @Override
    public void setMyInteractionList(MyInteractionListBean myInteractionList) {
        if(myInteractionList.getStatus()==200){
            if (myInteractionList.getResult().size() != 0) {
                if (anInt == 0) {
                    readlist.addAll(myInteractionList.getResult());
                    myInteractiveAdapter.notifyDataSetChanged();
                } else {
                    noreadList.addAll(myInteractionList.getResult());
                    noreadAdapter.notifyDataSetChanged();
                }
                recyIntera.setVisibility(View.VISIBLE);
                linearModle.setVisibility(View.GONE);
            } else {
                recyIntera.setVisibility(View.GONE);
                linearModle.setVisibility(View.VISIBLE);
            }
        }else{

            ToastUtil.makeShortText(context,"身份过期");startActivity(PwdLoginActivity.class);
        }


    }

    @Override
    public void setFanlkui(CollectBean collectBean) {

    }

    @Override
    protected InteractionsPresenter<InteractionsMvp.Interaction_View> createPresenter() {
        return new InteractionsPresenter<>();
    }
}
