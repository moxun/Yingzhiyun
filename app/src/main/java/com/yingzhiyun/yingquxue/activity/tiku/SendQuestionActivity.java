package com.yingzhiyun.yingquxue.activity.tiku;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.mine.UpdateinfoActivity;
import com.yingzhiyun.yingquxue.adapter.TakePhotoAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.MMLoading;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;


import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;

import java.io.File;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendQuestionActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.ed_question)
    EditText edQuestion;
    @BindView(R.id.recy_photo)
    RecyclerView recyPhoto;
    @BindView(R.id.send)
    TextView send;
    int REQUEST_SELECT_IMAGES_CODE=200;
    private ArrayList<String> imagePaths=new ArrayList<>();
    private ArrayList<File> list;
    private TakePhotoAdapter takePhotoAdapter;
    private MMLoading mmLoading;
    private List<LocalMedia> selectList;

    @Override
    protected void initData() {
        list = new ArrayList<>();

        takePhotoAdapter = new TakePhotoAdapter(this,imagePaths );
        takePhotoAdapter.OnsetOnClickListener(new TakePhotoAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener() {

                PictureSelector.create(SendQuestionActivity.this)
                        .openGallery(PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(3)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数

                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径

                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                        .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片

                       .selectionMedia(selectList)// 是否传入已选图片
                        //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(true) // 裁剪是否可旋转图片
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }

            @Override
            public void deleteImage(int position) {
                imagePaths.remove(position);
                selectList.remove(position);
                takePhotoAdapter.notifyDataSetChanged();
            }
        });
        recyPhoto.setLayoutManager(new GridLayoutManager(this,3));
        recyPhoto.setAdapter(takePhotoAdapter);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_sendquestion;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            imagePaths.clear();
            list.clear();
            selectList = PictureSelector.obtainMultipleResult(data);
            for (LocalMedia media : selectList) {
                imagePaths.add(media.getPath());
            }


            takePhotoAdapter.notifyDataSetChanged();
            for (int i = 0; i < imagePaths.size(); i++) {
                File file = new File(imagePaths.get(i));
                list.add(file);
            }
        }


    }

    /**
     * 上传文件及参数
     */
    private void sendMultipart(){

        MediaType MutilPart_Form_Data = MediaType.parse("multipart/form-data; charset=utf-8");
        MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);
        mbody.addFormDataPart("app_user_id", SharedPreferenceUtils.getUserid()+"");
        mbody.addFormDataPart("token",SharedPreferenceUtils.getToken());
        mbody.addFormDataPart("content",edQuestion.getText().toString());
        int i=0;
        if(list.size()>0){
            for(File file:list){
                if(file.exists()){
                    RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
                    mbody.addFormDataPart("img",file.getName(),body);
                    i++;
                }
            }
        }

        RequestBody requestBody =mbody.build();
        Request request = new Request.Builder()
                .url("http://192.168.0.120/yzy/app/publishingIssues")
                .post(requestBody)
                .build();

        HttpManager.getInstance().getOkhttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, @NotNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //此时已在主线程中，更新UI
                        hideLoading();
                        ToastUtil.makeLongText(SendQuestionActivity.this,"提问成功");
                    }
                });

                finish();
            }
        });
    }
    @OnClick({R.id.finish, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.send:
                if(StringUtils.isEmpty(edQuestion.getText().toString())){
                    ToastUtil.makeLongText(this,"请输入内容");
                }else{
                    showLoading("正在加载");
                    sendMultipart();
                }
                break;
        }
    }
    protected void showLoading(String msg) {
        if (mmLoading == null) {
            MMLoading.Builder builder = new MMLoading.Builder(this)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setCancelOutside(true);
            mmLoading = builder.create();
        }else {
            mmLoading.dismiss();
            MMLoading.Builder builder = new MMLoading.Builder(this)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setCancelOutside(false);
            mmLoading = builder.create();
        }
        mmLoading.show();
    }

    protected void hideLoading() {
        if (mmLoading != null && mmLoading.isShowing()) {
            mmLoading.dismiss();
        }
    }
}
