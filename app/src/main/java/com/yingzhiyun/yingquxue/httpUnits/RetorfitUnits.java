package com.yingzhiyun.yingquxue.httpUnits;

import android.util.Log;




import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by Administrator on 2019/1/17.
 */

public class RetorfitUnits implements HttpServer {
      private FristServer mFristServer;
    private final FristServer mServer;

    public RetorfitUnits() {
           mFristServer = HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
        mServer = HttpManager.getInstance().getNoServer(FristServer.URL, FristServer.class);
    }

}
