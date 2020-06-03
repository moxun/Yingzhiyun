package com.yingzhiyun.yingquxue.Fragment;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AdvertisingBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.VersionJson;
import com.yingzhiyun.yingquxue.OkBean.LeftBean;
import com.yingzhiyun.yingquxue.OkBean.RightLeft;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.VersionBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.QueryscoreActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.BashiinfoActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.CourseActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.ForecastTestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.JiaocaiActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MoreSelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MustTestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.QuestionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.QuestionListActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.SearchActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.TeachingActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.mine.AboutUSActivity;
import com.yingzhiyun.yingquxue.activity.mine.MoreTeacherActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TestPagperinfoActivity;
import com.yingzhiyun.yingquxue.activity.vip.VipTopupActivity;
import com.yingzhiyun.yingquxue.activity.vip.VipinfoActivity;
import com.yingzhiyun.yingquxue.activity.zhibo.AlivePlayActivity;
import com.yingzhiyun.yingquxue.activity.zhibo.TEacherAliveActivity;
import com.yingzhiyun.yingquxue.adapter.AliveListAdapter;
import com.yingzhiyun.yingquxue.adapter.BashiAdapter;
import com.yingzhiyun.yingquxue.adapter.CourseListAdapter;
import com.yingzhiyun.yingquxue.adapter.HomeJinpinAdapter;
import com.yingzhiyun.yingquxue.adapter.MiddleZhaunquADapter;
import com.yingzhiyun.yingquxue.adapter.NewHomeListAdapter;
import com.yingzhiyun.yingquxue.adapter.TestPagperAapter;
import com.yingzhiyun.yingquxue.adapter.homepager.HomeTypeAdapter;
import com.yingzhiyun.yingquxue.base.adapter.ZhuanQuAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.httpUnits.DownloadService;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.SystemManager;
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils;
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack;
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback;
import com.yingzhiyun.yingquxue.presenter.HomePagerPresenter;
import com.yingzhiyun.yingquxue.units.DialogUtil;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.BIND_AUTO_CREATE;

