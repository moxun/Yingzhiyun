//package com.yingzhiyun.yingquxue.Fragment;
//
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.app.DownloadManager;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.IBinder;
//import android.provider.MediaStore;
//import android.provider.Settings;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.core.content.FileProvider;
//import androidx.core.widget.NestedScrollView;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//import androidx.viewpager.widget.ViewPager;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//import com.google.gson.Gson;
//import com.yingzhiyun.yingquxue.Fragment.home.HomeViewpagerFragment;
//import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
//import com.yingzhiyun.yingquxue.MyApp.MyApp;
//import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
//import com.yingzhiyun.yingquxue.OkBean.Json;
//import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
//import com.yingzhiyun.yingquxue.OkBean.JsonBean.VersionJson;
//import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
//import com.yingzhiyun.yingquxue.OkBean.VersionBean;
//import com.yingzhiyun.yingquxue.R;
//import com.yingzhiyun.yingquxue.activity.MainActivity;
//import com.yingzhiyun.yingquxue.activity.QueryscoreActivity;
//import com.yingzhiyun.yingquxue.activity.examination.ExaminationActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.BaogaoActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.CourseActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.JiaocaiActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.MoreSelectionActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.MustTestActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.PracticeZuoActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.PracticetestActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.QuestionActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.SearchActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.TeachingActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.VideoinfoActivity;
//import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
//import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
//import com.yingzhiyun.yingquxue.activity.mine.MoreTeacherActivity;
//import com.yingzhiyun.yingquxue.activity.score.MineScoreActivity;
//import com.yingzhiyun.yingquxue.activity.shopping.ShopHomeActivity;
//import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
//import com.yingzhiyun.yingquxue.adapter.ModuleAdapter;
//import com.yingzhiyun.yingquxue.adapter.SchoolAdapter;
//import com.yingzhiyun.yingquxue.adapter.homepager.HomeTypeAdapter;
//import com.yingzhiyun.yingquxue.adapter.homepager.SelectionAdapter;
//import com.yingzhiyun.yingquxue.adapter.homepager.VideoAdapter;
//import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
//import com.yingzhiyun.yingquxue.httpUnits.DownloadService;
//import com.yingzhiyun.yingquxue.httpUnits.SystemManager;
//import com.yingzhiyun.yingquxue.presenter.HomePagerPresenter;
//import com.yingzhiyun.yingquxue.units.DialogUtil;
//import com.yingzhiyun.yingquxue.units.MD5Util;
//import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
//import com.yingzhiyun.yingquxue.units.ToastUtil;
//import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;
//import com.yingzhiyun.yingquxue.units.view.HomeViewPager;
//import com.youth.banner.Banner;
//import com.youth.banner.listener.OnBannerListener;
//import com.youth.banner.loader.ImageLoader;
//
//import org.jetbrains.annotations.NotNull;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.Serializable;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//import java.util.concurrent.TimeUnit;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//import static android.app.Activity.RESULT_OK;
//import static android.content.Context.BIND_AUTO_CREATE;
//
//public class HomeFragment extends BaseFragment<HomePagerMvp.HomePager_View, HomePagerPresenter<HomePagerMvp.HomePager_View>> implements HomePagerMvp.HomePager_View, OnBannerListener {
//    @BindView(R.id.background)
//    ImageView background;
//    @BindView(R.id.iv_search)
//    ImageView ivSearch;
//    @BindView(R.id.tv_search)
//    TextView tvSearch;
//    @BindView(R.id.serach_Lin)
//    LinearLayout serachLin;
//    @BindView(R.id.home_banner)
//    Banner homeBanner;
//    @BindView(R.id.scroll)
//    NestedScrollView scroll;
//    @BindView(R.id.refresh_layout)
//    VerticalSwipeRefreshLayout refreshLayout;
//    @BindView(R.id.rl_root)
//    RelativeLayout rlRoot;
//    @BindView(R.id.recy_module)
//    RecyclerView recyModule;
//
//    @BindView(R.id.recy_selection)
//    RecyclerView recySelection;
//
//    @BindView(R.id.recy_Video)
//    RecyclerView recyVideo;
//
//    @BindView(R.id.recy_Teacher)
//    RecyclerView recyTeacher;
//    @BindView(R.id.viewpager)
//    HomeViewPager viewpager;
//    @BindView(R.id.icon_one)
//    ImageView iconOne;
//    @BindView(R.id.icon_two)
//    ImageView iconTwo;
//    @BindView(R.id.icon_three)
//    ImageView iconThree;
//    @BindView(R.id.icon_four)
//    ImageView iconFour;
//    @BindView(R.id.jianxuan)
//    TextView jianxuan;
//
//
//    private List<String> mImages = new ArrayList<>();
//    private List<HomePagerBean.ResultBean.MenuBean> moduleBeans = new ArrayList<>();
//    private List<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> dataList;
//    private SelectionAdapter selectionAdapter;
//    private ArrayList<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> videoList;
//    private VideoAdapter videoAdapter;
//    private ArrayList<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> teacherList;
//    private VideoAdapter teacherAdaoter;
//    private ArrayList<HomePagerBean.ResultBean.TestListBean.ListBean> listBeans;
//    private SchoolAdapter schoolAdapter;
//    private HomeTypeAdapter moduleAdapter;
//    private int selectionid;
//    private ModuleBean moduleBean;
//    private ArrayList<Fragment> fragment;
//    private LayoutInflater baseInflater;
//    private VersionBean versionBean;
//    private HomePagerBean bean;
//    private static String APK_URL = "";
//
//    private DownloadService.DownloadBinder mDownloadBinder;
//    private Disposable mDisposable;//
//
//    private ServiceConnection mConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            Log.d("mxoun", "onServiceConnected: ");
//            mDownloadBinder = (DownloadService.DownloadBinder) service;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            mDownloadBinder = null;
//        }
//    };
//    private String md5;
//    private String apkPath;
//
//    public int createLayoutId() {
//        return R.layout.fragmen_home;
//    }
//
//    public boolean isNetworkConnected(Context context) {
//        if (context != null) {
//            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
//            if (mNetworkInfo != null) {
//                return mNetworkInfo.isAvailable();
//            }
//        }
//        return false;
//    }
//
//    @SuppressLint("WrongConstant")
//    @Override
//    protected void initData() {
//        Map<String, Object> json = new HashMap<>();
//        json.put("command", 8);
//        json.put("idNumber", "8017208018");
//        json.put("batchId", 4);
//        json.put("classId", 19);
//        json.put("gradeId", 1);
//        json.put("schoolId", 1);
//        String str = new Gson().toJson(json);
//        str = str += "yzy.ruiyunqu.com";
//        System.out.println(str);
//
//        try {
//            md5 = MD5Util.getMD5(str);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        Log.d("lllll", "initData: " + md5);
//        baseInflater = LayoutInflater.from(context);
//        setBanner();
//        background.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(MustTestActivity.class);
//            }
//        });
//        refreshLayout.setOnRefreshListener(this);
//        moduleAdapter = new HomeTypeAdapter(moduleBeans, getContext());
//        recyModule.setNestedScrollingEnabled(false);
//        recyModule.setLayoutManager(new GridLayoutManager(getContext(), 4));
//        recyModule.setAdapter(moduleAdapter);
//
//
//        Intent intent = new Intent(context, DownloadService.class);
//        context.startService(intent);
//        context.bindService(intent, mConnection, BIND_AUTO_CREATE);//绑定服务
//        GridLayoutManager gridLayout = new GridLayoutManager(getContext(), 2);
//        gridLayout.setOrientation(GridLayoutManager.HORIZONTAL);
//        recySelection.setNestedScrollingEnabled(false);
//        recySelection.setLayoutManager(gridLayout);
//        dataList = new ArrayList<>();
//        selectionAdapter = new SelectionAdapter(getContext(), dataList, "selection");
//        selectionAdapter.OnsetOnClickListener(new SelectionAdapter.setOnClickListener() {
//            @Override
//            public void setOnClickListener() {
//                if (SharedPreferenceUtils.getisLogin()) {
//                    //点击轮播图
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id", selectionid);
//                    startActivity(MoreSelectionActivity.class, bundle);
//                } else {
//                    startActivity(PwdLoginActivity.class);
//                }
//
//
//            }
//        });
//        recySelection.setAdapter(selectionAdapter);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        recyVideo.setNestedScrollingEnabled(false);
//        recyVideo.setLayoutManager(gridLayoutManager);
//        videoList = new ArrayList<>();
//        videoAdapter = new VideoAdapter(getContext(), videoList);
//        recyVideo.setAdapter(videoAdapter);
//        videoAdapter.OnsetOnClickListener(new VideoAdapter.setOnClickListener() {
//            @Override
//            public void setOnClickListener() {
//                if (SharedPreferenceUtils.getisLogin()) {
//                    Intent intent = new Intent(context, MoreTeacherActivity.class);
//                    intent.putExtra("bean", 18);
//                    intent.putExtra("name", "视频资源");
//                    startActivity(intent);
//                } else {
//                    startActivity(PwdLoginActivity.class);
//                }
//            }
//
//            @Override
//            public void deleteImage(HomePagerBean.ResultBean.SectionDetailBean.DetailBean detailBean) {
//                if (SharedPreferenceUtils.getisLogin()) {
//                    //点击轮播图
//                    context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id", detailBean.getId()));
//                } else {
//                    startActivity(PwdLoginActivity.class);
//                }
//            }
//        });
//        recyTeacher.setNestedScrollingEnabled(false);
//        GridLayoutManager grid = new GridLayoutManager(getContext(), 2);
//        grid.setOrientation(RecyclerView.HORIZONTAL);
//        recyTeacher.setLayoutManager(grid);
//        teacherList = new ArrayList<>();
//        teacherAdaoter = new VideoAdapter(getContext(), teacherList);
//        recyTeacher.setAdapter(teacherAdaoter);
//        teacherAdaoter.OnsetOnClickListener(new VideoAdapter.setOnClickListener() {
//            @Override
//            public void setOnClickListener() {
//                if (SharedPreferenceUtils.getisLogin()) {
//                    Intent intent = new Intent(context, MoreTeacherActivity.class);
//                    intent.putExtra("bean", 26);
//                    intent.putExtra("name", "名师辅导");
//                    startActivity(intent);
//                } else {
//                    startActivity(PwdLoginActivity.class);
//                }
//            }
//
//            @Override
//            public void deleteImage(HomePagerBean.ResultBean.SectionDetailBean.DetailBean detailBean) {
//                if (SharedPreferenceUtils.getisLogin()) {
//                    //点击轮播图
//                    context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id", detailBean.getId()));
//                } else {
//                    startActivity(PwdLoginActivity.class);
//                }
//            }
//        });
//        listBeans = new ArrayList<>();
//        try {
//            presenter.getVersion(new Gson().toJson(new VersionJson(MyApp.version, "Android")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (isNetworkConnected(context)) {
//
//        } else {
//            ToastUtil.makeShortText(context, "网络连接好像断开了");
//
//        }
//        String huancun = SharedPreferenceUtils.getHuancun();
//        HomePagerBean homePagerBean = new Gson().fromJson(huancun, HomePagerBean.class);
//        HomeSeetings(homePagerBean);
//    }
//
//    private String getVersionName() throws Exception {
//        //获取packagemanager的实例
//        PackageManager packageManager = mActivity.getPackageManager();
//        //getPackageName()是你当前类的包名，0代表是获取版本信息
//        PackageInfo packInfo = packageManager.getPackageInfo(mActivity.getPackageName(), 0);
//        return packInfo.versionName;
//    }
//
//    public static void saveImageToGallery(Context context, Bitmap bmp) {
//        // 首先保存图片
//        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        String fileName = System.currentTimeMillis() + ".jpg";
//        File file = new File(appDir, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
//
//    }
//
//    private void setBanner() {
//        homeBanner.setImageLoader(new ImageLoader() {
//            @Override
//            public void displayImage(Context context, Object path, ImageView imageView) {
//
//
//                Glide.with(context)
//                        .load(path)
//                        .apply(new RequestOptions().error(R.drawable.icon_erroe))
//                        .into(imageView);
//
//            }
//        });
//        homeBanner.setDelayTime(3000);
//        homeBanner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                if (SharedPreferenceUtils.getisLogin()) {
//                    //点击轮播图
//                    HomePagerBean.ResultBean.BannerBean bannerBean = bean.getResult().getBanner().get(position);
//                    String type = bean.getResult().getBanner().get(position).getType();
//                    if (type.equals("list")) {
//                        Intent intent = new Intent(context, JiaocaiActivity.class);
//                        intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean(bean.getResult().getBanner().get(position).getImg_url(), bean.getResult().getBanner().get(position).getId(), "", bean.getResult().getBanner().get(position).getType()));
//                        startActivity(intent);
//                    } else if (type.equals("course")) {
//                        startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id", bannerBean.getId()));
//                    } else if (type.equals("courseList")) {
//                        Intent intent = new Intent(context, MoreTeacherActivity.class);
//                        intent.putExtra("bean", 26);
//                        intent.putExtra("name", "名师辅导");
//                    } else if (type.equals("collectionList")) {
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("id", selectionid);
//                        startActivity(MoreSelectionActivity.class, bundle);
//                    } else if (type.equals("courseModel")) {
//                        Intent intent = new Intent(context, CourseActivity.class);
//
//                        context.startActivity(intent);
//                    } else if (type.equals("vocationalTraining")) {
//                        Intent intent = new Intent(context, JiaocaiActivity.class);
//                        intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean("", 17, "职业培训", "vocationalTraining"));
//                        context.startActivity(intent);
//                    } else if (type.equals("folderList")) {
//                        Intent intent = new Intent(context, EntranceActivity.class);
//                        intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean("", bannerBean.getId(), "", type));
//                        context.startActivity(intent);
//                    } else if (type.equals("marking")) {
//                        Intent intent = new Intent(context, QueryscoreActivity.class);
//
//                        context.startActivity(intent);
//                    } else if (type.equals("scoreQuery")) {
//                        Intent intent = new Intent(context, QueryscoreActivity.class);
//
//                        context.startActivity(intent);
//                    } else if (type.equals("bookList")) {
//                        Intent intent = new Intent(context, TeachingActivity.class);
//                        intent.putExtra("bean", moduleBean);
//                        context.startActivity(intent);
//                    }else if(type.equals("onlineTest")){
//                        Intent intent = new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("type",2);
//
//                        startActivity(intent);
//                    }
//                } else {
//                    startActivity(PwdLoginActivity.class);
//                }
//
//            }
//        });
//
//
//    }
//
//
//    /**
//     * 轮播图的监听
//     *
//     * @param position
//     */
//    @Override
//    public void OnBannerClick(int position) {
//        Toast.makeText(mActivity, "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
//    }
//
//    private void sendMultipart() {
//
//        int cacheSize = 10 * 1024 * 1024;
//        //设置超时时间及缓存
//        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .writeTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS);
//        OkHttpClient mOkHttpClient = builder.build();
//
//
//        RequestBody body = new FormBody.Builder()
//                .add("country_code", "+86")
//                .add("user_string", "17611559382")
//                .add("mobile_code", "107632")
////                .add("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4")
////                .add("format", "json")
//                .build();
//
//        Request request = new Request
//                .Builder()
//                .post(body)//Post请求的参数传递
//                .url("http://bull.wanzhantong.cn/api/user/check_mobile")
//                .build();
////        try {
////            Response response = mOkHttpClient.newCall(request).execute();
////            String result = response.body().string();
////            SharedPreferenceUtils.setHuancun(result);
////            response.body().close();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, @NotNull Response response) throws IOException {
//                Log.d("test", "onResponse: " + response.body().string());
//            }
//        });
//    }
//
//    @Override
//    public void setHomePager(HomePagerBean homePager) {
//        if (homePager.getStatus() == 200) {
//            HomeSeetings(homePager);
//            recySelection.setNestedScrollingEnabled(true);
//        }
//
//    }
//
//    private void HomeSeetings(HomePagerBean homePager) {
//        bean = homePager;
//        if (homePager != null) {
//            selectionid = homePager.getResult().getSection_detail().get(0).getId();
//            dataList.addAll(homePager.getResult().getSection_detail().get(0).getDetail());
//            selectionAdapter.notifyDataSetChanged();
//
//            videoList.addAll(homePager.getResult().getSection_detail().get(1).getDetail());
//            videoAdapter.notifyDataSetChanged();
//
//            teacherList.addAll(homePager.getResult().getSection_detail().get(2).getDetail());
//            teacherAdaoter.notifyDataSetChanged();
//
//
//            moduleBean = new ModuleBean("视频资料", R.mipmap.video, homePager.getResult().getMenu().get(6).getId());
//            moduleBeans.addAll(homePager.getResult().getMenu());
//            moduleAdapter.notifyDataSetChanged();
//            mImages.clear();
//            for (int i = 0; i < homePager.getResult().getBanner().size(); i++) {
//                mImages.add(homePager.getResult().getBanner().get(i).getImg_url());
//            }
//            if (mImages != null) {
//                homeBanner.setImages(mImages);
//                homeBanner.start();
//            }
//
//
//            fragment = new ArrayList<Fragment>();
//            HomeViewpagerFragment OneFragment = new HomeViewpagerFragment();
//            Bundle onebundle = new Bundle();
//            onebundle.putSerializable("bean", (Serializable) homePager.getResult().getTest_list().getList());
//            onebundle.putString("title", homePager.getResult().getTest_list().getTitle());
//            onebundle.putInt("id", homePager.getResult().getTest_list().getId());
//            onebundle.putString("type", homePager.getResult().getTest_list().getType());
//            OneFragment.setArguments(onebundle);
//
//            HomeViewpagerFragment TwoFragment = new HomeViewpagerFragment();
//            Bundle twobundle = new Bundle();
//            twobundle.putSerializable("bean", (Serializable) homePager.getResult().getC2E().getList());
//            twobundle.putString("title", homePager.getResult().getC2E().getTitle());
//            twobundle.putInt("id", homePager.getResult().getC2E().getId());
//            twobundle.putString("type", homePager.getResult().getC2E().getType());
//            TwoFragment.setArguments(twobundle);
//
//            HomeViewpagerFragment ThreeFragment = new HomeViewpagerFragment();
//            Bundle threebundle = new Bundle();
//            threebundle.putSerializable("bean", (Serializable) homePager.getResult().getOlympiad().getList());
//            threebundle.putString("title", homePager.getResult().getOlympiad().getTitle());
//            threebundle.putInt("id", homePager.getResult().getOlympiad().getId());
//            threebundle.putString("type", homePager.getResult().getOlympiad().getType());
//            ThreeFragment.setArguments(threebundle);
//
//            HomeViewpagerFragment FourFragment = new HomeViewpagerFragment();
//            Bundle fourbundle = new Bundle();
//            fourbundle.putString("title", homePager.getResult().getLingJun().getTitle());
//            fourbundle.putInt("id", homePager.getResult().getLingJun().getId());
//            fourbundle.putString("type", homePager.getResult().getLingJun().getType());
//            fourbundle.putSerializable("bean", (Serializable) homePager.getResult().getLingJun().getList());
//            FourFragment.setArguments(fourbundle);
//            fragment.add(FourFragment);
//            fragment.add(OneFragment);
//            fragment.add(TwoFragment);
//            fragment.add(ThreeFragment);
//            FrgmentAdapter frgmentAdapter = new FrgmentAdapter(getChildFragmentManager(), fragment, new ArrayList<String>());
//            viewpager.setAdapter(frgmentAdapter);
//            viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    if (position == 0) {
//                        iconOne.setImageResource(R.mipmap.icon_right_hint);
//                        iconTwo.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconThree.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconFour.setImageResource(R.mipmap.icon_gray_yuan);
//                    } else if (position == 1) {
//                        iconTwo.setImageResource(R.mipmap.icon_right_hint);
//                        iconOne.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconThree.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconFour.setImageResource(R.mipmap.icon_gray_yuan);
//                    } else if (position == 2) {
//                        iconThree.setImageResource(R.mipmap.icon_right_hint);
//                        iconOne.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconTwo.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconFour.setImageResource(R.mipmap.icon_gray_yuan);
//                    } else {
//                        iconThree.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconOne.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconTwo.setImageResource(R.mipmap.icon_gray_yuan);
//                        iconFour.setImageResource(R.mipmap.icon_right_hint);
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
//            frgmentAdapter.notifyDataSetChanged();
//        }
//
//    }
//
//    @Override
//    public void setVersion(VersionBean version) {
//        versionBean = version;
//        if (version.getResult().getCan() == 1) {
//            dialogVersion().show();
//        }
//
//    }
//
//    @Override
//    protected HomePagerPresenter<HomePagerMvp.HomePager_View> createPresenter() {
//        return new HomePagerPresenter<>();
//    }
//
//
//    @OnClick({R.id.iv_search, R.id.tv_search})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.iv_search:
//                if (SharedPreferenceUtils.getisLogin()) {
//
//                    startActivity(new Intent(getContext(), SearchActivity.class));
//                } else {
//                    startActivity(PwdLoginActivity.class);
//                }
//
//                break;
//            case R.id.tv_search:
//                if (SharedPreferenceUtils.getisLogin()) {
//
//                    startActivity(new Intent(getContext(), SearchActivity.class));
//                } else {
//                    startActivity(PwdLoginActivity.class);
//                }
//
//                break;
//        }
//    }
//
//
//    @Override
//    public void onRefresh() {
//        recySelection.setNestedScrollingEnabled(false);
//        if (isNetworkConnected(context)) {
//            dataList.clear();
//
//            moduleBeans.clear();
//            videoList.clear();
//            teacherList.clear();
//            listBeans.clear();
//            iconOne.setImageResource(R.mipmap.icon_right_hint);
//            iconTwo.setImageResource(R.mipmap.icon_gray_yuan);
//            iconThree.setImageResource(R.mipmap.icon_gray_yuan);
//            iconFour.setImageResource(R.mipmap.icon_gray_yuan);
//            presenter.getHome(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
//        } else {
//            ToastUtil.makeShortText(context, "网络连接好像断开了");
//        }
//
//        refreshLayout.setRefreshing(false);
//    }
//
//    Dialog dialogVersion() {
//
//        View dialogView = baseInflater.inflate(R.layout.dialog_version, null);
//        final Dialog dialog = DialogUtil.showDialogCenter(getContext(), dialogView, 255);
//        TextView tvTitle = (TextView) dialogView.findViewById(R.id.content);
//
//        TextView sure = (TextView) dialogView.findViewById(R.id.btn_login);
//        ImageView nv = dialogView.findViewById(R.id.close);
//
//        tvTitle.setText(versionBean.getResult().getDescription().toString().replace("\\n", "\n").replace("\\r", "\r"));
//        sure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                APK_URL = versionBean.getResult().getDownloadPath();
//                if (mDownloadBinder != null) {
//                    Log.d("moxun", "onClick: " + APK_URL);
//                    long downloadId = mDownloadBinder.startDownload(APK_URL);
//                    startCheckProgress(downloadId);
//                }
//                ToastUtil.makeLongText(getContext(), "正在下载...");
//            }
//        });
//        if (versionBean.getResult().getIsForced() == 0) {
//            nv.setVisibility(View.VISIBLE);
//        } else {
//            nv.setVisibility(View.GONE);
//        }
//        nv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//
//            }
//        });
//        return dialog;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mDisposable != null) {
//            //取消监听
//            mDisposable.dispose();
//        }
//    }
//
//    //开始监听进度
//    private void startCheckProgress(long downloadId) {
//        Observable
//                .interval(100, 200, TimeUnit.MILLISECONDS, Schedulers.io())//无限轮询,准备查询进度,在io线程执行
//                .filter(times -> mDownloadBinder != null)
//                .map(i -> mDownloadBinder.getProgress(downloadId))//获得下载进度
//                .takeUntil(progress -> progress >= 100)//返回true就停止了,当进度>=100就是下载完成了
//                .distinct()//去重复
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ProgressObserver());
//    }
//
//
//    //观察者
//    private class ProgressObserver implements Observer<Integer> {
//
//        @Override
//        public void onSubscribe(Disposable d) {
//            mDisposable = d;
//        }
//
//        @Override
//        public void onNext(Integer progress) {
//
//        }
//
//        @Override
//        public void onError(Throwable throwable) {
//            throwable.printStackTrace();
//            Toast.makeText(context, "出错", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onComplete() {
//
//
//            apkPath = SharedPreferenceUtils.getFilepath();
//            Log.d("DownloadFinishReceiver", apkPath);
//            if (!apkPath.isEmpty()) {
//                SystemManager.setPermission(apkPath);//提升读写权限,否则可能出现解析异常
//                Log.e("DownloadFinishReceiver", "apkPath");
//                openAPKFile(context, apkPath);
//            } else {
//                Log.e("DownloadFinishReceiver", "apkPath is null");
//            }
//            Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void openAPKFile(Context mContext, String fileUri) {
//
//// 核心是下面几句代码
//        if (null != fileUri) {
//            try {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                File apkFile = new File(fileUri);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    // 由于没有在Activity环境下启动Activity,设置下面的标签
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
//                    Uri apkUri = FileProvider.getUriForFile(mContext, "com.yingzhiyun.yingquxue.fileProvider", apkFile);
//                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        boolean hasInstallPermission = mContext.getPackageManager().canRequestPackageInstalls();
//
//                        if (!hasInstallPermission) {
//                            Intent intent1 = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + "com.yingzhiyun.yingquxue"));
//
//// 不能通过NEW_TASK打开activityForResult
//
//                            startActivityForResult(intent, 20);
//                            return;
//                        }
//                    }
//                } else {
//                    intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                }
//                if (mContext.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
//
//                    mContext.startActivity(intent);
//                }
//            } catch (Throwable e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //版本更新要用
//        if (resultCode == RESULT_OK && requestCode == 20) {
//            openAPKFile(context, apkPath);
////版本安装操作
//        }
//    }
//}
