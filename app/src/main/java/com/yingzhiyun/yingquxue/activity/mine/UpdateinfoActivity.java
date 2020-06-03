package com.yingzhiyun.yingquxue.activity.mine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yingzhiyun.yingquxue.Mvp.userinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;

import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.UpdateUserinfoJson;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.OkBean.localbean.UserinAdapterBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
//import com.yingzhiyun.yingquxue.activity.tiku.GlideLoader;
import com.yingzhiyun.yingquxue.activity.tiku.SendQuestionActivity;
import com.yingzhiyun.yingquxue.adapter.UserinfoAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.presenter.userinfoPrsenter;
import com.yingzhiyun.yingquxue.units.DateTimeDialogOnlyYMD;
import com.yingzhiyun.yingquxue.units.DialogUtil;
import com.yingzhiyun.yingquxue.units.PickerView;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;
//import com.lcw.library.imagepicker.ImagePicker;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateinfoActivity extends BaseActicity<userinfoMvp.userinfo_View, userinfoPrsenter<userinfoMvp.userinfo_View>> implements userinfoMvp.userinfo_View, DateTimeDialogOnlyYMD.MyOnDateSetListener {

    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.rea_image)
    RelativeLayout reaImage;
    @BindView(R.id.recy_info)
    RecyclerView recyInfo;
    private ArrayList<UserinAdapterBean> userinAdapterBeans;
    private UserinfoAdapter userinfoAdapter;
    int REQUEST_SELECT_IMAGES_CODE = 200;

    private File file;
    private LayoutInflater baseInflater;
    private DateTimeDialogOnlyYMD dateTimeDialogOnlyYear;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy");
    private List<String> grade;
    private String grade1;
    private String name, sex, school, gradetitle, classtitle, year, student_id = "";
    private ArrayList<String> nianji;
    private int nian = 2010;
    int type = 0;
    private Dialog dialog;
    private List<LocalMedia> selectList;

    @Override
    protected void initData() {
        baseInflater = LayoutInflater.from(this);
        userinAdapterBeans = new ArrayList<>();
        nianji = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            nianji.add(nian + "");
            nian++;
        }
        userinfoAdapter = new UserinfoAdapter(userinAdapterBeans);
        recyInfo.setLayoutManager(new LinearLayoutManager(this));
        recyInfo.setAdapter(userinfoAdapter);
        dateTimeDialogOnlyYear = new DateTimeDialogOnlyYMD(this, this, false, false, true);
        userinfoAdapter.OnsetOnClickListener(new UserinfoAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(UserinAdapterBean userinAdapterBean) {
                String title = userinAdapterBean.getTitle();
                if (title.equals("昵称")) {
                    dialogEditext("请输入您的昵称").show();
//                    showDialog();
                } else if (title.equals("性别")) {
                    dialogSex().show();
                } else if (title.equals("班级")) {
                    dialogEditext("请输入您的班级").show();
                } else if (title.equals("学号")) {
                    dialogEditext("请输入您的学号").show();
                } else if (title.equals("入学年份")) {
                    dialogNianji().show();
                } else if (title.equals("年级")) {
                    dialognianji().show();
                } else {
                    Intent intent = new Intent(UpdateinfoActivity.this, SearchSchoolActivity.class);
                    startActivityForResult(intent, 5);
                }
            }
        });
        Log.d("id", "initData: " + SharedPreferenceUtils.getUserid());
        Log.d("token", "initData: " + SharedPreferenceUtils.getToken());
        mPresentser.getuserinfo(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
        grade = new ArrayList<String>();
        grade.add("高一");
        grade.add("高二");
        grade.add("高三");
        grade.add("七年级");
        grade.add("八年级");
        grade.add("九年级");
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(UpdateinfoActivity.this);
                } else {
                    Toast.makeText(UpdateinfoActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private Dialog dialogNianji() {
        View dialogView = baseInflater.inflate(R.layout.dialog_nianji, null);
        final Dialog dialog = DialogUtil.showDialogCenter(this, dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);
        tvTitle.setText("入学年份");
        TextView dialog_left_btn = (TextView) dialogView.findViewById(R.id.dialog_left_btn);
        TextView dialog_right_btn = (TextView) dialogView.findViewById(R.id.dialog_right_btn);
        PickerView pickerView = dialogView.findViewById(R.id.picker_view);

        pickerView.setData(nianji);
        year = "2015";
        pickerView.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                year = text;
            }
        });
        dialog_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


            }
        });
        dialog_right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                userinfoAdapter.userinAdapterBeans.get(5).setName(year);
                userinfoAdapter.notifyDataSetChanged();
            }
        });
        return dialog;
    }
    // onActivityResult

    @Override
    public int createLayoutID() {
        return R.layout.activity_updateinfo;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (type == 1) {
            mPresentser.getupdateinfo(new Gson().toJson(new UpdateUserinfoJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), name, sex, school, gradetitle, classtitle, year, student_id, MyApp.version, "Android")));
        }

    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    public void setuserinfo(UserinfoBean userinfoBean) {
        if (userinfoBean.getStatus() == 200) {
            type = 1;
            UserinfoBean.ResultBean result = userinfoBean.getResult();
            name = result.getNickname();
            sex = result.getSex() + "";
            gradetitle = result.getGrade() + "";
            school = result.getSchool_id() + "";
            classtitle = result.getClazz();
            year = result.getEnrollment_year();
            student_id = result.getStudent_id();

            userinAdapterBeans.add(new UserinAdapterBean("昵称", result.getNickname()));
//        userinAdapterBeans.add(new UserinAdapterBean("性别",result.getSex()));
            if (result.getSex().equals("1")) {
                userinAdapterBeans.add(new UserinAdapterBean("性别", "男"));
            } else if (result.getSex().equals("2")) {
                userinAdapterBeans.add(new UserinAdapterBean("性别", "女"));
            } else {
                userinAdapterBeans.add(new UserinAdapterBean("性别", "未填写"));
            }

            if (result.getSchool().equals("")) {
                userinAdapterBeans.add(new UserinAdapterBean("学校", "未填写"));
            } else {
                userinAdapterBeans.add(new UserinAdapterBean("学校", result.getSchool()));
            }
            if (result.getGrade().equals("")) {
                userinAdapterBeans.add(new UserinAdapterBean("年级", "未填写"));
            } else {
                if (result.getGrade().equals("7")) {
                    userinAdapterBeans.add(new UserinAdapterBean("年级", "七年级"));
                } else if (result.getGrade().equals("8")) {
                    userinAdapterBeans.add(new UserinAdapterBean("年级", "八年级"));
                } else if (result.getGrade().equals("9")) {
                    userinAdapterBeans.add(new UserinAdapterBean("年级", "九年级"));
                } else if (result.getGrade().equals("10")) {
                    userinAdapterBeans.add(new UserinAdapterBean("年级", "高一"));
                } else if (result.getGrade().equals("11")) {
                    userinAdapterBeans.add(new UserinAdapterBean("年级", "高二"));
                } else {
                    userinAdapterBeans.add(new UserinAdapterBean("年级", "高三"));
                }

            }
            if (result.getClazz().equals("")) {
                userinAdapterBeans.add(new UserinAdapterBean("班级", "未填写"));
            } else {
                userinAdapterBeans.add(new UserinAdapterBean("班级", result.getClazz()));
            }
            if (result.getEnrollment_year().equals("")) {
                userinAdapterBeans.add(new UserinAdapterBean("入学年份", "未填写"));
            } else {
                userinAdapterBeans.add(new UserinAdapterBean("入学年份", result.getEnrollment_year()));
            }

            if (result.getStudent_id().equals("")) {
                userinAdapterBeans.add(new UserinAdapterBean("学号", "未填写"));
            } else {
                userinAdapterBeans.add(new UserinAdapterBean("学号", result.getStudent_id()));
            }
            userinfoAdapter.notifyDataSetChanged();


            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(this)
                    .load(SharedPreferenceUtils.getuserhead())
                    .apply(requestOptions)
                    .into(imageHead);
        } else {
            ToastUtil.makeLongText(this, (String) userinfoBean.getHint());
        }

    }

    @Override
    public void setSchool(SchoolBean school) {

    }

    @Override
    public void setupdateinfo(CollectBean collectBean) {

    }

    @Override
    public void setmyBetList(BetListBean betListBean) {

    }

    @Override
    public void setmyBetFiles(YatiBean bashiinfoBean) {

    }

    @Override
    protected userinfoPrsenter createPresenter() {
        return new userinfoPrsenter();
    }


    @OnClick({R.id.finish, R.id.rea_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.rea_image:

                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(UpdateinfoActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数

                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                        .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示

                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(true)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(false)// 是否开启点击声音
//                        .selectionMedia(selectList)// 是否传入已选图片
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
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                selectList = PictureSelector.obtainMultipleResult(data);
                file = new File(selectList.get(0).getPath());
                SharedPreferenceUtils.setuserhead(selectList.get(0).getPath());
                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(this)
                        .load(SharedPreferenceUtils.getuserhead())
                        .apply(requestOptions)
                        .into(imageHead);

                sendMultipart();
            } else {
                SchoolBean.ResultBean bean = (SchoolBean.ResultBean) data.getSerializableExtra("bean");
                school = bean.getId() + "";
                SharedPreferenceUtils.setSchool(bean.getName());
                userinfoAdapter.userinAdapterBeans.get(2).setName(bean.getName());
                userinfoAdapter.notifyDataSetChanged();
            }

        }


    }

    protected Dialog dialogEditext(String title) {

        View dialogView = baseInflater.inflate(R.layout.dialog_nickname, null);
        final Dialog dialog = DialogUtil.showDialogCenter(this, dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);
        EditText tvContent = dialogView.findViewById(R.id.content);
        tvContent.setLongClickable(true);
        TextView rigntBtn = (TextView) dialogView.findViewById(R.id.dialog_right_btn);
        TextView leftBtn = (TextView) dialogView.findViewById(R.id.dialog_left_btn);
        if (!StringUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }


        rigntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!StringUtils.isEmpty(tvContent.getText().toString())) {
                    if (title.equals("请输入您的昵称")) {
                        name = tvContent.getText().toString();
                        SharedPreferenceUtils.setusername(name);
                        userinfoAdapter.userinAdapterBeans.get(0).setName(tvContent.getText().toString());
                    } else if (title.equals("请输入您的班级")) {

                        classtitle = tvContent.getText().toString();
                        userinfoAdapter.userinAdapterBeans.get(4).setName(tvContent.getText().toString());
                    } else if (title.equals("请输入您的学号")) {
                        student_id = tvContent.getText().toString();
                        userinfoAdapter.userinAdapterBeans.get(6).setName(tvContent.getText().toString());
                    }

                    userinfoAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();

            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialogLeftBtn();
            }
        });
        return dialog;
    }

    protected Dialog dialogSex() {

        View dialogView = baseInflater.inflate(R.layout.dialog_sex, null);
        final Dialog dialog = DialogUtil.showDialogCenter(this, dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);

        TextView nan = (TextView) dialogView.findViewById(R.id.text_nan);
        TextView nv = (TextView) dialogView.findViewById(R.id.text_nv);


        nan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                userinfoAdapter.userinAdapterBeans.get(1).setName("男");

                sex = "1";
                userinfoAdapter.notifyDataSetChanged();
            }
        });
        nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                userinfoAdapter.userinAdapterBeans.get(1).setName("女");
                sex = "2";

                userinfoAdapter.notifyDataSetChanged();
            }
        });
        return dialog;
    }

    protected Dialog dialognianji() {

        View dialogView = baseInflater.inflate(R.layout.dialog_nianji, null);
        final Dialog dialog = DialogUtil.showDialogCenter(this, dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);

        TextView dialog_left_btn = (TextView) dialogView.findViewById(R.id.dialog_left_btn);
        TextView dialog_right_btn = (TextView) dialogView.findViewById(R.id.dialog_right_btn);
        PickerView pickerView = dialogView.findViewById(R.id.picker_view);

        pickerView.setData(grade);
        grade1 = "七年级";
        pickerView.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {

                grade1 = text;
            }
        });
        dialog_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


            }
        });
        dialog_right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (grade1 != null) {
                    if (grade1.equals("七年级")) {
                        gradetitle = "7";
                    } else if (grade1.equals("八年级")) {
                        gradetitle = "8";
                    } else if (grade1.equals("九年级")) {
                        gradetitle = "9";
                    } else if (grade1.equals("高一")) {
                        gradetitle = "10";
                    } else if (grade1.equals("高二")) {
                        gradetitle = "11";
                    } else {
                        gradetitle = "12";
                    }
                }
                SharedPreferenceUtils.setClazz(grade1);
                userinfoAdapter.userinAdapterBeans.get(3).setName(grade1);
                userinfoAdapter.notifyDataSetChanged();
            }
        });
        return dialog;
    }

    /**
     * 上传文件及参数
     */
    private void sendMultipart() {


        MediaType MutilPart_Form_Data = MediaType.parse("multipart/form-data; charset=utf-8");
        MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        mbody.addFormDataPart("app_user_id", SharedPreferenceUtils.getUserid() + "");
        mbody.addFormDataPart("token", SharedPreferenceUtils.getToken());

        RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
        mbody.addFormDataPart("img", file.getName(), body);


        RequestBody requestBody = mbody.build();
        Request request = new Request.Builder()
                .url("http://192.168.0.120/yzy/app/updateUserHeadImg")
                .post(requestBody)
                .build();

        HttpManager.getInstance().getOkhttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, @NotNull Response response) throws IOException {

            }
        });
    }


    @Override
    public void onDateSet(Date date) {
        userinfoAdapter.userinAdapterBeans.get(5).setName(mFormatter.format(date) + "");
        userinfoAdapter.notifyDataSetChanged();
        Log.d("oxun", "onDateSet: ");
    }

    private void showDialog() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_nickname, null);
        TextView viewById = inflate.findViewById(R.id.dialog_left_btn);
        TextView dialog_right_btn = inflate.findViewById(R.id.dialog_right_btn);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog_right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
//弹窗点击周围空白处弹出层自动消失弹窗消失(false时为点击周围空白处弹出层不自动消失)
        dialog.setCanceledOnTouchOutside(true);
//将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window window = dialog.getWindow();

        WindowManager.LayoutParams wlp = window.getAttributes();

        //获取通知栏高度  重要的在这，获取到通知栏高度
        int notificationBar = Resources.getSystem().getDimensionPixelSize(

                Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));

//获取控件 textview 的绝对坐标,( y 轴坐标是控件上部到屏幕最顶部（不包括控件本身）)
//location [0] 为x绝对坐标;location [1] 为y绝对坐标
        int[] location = new int[2];
        reaImage.getLocationInWindow(location); //获取在当前窗体内的绝对坐标

        reaImage.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标

        wlp.x = 0;//对 dialog 设置 x 轴坐标
        wlp.y = location[1] + reaImage.getHeight() - notificationBar; //对dialog设置y轴坐标
        wlp.gravity = Gravity.TOP;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();//显示对话框

    }

}
