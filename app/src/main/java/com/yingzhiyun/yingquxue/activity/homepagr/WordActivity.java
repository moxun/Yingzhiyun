package com.yingzhiyun.yingquxue.activity.homepagr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.gson.Gson;


import com.tencent.liteav.demo.play.SuperPlayerView;
import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.Collectjson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.MyCollectJson;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.vip.VipTopupActivity;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.okhttp.cookie.CookieJarImpl;
import com.yingzhiyun.yingquxue.okhttp.cookie.store.PersistentCookieStore;
import com.yingzhiyun.yingquxue.presenter.VideoinPresenter;
import com.yingzhiyun.yingquxue.units.DialogUtil;
import com.yingzhiyun.yingquxue.units.LoadFileModel;
import com.yingzhiyun.yingquxue.units.Md5Tool;
import com.yingzhiyun.yingquxue.units.PickerView;
import com.yingzhiyun.yingquxue.units.RefreshDialog;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.SuperFileView2;
import com.yingzhiyun.yingquxue.units.TLog;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordActivity extends BaseActicity<VideoinfoMvp.Videoinfo_View, VideoinPresenter<VideoinfoMvp.Videoinfo_View>> implements VideoinfoMvp.Videoinfo_View, EasyPermissions.PermissionCallbacks {
    private static final String TAG = "lol";
    @BindView(R.id.mSuperFileView)
    SuperFileView2 mSuperFileView;
    @BindView(R.id.activity_file_display)
    RelativeLayout activityFileDisplay;
    String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @BindView(R.id.favouter)
    ImageView favouter;
    @BindView(R.id.answer_card)
    ImageButton answerCard;
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.tool_title)
    TextView toolTitle;

    private int id;
    private ZiyuanBean.ResultBean resultBean;
    private String file_path;

    private boolean collection = false;

    private File fileN;
    private RefreshDialog refreshDialog;
    private LayoutInflater baseInflater;
    private SuperFileView2 viewById;
    private boolean isvip;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //获取权限
    @SuppressLint("WrongConstant")
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            //已经打开权限


                downLoadFromNet(file_path, mSuperFileView);
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的使用权限", 1, perms);
        }
    }

    @Override
    protected void initData() {
        baseInflater = LayoutInflater.from(this);
        refreshDialog = new RefreshDialog(this);

        Intent intent = getIntent();
        mPresentser.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                SharedPreferenceUtils.getToken(), "file", MyApp.version, "Android")));

        id = intent.getIntExtra("id", 0);
        file_path = intent.getStringExtra("filepath");

        isvip = intent.getBooleanExtra("isvip", false);
        collection = intent.getBooleanExtra("shoucang", false);
        getMessage(file_path);
        String title = intent.getStringExtra("title");
        if (title != null) {
            toolTitle.setText(title);

        }
        if(isvip){
            favouter.setVisibility(View.GONE);
            answerCard.setVisibility(View.GONE);
        }


        getPermission();
        sendMultipart();
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_word;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    private void downLoadFromNet(final String url, final SuperFileView2 mSuperFileView2) {

        //1.网络下载、存储路径、
        File cacheFile = getCacheFile(url);
        if (cacheFile.exists()) {
            if (cacheFile.length() <= 0) {
                TLog.d(TAG, "删除空文件！！");

                cacheFile.delete();
                return;
            } else {
                fileN=cacheFile;
                mSuperFileView2.displayFile(cacheFile);
                return;
            }

        }
        if(!WifiConnected(this)){
            dialogWifi().show();
        }else {
            ShowFile(url,mSuperFileView);
        }


    }

    /***
     * 获取缓存目录
     *
     * @param url
     * @return
     */
    private File getCacheDir(String url) {

        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/007/");

    }
    private Dialog dialogWifi() {
        View dialogView = baseInflater.inflate(R.layout.dialog_nowifi, null);
        final Dialog dialog = DialogUtil.showDialogCenter(this, dialogView, 300);

        TextView dialog_left_btn = (TextView) dialogView.findViewById(R.id.dialog_left_btn);
        TextView dialog_right_btn = (TextView) dialogView.findViewById(R.id.dialog_right_btn);





        dialog_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();

            }
        });
        dialog_right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ShowFile(file_path, mSuperFileView);
            }
        });
        return dialog;
    }

    public void ShowFile(final String url, final SuperFileView2 mSuperFileView2) {
        refreshDialog.showLoading();
        LoadFileModel.loadPdfFile(url, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                TLog.d(TAG, "下载文件-->onResponse");
                boolean flag;
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    ResponseBody responseBody = response.body();
                    is = responseBody.byteStream();
                    long total = responseBody.contentLength();

                    File file1 = getCacheDir(url);
                    if (!file1.exists()) {
                        file1.mkdirs();
                        TLog.d(TAG, "创建缓存目录： " + file1.toString());
                    }


                    //fileN : /storage/emulated/0/pdf/kauibao20170821040512.pdf
                    //new File(getCacheDir(url), getFileName(url))
                    fileN = getCacheFile(url);

                    TLog.d(TAG, "创建缓存文件： " + fileN.toString());
                    if (!fileN.exists()) {
                        boolean mkdir = fileN.createNewFile();
                    }
                    fos = new FileOutputStream(fileN);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        TLog.d(TAG, "写入缓存文件" + fileN.getName() + "进度: " + progress);
                    }
                    fos.flush();
                    TLog.d(TAG, "文件下载成功,准备展示文件。");
                    String fileName = fileN.getPath();
                    refreshDialog.hideLoading();
                    mSuperFileView2.displayFile(fileN);

                } catch (Exception e) {
                    refreshDialog.hideLoading();
                    TLog.d(TAG, "文件下载异常 = " + e.toString());
                } finally {
                    try {
                        if (is != null)
                            is.close();
                        refreshDialog.hideLoading();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                        refreshDialog.hideLoading();

                    } catch (IOException e) {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                TLog.d(TAG, "文件下载失败");
                File file = getCacheFile(url);
                if (!file.exists()) {
                    TLog.d(TAG, "删除下载失败文件");
                    file.delete();
                }
            }
        });
    }

    /***
     * 绝对路径获取缓存文件
     *
     * @param url
     * @return
     */
    private File getCacheFile(String url) {
//        getExternalFilesDir(null);
//        File cacheFile = new File(Environment.DIRECTORY_PICTURES+ "/007/"
//                + getFileName(url));
//        TLog.d(TAG, "缓存文件 = " + cacheFile.toString());
//        // set "Documents" as subDir
        final File[] dirs = getExternalFilesDirs("Documents");
        File primaryDir = null;
        if (dirs != null && dirs.length > 0) {
            primaryDir = dirs[0];
        }

        File cacheFile = new File(primaryDir.getAbsolutePath(), getFileName(url));
        Log.d(TAG, "getCacheFile: " + cacheFile.getPath());

        return cacheFile;
    }

    /***
     * 根据链接获取文件名（带类型的），具有唯一性
     *
     * @param url
     * @return
     */
    private String getFileName(String url) {
        if (url != null) {
            String fileName = Md5Tool.hashKey(url) + "." + getFileType(url);
            return fileName;
        }
        return "";
    }

    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            TLog.d(TAG, "paramString---->null");
            return str;
        }
        TLog.d(TAG, "paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            TLog.d(TAG, "i <= -1");
            return str;
        }


        str = paramString.substring(i + 1);
        TLog.d(TAG, "paramString.substring(i + 1)------>" + str);
        return str;
    }

    @Override
    protected VideoinPresenter<VideoinfoMvp.Videoinfo_View> createPresenter() {
        return new VideoinPresenter<>();
    }

    /**
     * 上传文件及参数
     */
    private void sendMultipart() {
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        //设置超时时间及缓存


        MediaType mediaType = MediaType.parse("application/json");
//使用JSONObject封装参数
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("courseWareId", id);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//创建RequestBody对象，将参数按照指定的MediaType封装
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url("http://192.168.0.120/yzy/app/historicRecordCourseWare")
                .build();

        HttpManager.getInstance().getOkhttpClient().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {

            }
        });
    }
    /**
     * 上传文件及参数
     */
    private void getMessage(String file_path) {
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;




//创建RequestBody对象，将参数按照指定的MediaType封装

        Request request = new Request
                .Builder()
                .get()

                .url(file_path)
                .build();

        HttpManager.getInstance().getOkhttpClient().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                String string = response.body().string();
                if(string.equals("no")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(new Intent(WordActivity.this, VipTopupActivity.class));
                        }
                    });
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        viewById = findViewById(R.id.mSuperFileView);
        viewById.onStopDisplay();

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        downLoadFromNet(file_path, mSuperFileView);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        finish();
        ToastUtil.makeLongText(this, "此功能需要权限");
    }

    @Override
    public void setVideoinfo(VideoinfoBean videoinfo) {

    }



    @Override
    public void setCollectVideo(CollectBean collectVideo) {
        Log.d(TAG, "setCollectVideo: " + collectVideo.getHint());
        if (collectVideo.getStatus() == 200) {
            if (collection) {
                collection = false;
                favouter.setImageResource(R.mipmap.icon_wordno);
            } else {
                collection = true;
                favouter.setImageResource(R.mipmap.icon_shoucang);
            }
        } else if (collectVideo.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, collectVideo.getHint());
            startActivity(PwdLoginActivity.class);
        }

    }

    @Override
    public void setMyCollect(ZiyuanBean collectVideo) {
        if (collectVideo.getResult() != null && collectVideo.getResult().size() > 0) {
            for (int i = 0; i < collectVideo.getResult().size(); i++) {
                if (collectVideo.getResult().get(i).getId() == id) {
                    collection = true;
                    favouter.setImageResource(R.mipmap.icon_wordlike);
                }
            }
        }
        if (collectVideo.getStatus() == 511) {
            finish();
            startActivity(PwdLoginActivity.class);
        }
    }

    // 根据文件后缀名获得对应的MIME类型。
    private static String getMimeType(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        String mime = "*/*";
        if (filePath != null) {
            try {
                mmr.setDataSource(filePath);
                mime = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            } catch (IllegalStateException e) {
                return mime;
            } catch (IllegalArgumentException e) {
                return mime;
            } catch (RuntimeException e) {
                return mime;
            }
        }
        return mime;
    }


    /**
     * 分享文件
     *
     * @paramfile
     */

    private void voidshareFile(File file) {
        try {
            if (file == null || !file.exists()) {
                Log.d(TAG, "voidshareFile: ");
                return;

            }
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//设置intent的data和Type属性。
//添加这一句表示对目标应用临时授权该Uri所代表的文件
            Intent share = new Intent(Intent.ACTION_SEND);
            Log.e("ShareUtils", "shareFile: " + file.getPath());
            Uri uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".fileProvider", file);
            share.putExtra(Intent.EXTRA_STREAM, uri);
            share.setType("*/*");
            startActivity(Intent.createChooser(share, "Alpha Share"));
        } catch (Exception e) {

            e.printStackTrace();


        }

    }

    public File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    @OnClick({R.id.finish, R.id.favouter, R.id.answer_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.favouter:
                if (SharedPreferenceUtils.getisLogin()) {

                    mPresentser.getCollectVideo(new Gson().toJson(new Collectjson(id,
                            "file", SharedPreferenceUtils.getUserid(),
                            SharedPreferenceUtils.getToken(), MyApp.version, "Android")));

                } else {
                    ToastUtil.makeLongText(this, "您还没有登录");
                }
                break;
            case R.id.answer_card:
                voidshareFile(fileN);
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blue_select);
//                sharePictureToWechatFriend(this,getFile(bitmap));
                break;
        }
    }

    /**
     * 判断当前网络是否为 Wifi 网络连接
     * @param context
     * @return
     */
    private static boolean WifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

}