public class NewHomeFragment extends BaseFragment<HomePagerMvp.HomePager_View, HomePagerPresenter<HomePagerMvp.HomePager_View>> implements HomePagerMvp.HomePager_View, OnBannerListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.recy_module)
    RecyclerView recyModule;
    @BindView(R.id.recy_sushi)
    RecyclerView recySushi;
    @BindView(R.id.recy_jingxuan)
    RecyclerView recyJingxuan;
    @BindView(R.id.text_1_1)
    TextView text11;
    @BindView(R.id.text_1_2)
    TextView text12;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.text_2_1)
    TextView text21;
    @BindView(R.id.text_2_2)
    TextView text22;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.text_3_1)
    TextView text31;
    @BindView(R.id.text_3_2)
    TextView text32;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    @BindView(R.id.mouble_resource)
    LinearLayout moubleResource;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.recy_resource)
    RecyclerView recyResource;
    @BindView(R.id.text_gaokao)
    TextView textGaokao;
    @BindView(R.id.icon_search)
    ImageView iconSearch;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.day)
    RelativeLayout day;
    @BindView(R.id.recy_gaokao)
    RecyclerView recyGaokao;
    @BindView(R.id.recy_zhongkao)
    RecyclerView recyZhongkao;
    @BindView(R.id.text_4_1)
    TextView text41;
    @BindView(R.id.text_4_2)
    TextView text42;
    @BindView(R.id.ll_4)
    LinearLayout ll4;
    @BindView(R.id.back_vip)
    ImageView backVip;
    private HomeTypeAdapter moduleAdapter;
    private List<HomePagerBean.ResultBean.MenuBean> moduleBeans = new ArrayList<>();
    private List<String> mImages = new ArrayList<>();
    private ArrayList<HomePagerBean.ResultBean.LeftBean.DetailBean> leftLists;
    private NewHomeListAdapter newHomeListAdapter;
    private ArrayList<HomePagerBean.ResultBean.RightBean.DetailBeanX> rightBeans;
    private HomeJinpinAdapter homeJinpinAdapter;
    private ArrayList<CourseBean.ResultBean> list;
    private CourseListAdapter courseListAdapter;
    private ArrayList<TestPaperListBean.ResultBean> resultBeans;
    private TestPagperAapter testPagperAapter;
    private ArrayList<ZiyuanBean.ResultBean> taojuanLists = new ArrayList<>();
    private ArrayList<HomePagerBean.ResultBean.GkzqBean> highLists = new ArrayList<>();
    private ArrayList<HomePagerBean.ResultBean.GkzqBean> middleLists = new ArrayList<>();
    private BashiAdapter schoolAdapter;
    private HomePagerBean bean;
    private static String APK_URL = "";
    private VersionBean versionBean;
    private DownloadService.DownloadBinder mDownloadBinder;
    private Disposable mDisposable;//
    private LayoutInflater baseInflater;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("mxoun", "onServiceConnected: ");
            mDownloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mDownloadBinder = null;
        }
    };
    private String md5;
    private String apkPath;
    private JSONObject jsonObject;
    private int width;
    private ZiyuanBean.ResultBean listBean;
    private TextView shoucang;
    private ZhuanQuAdapter highzhuanQuAdapter;
    private MiddleZhaunquADapter milladapter;
    private ArrayList<ZiyuanBean.ResultBean> alives;
    private AliveListAdapter aliveListAdapter;
    private RelativeLayout relativeLayout;
    private ProgressBar progressbar;
    private TextView text_xiaze;

    @Override
    public void onRefresh() {

        if (isNetworkConnected(context)) {
            mImages.clear();
            highLists.clear();
            middleLists.clear();
            moduleBeans.clear();
            leftLists.clear();
            rightBeans.clear();
            list.clear();
            rightBeans.clear();
            taojuanLists.clear();
            text11.setTextColor(Color.parseColor("#ff1090eb"));
            text12.setTextColor(Color.parseColor("#ffffffff"));
            text12.setBackgroundResource(R.drawable.shoucang);
            text21.setTextColor(Color.parseColor("#000000"));
            text22.setTextColor(Color.parseColor("#999999"));
            text22.setBackgroundResource(R.color.fff6f6f6);
            text31.setTextColor(Color.parseColor("#000000"));
            text32.setTextColor(Color.parseColor("#999999"));
            text32.setBackgroundResource(R.color.fff6f6f6);
            recyResource.setAdapter(courseListAdapter);
            if (bean != null) {
                list.addAll(bean.getResult().getKeCheng().getList());
            }

            courseListAdapter.notifyDataSetChanged();
            getHome();
        } else {
            ToastUtil.makeShortText(context, "网络连接好像断开了");
        }

        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setHomePager(HomePagerBean homePager) {


    }

    @Override
    public void setVersion(VersionBean version) {
        versionBean = version;

        if (version.getResult().getCan() == 1) {
            dialogVersion().show();
        }
    }

    public void getHome() {
        mImages.clear();
        highLists.clear();
        middleLists.clear();
        moduleBeans.clear();
        leftLists.clear();
        rightBeans.clear();
        list.clear();
        rightBeans.clear();
        taojuanLists.clear();
        text11.setTextColor(Color.parseColor("#ff1090eb"));
        text12.setTextColor(Color.parseColor("#ffffffff"));
        text12.setBackgroundResource(R.drawable.shoucang);
        text21.setTextColor(Color.parseColor("#000000"));
        text22.setTextColor(Color.parseColor("#999999"));
        text22.setBackgroundResource(R.color.fff6f6f6);
        text31.setTextColor(Color.parseColor("#000000"));
        text32.setTextColor(Color.parseColor("#999999"));
        text32.setBackgroundResource(R.color.fff6f6f6);
        recyResource.setAdapter(courseListAdapter);
        if (bean != null) {
            list.addAll(bean.getResult().getKeCheng().getList());
        }

        courseListAdapter.notifyDataSetChanged();
        MediaType mediaType = MediaType.parse("application/json");

        OkHttpUtils.postString()
                .url(FristServer.URL + "getIndexData")
                .mediaType(mediaType)
                .content(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), MyApp.version, "Android")))
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<HomePagerBean>() {
                    @Override
                    public void onError(String e) {
                        getHome();
                    }

                    @Override
                    public void onResponse(HomePagerBean homePager) throws JSONException {
                        if (homePager.getStatus() == 200) {
                            setHome(homePager);
                            String s = new Gson().toJson(homePager);
                            SharedPreferenceUtils.setHuancun(s);
                        }
                    }
                }));
    }

    private void setHome(HomePagerBean homePager) {
        bean = homePager;
        moduleBeans.addAll(homePager.getResult().getMenu());
        moduleAdapter.notifyDataSetChanged();

        for (int i = 0; i < homePager.getResult().getBanner().size(); i++) {
            mImages.add(homePager.getResult().getBanner().get(i).getImg_url());
        }
        if (mImages != null) {
            homeBanner.setImages(mImages);
            homeBanner.start();
        }

        if (homePager.getResult().getLeft() == null) {
            return;
        }
        leftLists.addAll(homePager.getResult().getLeft().getDetail());
        newHomeListAdapter.notifyDataSetChanged();

        rightBeans.addAll(homePager.getResult().getRight().getDetail());
        homeJinpinAdapter.notifyDataSetChanged();

        list.addAll(homePager.getResult().getKeCheng().getList());
        courseListAdapter.notifyDataSetChanged();

        highLists.addAll(homePager.getResult().getGkzq());
        highzhuanQuAdapter.notifyDataSetChanged();


        middleLists.addAll(homePager.getResult().getZkzq());
        milladapter.notifyDataSetChanged();

        Glide.with(mActivity).load(homePager.getResult().getSection_ad().getImg_url()).into(backVip);
        backVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiaozhuan(homePager.getResult().getSection_ad());
            }
        });
    }

    @Override
    public void setLeft(LeftBean leftBean) {
        if (leftBean.getStatus() == 200) {
            leftLists.clear();
            leftLists.addAll(leftBean.getResult().getDetail());
            newHomeListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setRight(RightLeft rightLeft) {
        if (rightLeft.getStatus() == 200) {
            rightBeans.clear();
            rightBeans.addAll(rightLeft.getResult().getDetail());
            homeJinpinAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected HomePagerPresenter<HomePagerMvp.HomePager_View> createPresenter() {
        return new HomePagerPresenter<>();
    }


    @Override
    protected void initData() throws JSONException {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SearchActivity.class);
            }
        });
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        refreshLayout.setOnRefreshListener(this);
        baseInflater = LayoutInflater.from(context);
        Intent intent = new Intent(context, DownloadService.class);
        context.startService(intent);
        context.bindService(intent, mConnection, BIND_AUTO_CREATE);//绑定服务
        moduleAdapter = new HomeTypeAdapter(moduleBeans, getContext());
        recyModule.setNestedScrollingEnabled(false);
        recyModule.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyModule.setAdapter(moduleAdapter);
        recyModule.setNestedScrollingEnabled(false);
        leftLists = new ArrayList<>();
        newHomeListAdapter = new NewHomeListAdapter(leftLists, context);
        recySushi.setLayoutManager(new GridLayoutManager(context, 2));
        recySushi.setAdapter(newHomeListAdapter);
        recySushi.setNestedScrollingEnabled(false);
        rightBeans = new ArrayList<>();
        homeJinpinAdapter = new HomeJinpinAdapter(context, rightBeans);

        recyGaokao.setLayoutManager(new GridLayoutManager(context, 3));
        highzhuanQuAdapter = new ZhuanQuAdapter(highLists, context);
        recyGaokao.setAdapter(highzhuanQuAdapter);

        recyZhongkao.setLayoutManager(new GridLayoutManager(context, 2));
        milladapter = new MiddleZhaunquADapter(middleLists, context);
        recyZhongkao.setAdapter(milladapter);


        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //得到每个type的值
                int type = recyJingxuan.getAdapter().getItemViewType(position);
                if (type == 1) {
                    return gridLayoutManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        recyJingxuan.setLayoutManager(gridLayoutManager);
        recyJingxuan.setAdapter(homeJinpinAdapter);
        recyJingxuan.setNestedScrollingEnabled(false);
        recyJingxuan.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanIndex();

                /**
                 * 不相等时说明是Grid形式显示的
                 * 然后判断是左边还有右边显示，分别设置间距为10
                 */
                if (spanSize != gridLayoutManager.getSpanCount()) {
                    if (spanIndex == 1) {
                        outRect.left = 10;
                    } else {
                        outRect.right = 10;
                    }
                }
            }
        });

        list = new ArrayList<CourseBean.ResultBean>();
        Typeface fromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/type.OTF");

        textGaokao.setTypeface(fromAsset);
        if (SharedPreferenceUtils.getisLogin()) {
            getDay();
            textGaokao.setVisibility(View.VISIBLE);
            iconSearch.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
        } else {

            textGaokao.setVisibility(View.GONE);
            iconSearch.setVisibility(View.GONE);
            search.setVisibility(View.VISIBLE);
        }
        courseListAdapter = new CourseListAdapter(list, context);
        recyResource.setLayoutManager(new LinearLayoutManager(context));
        recyResource.setAdapter(courseListAdapter);

        recyResource.setNestedScrollingEnabled(false);
        resultBeans = new ArrayList<TestPaperListBean.ResultBean>();
        testPagperAapter = new TestPagperAapter(R.layout.item_testpaper, resultBeans);
        testPagperAapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                startActivity(new Intent(context, TestPagperinfoActivity.class).putExtra("id", testPagperAapter.resultBeans.get(position).getId()).putExtra("juantype", "testPaperCheck"));
            }
        });
        schoolAdapter = new BashiAdapter(this.taojuanLists);

        schoolAdapter.OnsetOnClickListener(new BashiAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(ZiyuanBean.ResultBean resultBean, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", resultBean.getId());
                bundle.putString("title", resultBean.getTitle());
                bundle.putBoolean("is", resultBean.isFolderCollection());
                startActivity(BashiinfoActivity.class, bundle);
            }

            @Override
            public void setCollListener(ZiyuanBean.ResultBean resultBean, int position, TextView textView) {
                listBean = resultBean;
                shoucang = textView;
                CollictionList(resultBean.getId());
            }
        });
        alives = new ArrayList<>();
        aliveListAdapter = new AliveListAdapter(alives, mActivity);
        setBanner();
        getHome();
        try {
            presenter.getVersion(new Gson().toJson(new VersionJson(MyApp.version, "Android")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @OnClick({R.id.icon_search, R.id.ll_1, R.id.ll_2, R.id.ll_3, R.id.rightsu, R.id.search, R.id.ll_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_search:
                if (SharedPreferenceUtils.getisLogin()) {

                    startActivity(new Intent(getContext(), SearchActivity.class));
                } else {
                    startActivity(PwdLoginActivity.class);
                }

                break;
            case R.id.search:
                if (SharedPreferenceUtils.getisLogin()) {

                    startActivity(new Intent(getContext(), SearchActivity.class));
                } else {
                    startActivity(PwdLoginActivity.class);
                }

                break;
            case R.id.ll_1:
                text11.setTextColor(Color.parseColor("#ff1090eb"));
                text12.setTextColor(Color.parseColor("#ffffffff"));
                text12.setBackgroundResource(R.drawable.shoucang);
                text21.setTextColor(Color.parseColor("#000000"));
                text22.setTextColor(Color.parseColor("#999999"));
                text22.setBackgroundResource(R.color.fff6f6f6);
                text31.setTextColor(Color.parseColor("#000000"));
                text32.setTextColor(Color.parseColor("#999999"));
                text32.setBackgroundResource(R.color.fff6f6f6);
                text41.setTextColor(Color.parseColor("#000000"));
                text42.setTextColor(Color.parseColor("#999999"));
                text42.setBackgroundResource(R.color.fff6f6f6);
                list.clear();
                recyResource.setAdapter(courseListAdapter);
                list.addAll(bean.getResult().getKeCheng().getList());
                courseListAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_2:
                text11.setTextColor(Color.parseColor("#000000"));
                text12.setTextColor(Color.parseColor("#999999"));
                text12.setBackgroundResource(R.color.fff6f6f6);
                text21.setTextColor(Color.parseColor("#ff1090eb"));
                text22.setTextColor(Color.parseColor("#ffffffff"));
                text22.setBackgroundResource(R.drawable.shoucang);
                text31.setTextColor(Color.parseColor("#000000"));
                text32.setTextColor(Color.parseColor("#999999"));
                text32.setBackgroundResource(R.color.fff6f6f6);
                text41.setTextColor(Color.parseColor("#000000"));
                text42.setTextColor(Color.parseColor("#999999"));
                text42.setBackgroundResource(R.color.fff6f6f6);
                resultBeans.clear();
                recyResource.setAdapter(testPagperAapter);
                resultBeans.addAll(bean.getResult().getZuoTi().getList());
                testPagperAapter.notifyDataSetChanged();
                break;
            case R.id.ll_3:
                text11.setTextColor(Color.parseColor("#000000"));
                text12.setTextColor(Color.parseColor("#999999"));
                text12.setBackgroundResource(R.color.fff6f6f6);
                text21.setTextColor(Color.parseColor("#000000"));
                text22.setTextColor(Color.parseColor("#999999"));
                text22.setBackgroundResource(R.color.fff6f6f6);
                text31.setTextColor(Color.parseColor("#ff1090eb"));
                text32.setTextColor(Color.parseColor("#ffffffff"));
                text32.setBackgroundResource(R.drawable.shoucang);
                text41.setTextColor(Color.parseColor("#000000"));
                text42.setTextColor(Color.parseColor("#999999"));
                text42.setBackgroundResource(R.color.fff6f6f6);
                taojuanLists.clear();
                recyResource.setAdapter(schoolAdapter);
                taojuanLists.addAll(bean.getResult().getTaoJuan().getList());
                schoolAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_4:
                text11.setTextColor(Color.parseColor("#000000"));
                text12.setTextColor(Color.parseColor("#999999"));
                text12.setBackgroundResource(R.color.fff6f6f6);
                text21.setTextColor(Color.parseColor("#000000"));
                text22.setTextColor(Color.parseColor("#999999"));
                text22.setBackgroundResource(R.color.fff6f6f6);
                text31.setTextColor(Color.parseColor("#000000"));
                text32.setTextColor(Color.parseColor("#999999"));
                text32.setBackgroundResource(R.color.fff6f6f6);
                text41.setTextColor(Color.parseColor("#ff1090eb"));
                text42.setTextColor(Color.parseColor("#ffffffff"));
                text42.setBackgroundResource(R.drawable.shoucang);
                alives.clear();
                recyResource.setAdapter(aliveListAdapter);
                alives.addAll(bean.getResult().getZhiBoKeCheng());
                aliveListAdapter.notifyDataSetChanged();
                break;
            case R.id.rightsu:
                JSONObject sonObjectright = new JSONObject();
                try {
                    sonObjectright.put("app_user_id", SharedPreferenceUtils.getUserid());
                    sonObjectright.put("token", SharedPreferenceUtils.getToken());
                    sonObjectright.put("type", "right");


                    sonObjectright.put("version", MyApp.version);
                    sonObjectright.put("device", "Android");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                presenter.getRight(sonObjectright.toString());
                break;
        }
    }

    private void setBanner() {
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {


                Glide.with(context)
                        .load(path)

                        .apply(new RequestOptions().error(R.drawable.icon_erroe))
                        .into(imageView);

            }
        });
        homeBanner.setDelayTime(3000);
        homeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                HomePagerBean.ResultBean.BannerBean bannerBean = bean.getResult().getBanner().get(position);
                tiaozhuan(bannerBean);

            }
        });


    }

    private void tiaozhuan( HomePagerBean.ResultBean.BannerBean bannerBean) {
        if (SharedPreferenceUtils.getisLogin()) {
            //点击轮播图

            String type = bannerBean.getType();
            if (type == null) {
                return;
            }
            if (type.equals("list")) {
                Intent intent = new Intent(context, JiaocaiActivity.class);
                intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean(bannerBean.getImg_url(),bannerBean.getId(), "",bannerBean.getType()));
                startActivity(intent);
            } else if (type.equals("course")) {
                startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id", bannerBean.getId()));
            } else if (type.equals("courseList")) {
                Intent intent = new Intent(context, MoreTeacherActivity.class);
                intent.putExtra("bean", 26);
                intent.putExtra("name", "名师辅导");
            } else if (type.equals("collectionList")) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", bannerBean.getId());
                startActivity(MoreSelectionActivity.class, bundle);
            } else if (type.equals("courseModel")) {
                Intent intent = new Intent(context, CourseActivity.class).putExtra("isVip",false);

                context.startActivity(intent);
            } else if (type.equals("vocationalTraining")) {
                Intent intent = new Intent(context, JiaocaiActivity.class);
                intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean("", 17, "职业培训", "vocationalTraining"));
                context.startActivity(intent);
            } else if (type.equals("folderList")) {
                Intent intent = new Intent(context, EntranceActivity.class);
                intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean("", bannerBean.getId(), "", type));
                context.startActivity(intent);
            } else if (type.equals("marking")) {
                Intent intent = new Intent(context, QueryscoreActivity.class);

                context.startActivity(intent);
            } else if (type.equals("scoreQuery")) {
                Intent intent = new Intent(context, QueryscoreActivity.class);

                context.startActivity(intent);
            } else if (type.equals("bookList")) {
                Intent intent = new Intent(context, TeachingActivity.class);

                context.startActivity(intent);
            } else if (type.equals("onlineTest")) {
                Intent intent = new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", 2);

                startActivity(intent);
            } else if (type.equals("doIt")) {
                startActivity(new Intent(context, TestPagperinfoActivity.class).putExtra("id", bannerBean.getId()).putExtra("juantype", "testPaperCheck"));
            } else if (type.equals("mustDo")) {
                context.startActivity(new Intent(context, MustTestActivity.class).putExtra("id", bannerBean.getId()).putExtra("gradetype", bannerBean.getGradeType()));
            } else if (type.equals("bet")) {
                context.startActivity(new Intent(context, ForecastTestActivity.class).putExtra("gradetype", bannerBean.getGradeType()));
            } else if (bannerBean.getType().equals("massiveItemBank")) {
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean(bannerBean.getImg_url(), bannerBean.getId(), "", bannerBean.getType()));
                context.startActivity(intent);
            } else if (bannerBean.getType().equals("folderList-noOpt")) {
                Intent intent = new Intent(context, QuestionListActivity.class);
                intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean("", bannerBean.getId(), "", type));
                context.startActivity(intent);
            } else if (bannerBean.getType().equals("folderList-province")) {
                Intent intent = new Intent(context, QuestionListActivity.class);
                intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean("", bannerBean.getId(), "", type));
                context.startActivity(intent);
            } else if (bannerBean.getType().equals("liveCourseModel")) {
                context.startActivity(new Intent(context, TEacherAliveActivity.class));
            } else if (bannerBean.getType().equals("folder")) {
                Bundle bundle = new Bundle();
                Log.d("----------", bannerBean.isCollection() + "");
                bundle.putInt("id", bannerBean.getId());
                bundle.putString("title", bannerBean.getTitle());
                bundle.putBoolean("is", bannerBean.isCollection());
                startActivity(BashiinfoActivity.class, bundle);
            }else if(type.equals("vip")){
                startActivity(VipinfoActivity.class);
            }else if(type.equals("link")){
                Bundle bundle = new Bundle();
                bundle.putString("url",bannerBean.getLink_url());
                bundle.putString("name","");
                startActivity(AboutUSActivity.class,bundle);
            }
        } else {
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    protected void Errorcool(String msg) {
        super.showLoading(msg);
        mActivity.finish();
        startActivity(PwdLoginActivity.class);
        ToastUtil.makeShortText(context, msg);
    }

    @Override
    protected void showLoading(String msg) {
        super.showLoading(msg);

        if (listBean.isFolderCollection()) {
            shoucang.setText("收藏");
            shoucang.setBackgroundResource(R.drawable.shoucang);
            listBean.setFolderCollection(false);
            ToastUtil.makeShortText(context, "取消收藏成功");
        } else {
            shoucang.setText("已收藏");
            shoucang.setBackgroundResource(R.drawable.yiguanzhu);
            listBean.setFolderCollection(true);
            ToastUtil.makeShortText(context, "收藏成功");
        }
    }

    Dialog dialogVersion() {

        View dialogView = baseInflater.inflate(R.layout.dialog_update_apk, null);
        final Dialog dialog = DialogUtil.showDialogCenter(getContext(), dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.content);
        relativeLayout = dialogView.findViewById(R.id.pogger_re);
        progressbar = dialogView.findViewById(R.id.progressbar_pingjun);
        text_xiaze = dialogView.findViewById(R.id.text_xiaze);
        TextView sure = (TextView) dialogView.findViewById(R.id.btn_login);
        ImageView nv = dialogView.findViewById(R.id.image_x);

        tvTitle.setText(versionBean.getResult().getDescription().toString().replace("\\n", "\n").replace("\\r", "\r"));
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sure.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
                APK_URL = versionBean.getResult().getDownloadPath();
                if (mDownloadBinder != null) {
                    Log.d("moxun", "onClick: " + APK_URL);
                    long downloadId = mDownloadBinder.startDownload(APK_URL);
                    startCheckProgress(downloadId);
                }
                ToastUtil.makeLongText(getContext(), "正在下载...");
            }
        });
        if (versionBean.getResult().getIsForced() == 0) {
            nv.setVisibility(View.VISIBLE);
        } else {
            nv.setVisibility(View.GONE);
        }
        nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        return dialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            //取消监听
            mDisposable.dispose();
        }
    }

    //开始监听进度
    private void startCheckProgress(long downloadId) {
        Observable
                .interval(100, 200, TimeUnit.MILLISECONDS, Schedulers.io())//无限轮询,准备查询进度,在io线程执行
                .filter(times -> mDownloadBinder != null)
                .map(i -> mDownloadBinder.getProgress(downloadId))//获得下载进度
                .takeUntil(progress -> progress >= 100)//返回true就停止了,当进度>=100就是下载完成了
                .distinct()//去重复
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver());
    }


    //观察者
    private class ProgressObserver implements Observer<Integer> {

        @Override
        public void onSubscribe(Disposable d) {
            mDisposable = d;
        }

        @Override
        public void onNext(Integer progress) {
            progressbar.setProgress(progress);
            text_xiaze.setText(progress+"%");
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
            Toast.makeText(context, "出错", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {


            apkPath = SharedPreferenceUtils.getFilepath();
            Log.d("DownloadFinishReceiver", apkPath);
            if (!apkPath.isEmpty()) {
                SystemManager.setPermission(apkPath);//提升读写权限,否则可能出现解析异常
                Log.e("DownloadFinishReceiver", "apkPath");
                openAPKFile(context, apkPath);
            } else {
                Log.e("DownloadFinishReceiver", "apkPath is null");
            }
            Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
        }
    }

    public void openAPKFile(Context mContext, String fileUri) {

// 核心是下面几句代码
        if (null != fileUri) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                File apkFile = new File(fileUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    // 由于没有在Activity环境下启动Activity,设置下面的标签
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                    Uri apkUri = FileProvider.getUriForFile(mContext, "com.yingzhiyun.yingquxue.fileProvider", apkFile);
                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        boolean hasInstallPermission = mContext.getPackageManager().canRequestPackageInstalls();

                        if (!hasInstallPermission) {
                            Intent intent1 = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + "com.yingzhiyun.yingquxue"));

// 不能通过NEW_TASK打开activityForResult

                            startActivityForResult(intent, 20);
                            return;
                        }
                    }
                } else {
                    intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                if (mContext.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {

                    mContext.startActivity(intent);
                }
            } catch (Throwable e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //版本更新要用
        if (resultCode == RESULT_OK && requestCode == 20) {
            openAPKFile(context, apkPath);
//版本安装操作
        }
    }

    private void getDay() {


        MediaType mediaType = MediaType.parse("application/json");


        RequestBody requestBody = RequestBody.create(mediaType, "");
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url("http://192.168.0.120/yzy/app/countDown")
                .build();

        HttpManager.getInstance().getOkhttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                AdvertisingBean advertisingBean = new Gson().fromJson(string, AdvertisingBean.class);
                AdvertisingBean.ResultBean result = advertisingBean.getResult();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (advertisingBean.getStatus() == 200) {
                            if (result.getDay() != null) {
                                textGaokao.setText(Html.fromHtml("<font color='#333333' size='28'>距离高考</font><font color='#DB253B' size='28'>" + advertisingBean.getResult().getDay() + "</font><font color='#333333' size='28'>天</font>"));
                            }
                        }
                    }
                });

            }
        });
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_newhome;
    }
}
