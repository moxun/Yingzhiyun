package com.yingzhiyun.yingquxue.units;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.R;

import java.util.Calendar;
import java.util.Date;

/**
 * 自定义 日期 选择 器  只能选择 日月年
 * Created by Administrator on 2016/6/22.
 */
public class DateTimeDialogOnlyYMD extends AlertDialog implements View.OnClickListener {

    private DatePicker mDatePicker;
    private MyOnDateSetListener onDateSetListener;

    private TextView cancleButton, okButton;

    // 控制 日期
    private int measureWidth;
    // 是否 显示 日选择器   true 显示 ，false 隐藏
    private boolean isDayVisible = true;
    // 是否 显示 月选择器   true 显示 ，false 隐藏
    private boolean isMonthVisible = true;
    // 是否 显示 年选择器   true 显示 ，false 隐藏
    private boolean isYearVisible = true;
    private Dialog dialog;


    protected DateTimeDialogOnlyYMD(Context context) {
        super(context);
    }

    protected DateTimeDialogOnlyYMD(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * @param context        上下文对象
     * @param callBack       选择 监听器
     * @param isDayVisible   日 是否可见
     * @param isMonthVisible 月 是否可见
     * @param isYearVisible  年 是否可见
     */
    public DateTimeDialogOnlyYMD(Context context, MyOnDateSetListener callBack, boolean isDayVisible, boolean isMonthVisible, boolean isYearVisible) {
        super(context);
        this.isDayVisible = isDayVisible;
        this.isMonthVisible = isMonthVisible;
        this.isYearVisible = isYearVisible;
        this.onDateSetListener = callBack;
        this.isDayVisible = isDayVisible;
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());;
        View view = inflater.inflate(R.layout.view_date_picker_dialog, null);

        mDatePicker = (DatePicker) view.findViewById(R.id.datePicker);
        RelativeLayout buttonGroup =  view.findViewById(R.id.buttonGroup);
        cancleButton = (TextView) view.findViewById(R.id.cancelButton);
        okButton = (TextView) view.findViewById(R.id.okButton);

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());

        cancleButton.setOnClickListener(this);
        okButton.setOnClickListener(this);

        // 是否 显示 年
        if (!this.isYearVisible) {
            ((ViewGroup) ((ViewGroup) mDatePicker.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
        }

        // 是否 显示 月
        if (!this.isMonthVisible) {
            ((ViewGroup) ((ViewGroup) mDatePicker.getChildAt(0)).getChildAt(0)).getChildAt(1).setVisibility(View.GONE);
        }

        // 是否 显示 日
        if (!this.isDayVisible) {
            ((ViewGroup) ((ViewGroup) mDatePicker.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
        }


        dialog = DialogUtil.showDialogCenter(getContext(), view, 300);
//        Log.i("testss", this.measureWidth + "measuredWidth");
    }


    public void hideOrShow() {

        if (!this.isShowing()) {
            dialog.show();
            //设置 显示 的 宽高

        } else {
            dialog.dismiss();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
                dialog.dismiss();
                break;
            case R.id.okButton:
                onOkButtonClick();
                dialog.dismiss();
                break;
        }
    }

    /**
     * 确认 按钮 点击 事件
     */
    private void onOkButtonClick() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mDatePicker.getYear());
        calendar.set(Calendar.MONTH, mDatePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, mDatePicker.getDayOfMonth());
        calendar.getTime();
        if (onDateSetListener != null) {
            onDateSetListener.onDateSet(calendar.getTime());
        }
        Log.i("testss", mDatePicker.getYear() + "====" + (mDatePicker.getMonth() + 1) + "==" + mDatePicker.getDayOfMonth());
    }

    /**
     * 时间  改变 监听
     */
    public interface MyOnDateSetListener {
        void onDateSet(Date date);
    }

}
