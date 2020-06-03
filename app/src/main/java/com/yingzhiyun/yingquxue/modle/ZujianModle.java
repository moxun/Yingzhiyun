package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.ZujianMvp;
import com.yingzhiyun.yingquxue.OkBean.KnowledgeBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.OkBean.TestPaperBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ZujianModle implements ZujianMvp.Zujian_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getKnowledge(ZujianMvp.Zujian_CallBack zujian_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getKnowledge(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<KnowledgeBean>(zujian_callBack) {
                    @Override
                    public void onNext(KnowledgeBean value) {
                        zujian_callBack.showKnowledge(value);
                    }
                });

    }

    @Override
    public void getTestPaper(ZujianMvp.Zujian_CallBack zujian_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getTestpaper(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TestPagperInfo>(zujian_callBack) {
                    @Override
                    public void onNext(TestPagperInfo value) {
                        zujian_callBack.showTestPaPer(value);
                    }
                });
    }
}
