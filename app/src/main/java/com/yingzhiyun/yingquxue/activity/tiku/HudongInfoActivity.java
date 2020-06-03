package com.yingzhiyun.yingquxue.activity.tiku;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.request.RequestOptions;
import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.InteractionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HudongIfoBean;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HodongInfoJson;
import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.ImagePreviewActivity;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.adapter.CelectBookAdapter;
import com.yingzhiyun.yingquxue.adapter.P;
import com.yingzhiyun.yingquxue.adapter.PhotoAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.InteractionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HudongInfoActivity extends BaseActicity<InteractionsMvp.Interaction_View, InteractionsPresenter<InteractionsMvp.Interaction_View>> implements
        InteractionsMvp.Interaction_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.questioner_head)
    ImageView questionerHead;
    @BindView(R.id.questioner_name)
    TextView questionerName;
    @BindView(R.id.questioner_time)
    TextView questionerTime;
    @BindView(R.id.questioner_content)
    FlexibleRichTextView questionerContent;
    @BindView(R.id.questioner_liulan)
    TextView questionerLiulan;
    @BindView(R.id.topic_title)
    TextView topicTitle;
    @BindView(R.id.answerer_head)
    ImageView answererHead;
    @BindView(R.id.answerer_name)
    TextView answererName;
    @BindView(R.id.answerer_time)
    TextView answererTime;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.answre_content)
    FlexibleRichTextView answreContent;
    @BindView(R.id.photo)
    RecyclerView photo;
    private int id;
    private PhotoAdapter photoAdapter;

    @Override
    public void setInteractionList(InteractionsListBean interactionList) {

    }

    @Override
    public void setInteractionBean(HudongIfoBean hudongIfoBean) {
        questionerTime.setText(hudongIfoBean.getResult().getQuestion().getAddTime());
        questionerLiulan.setText(hudongIfoBean.getResult().getReadVolume() + "");
        if (hudongIfoBean.getResult().getQuestion().getContentString() != null) {
            questionerContent.setText(hudongIfoBean.getResult().getQuestion().getContentString());
        }

        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(this).load(hudongIfoBean.getResult().getUserHeadImgPath())
              .apply(requestOptions).into(questionerHead);
        questionerName.setText(hudongIfoBean.getResult().getUserNickname());
        if (hudongIfoBean.getResult().getAnswer() != null) {
            if (hudongIfoBean.getResult().getAnswer().getContentString() != null) {
                answreContent.setText(hudongIfoBean.getResult().getAnswer().getContentString());
            }

            answererTime.setText(hudongIfoBean.getResult().getAnswer().getAddTime());
        } else {
            answreContent.setText("暂时没有回答");
        }
        photoAdapter = new PhotoAdapter(hudongIfoBean.getResult().getQuestion().getImgList());
        photo.setLayoutManager(new GridLayoutManager(this,4));
        photo.setAdapter(photoAdapter);
        photoAdapter.setOnItemListener(new PhotoAdapter.OnItemListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v, int pos, String booklistBean, ImageView imageView) {
                Intent intent = new Intent(HudongInfoActivity.this, ImagePreviewActivity.class);
                intent.putStringArrayListExtra("imageList", (ArrayList<String>) hudongIfoBean.getResult().getQuestion().getImgList());
                intent.putExtra(P.START_ITEM_POSITION, pos);
                intent.putExtra(P.START_IAMGE_POSITION, pos);
//                ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(HudongInfoActivity.this, imageView, imageView.getTransitionName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setMyInteractionList(MyInteractionListBean myInteractionList) {

    }

    @Override
    public void setFanlkui(CollectBean collectBean) {

    }

    @Override
    protected InteractionsPresenter<InteractionsMvp.Interaction_View> createPresenter() {
        return new InteractionsPresenter<>();
    }

    @Override
    protected void initData() {

        id = getIntent().getIntExtra("id", 0);
        mPresentser.getInteractionBean(new Gson().toJson(new HodongInfoJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), id, MyApp.version,"Android")));

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_hudonginfo;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }



}
