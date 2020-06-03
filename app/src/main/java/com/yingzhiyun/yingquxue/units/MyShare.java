package com.yingzhiyun.yingquxue.units;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yingzhiyun.yingquxue.R;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.qqmodel.QQ;
import cn.jiguang.share.qqmodel.QZone;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.wechat.WechatMoments;


/**
 * 分享
 */
public class MyShare {
    public static void share(final Context context, int shareType, String title, String text, String imgUrl, String linkUrl, final ShareResultCallback callBack) {
        switch (shareType) {
            case 1:
                //微信分享
                ShareParams shareParams = new ShareParams();
                shareParams.setTitle(title);
                shareParams.setText(text);
                shareParams.setShareType(Platform.SHARE_WEBPAGE);
                shareParams.setUrl(linkUrl);//必须
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon);
                shareParams.setImageData(bitmap);
                JShareInterface.share(Wechat.Name, shareParams, null);
                break;
            case 2:
                //朋友圈分享
                //微信分享
                ShareParams shareParams1 = new ShareParams();
                shareParams1.setTitle(title);
                shareParams1.setText(text);
                shareParams1.setShareType(Platform.SHARE_WEBPAGE);
                shareParams1.setUrl(linkUrl);//必须
                Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon);
                shareParams1.setImageData(bitmap1);
                JShareInterface.share(WechatMoments.Name, shareParams1, null);
                break;
            case 3:
                //QQ
                ShareParams shareParamsqq = new ShareParams();
                shareParamsqq.setTitle(title);
                shareParamsqq.setText(text);
                shareParamsqq.setShareType(Platform.SHARE_WEBPAGE);
                shareParamsqq.setUrl(linkUrl);//必须
                Bitmap bitmapqq = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon);
                shareParamsqq.setImageData(bitmapqq);
                JShareInterface.share(QQ.Name, shareParamsqq, null);
                break;

            case 4://qq空间分享
                //QQ
                ShareParams shareParamsqqc = new ShareParams();
                shareParamsqqc.setTitle(title);
                shareParamsqqc.setText(text);
                shareParamsqqc.setShareType(Platform.SHARE_WEBPAGE);
                shareParamsqqc.setUrl(linkUrl);//必须
                Bitmap bitmapqqc = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon);
                shareParamsqqc.setImageData(bitmapqqc);
                JShareInterface.share(QZone.Name, shareParamsqqc, null);
                break;

        }
    }

    public interface ShareResultCallback {
        /**
         * @param result 1成功，2失败，3取消，4我的圈子
         */
        void shareResultCallBack(int result);
    }
}
