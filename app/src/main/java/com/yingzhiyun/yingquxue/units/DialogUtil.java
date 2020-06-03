package com.yingzhiyun.yingquxue.units;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.R;


/**
 *
 */
public class DialogUtil {


    /**
     * 在界面中间显示对话框
     */
    public static Dialog showDialogCenter(Context context, View v, int width) {
        Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(v);
        dialog.setCanceledOnTouchOutside(false);
        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
		 * 对象,这样这可以以同样的方式改变这个Activity的属性.
		 */
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        if (width != -1) {
            lp.width = DisplayUtil.dip2px(context, width);
        }

        dialogWindow.setGravity(Gravity.CENTER);

        return dialog;
    }
    /**
     * 在界面中间显示对话框
     */
    public static Dialog showDialogLeft(Context context, View v, int width) {
        Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(v);
        dialog.setCanceledOnTouchOutside(false);
        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();


        dialogWindow.setGravity(Gravity.RIGHT);

        return dialog;
    }
    /**
     * 在界面底部显示对话框
     */
    public static Dialog showDialogBottom(Context context, View v) {
        Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(v);
        dialog.setCanceledOnTouchOutside(true);

		/*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
		 * 对象,这样这可以以同样的方式改变这个Activity的属性.
		 */
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = ((Activity) context).getWindowManager().getDefaultDisplay()
                .getWidth();
        dialogWindow.setGravity(Gravity.BOTTOM);

        return dialog;
    }
    /**
     * 伴奏详情分享
     * @param c
     * @param title
     * @param text
     * @param imgUrl
     * @param linkUrl
     * @param callBack
     */
    public static void showShareMusicDialog(final Context c, final String title, final String text, final String imgUrl, final String linkUrl,  final MyShare.ShareResultCallback callBack) {
        View shareView = LayoutInflater.from(c).inflate(R.layout.dialog_share_music, null);
        LinearLayout llWechat = (LinearLayout) shareView.findViewById(R.id.ll_wechat);
        LinearLayout llMoment = (LinearLayout) shareView.findViewById(R.id.ll_friend_circle);
        LinearLayout llSina = (LinearLayout) shareView.findViewById(R.id.ll_sina);
        LinearLayout llQQ = (LinearLayout) shareView.findViewById(R.id.ll_QQ);

        TextView tvCancle = (TextView) shareView.findViewById(R.id.tv_cancel_pay);

        final Dialog dialog = DialogUtil.showDialogBottom(c, shareView);
        llWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                shareParams(c, 1, title, text, imgUrl, linkUrl, callBack);
            }
        });
        llMoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                shareParams(c, 2, title, text, imgUrl, linkUrl, callBack);
            }
        });
        llSina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                shareParams(c, 4, title, text, imgUrl, linkUrl, callBack);
            }
        });
        llQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                shareParams(c, 3, title, text, imgUrl, linkUrl, callBack);
            }
        });

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void shareParams(Context c, int shareType, String title, String text, String imgUrl, String linkUrl, MyShare.ShareResultCallback callBack){
        MyShare.share(c, shareType, title, text, imgUrl, linkUrl, callBack);
    }

    public static Dialog showPhotoPictureDialog(Context context, final DialogClickListener listener) {
        View addJobViewDialog = LayoutInflater.from(context).inflate(R.layout.dialog_photo_picture, null);
        TextView tvPhoto = (TextView) addJobViewDialog.findViewById(R.id.tv_camera);
        TextView tvPicture = (TextView) addJobViewDialog.findViewById(R.id.tv_photo);
        TextView tvCancleDialog = (TextView) addJobViewDialog.findViewById(R.id.tv_cancel);
        final Dialog dialog = DialogUtil.showDialogBottom(context, addJobViewDialog);
        tvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.dialogClick(1);
                }
            }
        });
        tvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.dialogClick(2);
                }
            }
        });

        tvCancleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.dialogClick(4);
                }
            }
        });
        return dialog;
    }

    public interface ClickOptionListener{
        void clickOptionResopnse(int option);
    }
    public interface DialogClickListener {
        void dialogClick(int type);
    }

}
