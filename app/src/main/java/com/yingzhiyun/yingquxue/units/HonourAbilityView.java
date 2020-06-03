package com.yingzhiyun.yingquxue.units;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.icu.text.DecimalFormat;

import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yingzhiyun.yingquxue.R;


/**
 * http://git.oschina.net/tangbuzhi
 *
 * @author Tangbuzhi
 * @version V1.0
 * @Package
 * @Description: ${TODO}
 * @date: 2017/8/24
 */
public class HonourAbilityView extends View {

    //default value
    private int defDataCount = 4;
    private int defLineColor = Color.WHITE;
    private float defLineStrokeWidth = DisplayUtil.dp2px(getContext(), 1f);
    private int defDotColor = Color.RED;
    private float defDotStrokeWidth = DisplayUtil.dp2px(getContext(), 1f);
    private float defDotRadius = DisplayUtil.dp2px(getContext(), 3f);
    private int defCoverColor = Color.DKGRAY;
    private float defCoverStrokeWidth = DisplayUtil.dp2px(getContext(), 1.5f);
    private int defCenterTextSize = DisplayUtil.dp2px(getContext(), 28f);
    private int defTextSize = DisplayUtil.dp2px(getContext(), 13f);
    private int defTextColor = Color.WHITE;

    //attr value
    private int mDataCount;
    private int mLineColor;
    private float mLineStrokeWidth;
    private int mDotColor;
    private float mDotStrokeWidth;
    private float mDotRadius;
    private int mCoverColor;
    private float mCoverStrokeWidth;
    private int mTextColor;
    private float mTextSize;
    private float mCenterTextSize;

    //画笔
    private Paint mLinePaint;
    private Paint mDotPaint;
    private Paint mCoverPaint;
    private Paint mTextPaint;

    //具体绘画数据
    private float perAngle;

    private float[] datas = {0.85f, 0.96f, 0.72f, 0.91f, 0.68f, 0.96f};
    private String[] titles = {"战绩(KDA)", "推进", "输出", "发育", "团战", "生存"};

    public HonourAbilityView(Context context) {
        this(context, null);
    }

    public HonourAbilityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HonourAbilityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.honour_ability_view, defStyleAttr, 0);
        mDataCount = a.getInteger(R.styleable.honour_ability_view_honour_ability_count, defDataCount);
        mLineColor = a.getColor(R.styleable.honour_ability_view_honour_ability_line_color, defLineColor);
        mLineStrokeWidth = a.getDimension(R.styleable.honour_ability_view_honour_ability_line_width, defLineStrokeWidth);
        mDotColor = a.getColor(R.styleable.honour_ability_view_honour_ability_line_color, defDotColor);
        mDotStrokeWidth = a.getDimension(R.styleable.honour_ability_view_honour_ability_line_width, defDotStrokeWidth);
        mDotRadius = a.getDimension(R.styleable.honour_ability_view_honour_ability_line_width, defDotRadius);
        mCoverColor = a.getColor(R.styleable.honour_ability_view_honour_ability_cover_color, defCoverColor);
        mCoverStrokeWidth = a.getDimension(R.styleable.honour_ability_view_honour_ability_cover_width, defCoverStrokeWidth);
        mTextColor = a.getColor(R.styleable.honour_ability_view_honour_ability_text_color, defTextColor);
        mTextSize = a.getDimension(R.styleable.honour_ability_view_honour_ability_text_size, defTextSize);
        mCenterTextSize = a.getDimension(R.styleable.honour_ability_view_honour_ability_center_text_size, defCenterTextSize);
        a.recycle();

        if (mDataCount < 3) {
            throw new IllegalArgumentException("Edge of polygon can't be less than three");
        }

        perAngle = (float) (Math.PI * 2 / mDataCount);
        initPaint();
    }

    private void initPaint() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeWidth(mLineStrokeWidth);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStyle(Paint.Style.STROKE);

        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint.setStrokeWidth(mDotStrokeWidth);
        mDotPaint.setColor(mDotColor);
        mDotPaint.setStyle(mDotPaintStyle);

        mCoverPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoverPaint.setStrokeWidth(mCoverStrokeWidth);
        mCoverPaint.setColor(mCoverColor);
        mCoverPaint.setAlpha(120);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.FILL);
    }

    private float radius;
    private int centerX;
    private int centerY;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(w, h) / 2 * 3 / 5;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);
        drawLines(canvas);
//        drawDots(canvas);
        drawRegion(canvas);
        drawScore(canvas);
        drawTitle(canvas);
    }

    private void drawPolygon(Canvas canvas) {
        drawPolygon(canvas, 1f);

    }

    private void drawPolygon(Canvas canvas, float percent) {
        Path path = new Path();
        for (int i = 0; i < mDataCount; i++) {
            Point point = getPoint(i, percent);
            if (i == 0) {
                path.moveTo(point.x, point.y);
            } else {
                path.lineTo(point.x, point.y);
            }
        }
        path.close();
        canvas.drawPath(path, mLinePaint);
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < mDataCount; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            path.lineTo(getPoint(i, 1).x, getPoint(i, 1).y);
            canvas.drawPath(path, mLinePaint);
        }
    }

    private void drawDots(Canvas canvas) {
        for (int i = 0; i < mDataCount; i++) {
            Point point = getPoint(i, datas[i]);
            canvas.drawCircle(point.x, point.y, mDotRadius, mDotPaint);
        }
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < mDataCount; i++) {
            Point point = getPoint(i, datas[i]);
            if (i == 0) {
                path.moveTo(point.x, point.y);
            } else {
                path.lineTo(point.x, point.y);
            }
        }
        path.close();

        mCoverPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mCoverPaint);

        mCoverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, mCoverPaint);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void drawScore(Canvas canvas) {
        float total = 0f;
        for (int i = 0; i < mDataCount; i++) {
            total += datas[i];
        }
        float average = total / mDataCount;
        DecimalFormat format = new DecimalFormat("0.00");
        String text = format.format(average);
        mTextPaint.setTextSize(mCenterTextSize);
        drawText(text, centerX, centerY, mTextPaint, canvas);
    }

    private void drawText(String text, float centerX, float centerY, Paint paint, Canvas canvas) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, centerX - bounds.width() / 2, centerY + bounds.height() / 2, paint);
    }

    private void drawTitle(Canvas canvas) {
        for (int i = 0; i < mDataCount; i++) {
            Point point = getPoint(i, 1.2f);
            mTextPaint.setTextSize(mTextSize);
            drawText(titles[i], point.x, point.y, mTextPaint, canvas);
        }
    }

    /**
     * 获取各个点的坐标
     *
     * @param position 点的位置，从0开始
     * @return 返回已知坐标的点
     */
    private Point getPoint(int position, float percent) {
        int x, y;
        float x0 = (float) (radius * Math.sin(position * perAngle) * percent);
        float y0 = (float) (radius * Math.cos(position * perAngle) * percent);
        if (mDataCount % 2 != 0) {
            x = (int) (centerX + x0);
            y = (int) (centerY - y0);
        } else {
            //任意点(x,y)，绕一个坐标点(rx0,ry0)逆时针旋转a角度后的新的坐标设为(x0, y0)，有公式：
            //x0= (x - rx0)*cos(a) - (y - ry0)*sin(a) + rx0 ;
            //y0= (x - rx0)*sin(a) + (y - ry0)*cos(a) + ry0 ;
            //这里是应用矩阵旋转法则计算旋转 perAngle/2 之后点的x，y坐标
            //值得注意的是，在变换过程中，需把点还原为中心点的相对坐标，然后再加上中心点的x,y坐标即为旋转后的坐标
            float rotationAngle = (float) (perAngle / 2 - Math.PI);
            x = (int) (centerX + x0 * Math.cos(rotationAngle) - y0 * Math.sin(rotationAngle));
            y = (int) (centerY + x0 * Math.sin(rotationAngle) + y0 * Math.cos(rotationAngle));
        }
        return new Point(x, y);
    }

    public int getDataCount() {
        return mDataCount;
    }

    public HonourAbilityView setDataCount(int dataCount) {
        if (dataCount < 3) {
            throw new IllegalArgumentException("Edge of polygon can't be less than three");
        }
        mDataCount = dataCount;
        return this;
    }

    public int getLineColor() {
        return mLineColor;
    }

    public HonourAbilityView setLineColor(int lineColor) {
        mLineColor = lineColor;
        return this;
    }

    public float getLineStrokeWidth() {
        return mLineStrokeWidth;
    }

    public HonourAbilityView setLineStrokeWidth(float lineStrokeWidth) {
        mLineStrokeWidth = lineStrokeWidth;
        return this;
    }

    public int getDotColor() {
        return mDotColor;
    }

    public HonourAbilityView setDotColor(int dotColor) {
        mDotColor = dotColor;
        return this;
    }

    public float getDotStrokeWidth() {
        return mDotStrokeWidth;
    }

    public HonourAbilityView setDotStrokeWidth(float dotStrokeWidth) {
        mDotStrokeWidth = dotStrokeWidth;
        return this;
    }

    public float getDotRadius() {
        return mDotRadius;
    }

    public HonourAbilityView setDotRadius(float dotRadius) {
        mDotRadius = dotRadius;
        return this;
    }

    public int getCoverColor() {
        return mCoverColor;
    }

    public HonourAbilityView setCoverColor(int coverColor) {
        mCoverColor = coverColor;
        return this;
    }

    public float getCoverStrokeWidth() {
        return mCoverStrokeWidth;
    }

    public HonourAbilityView setCoverStrokeWidth(float coverStrokeWidth) {
        mCoverStrokeWidth = coverStrokeWidth;
        return this;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public HonourAbilityView setTextColor(int textColor) {
        mTextColor = textColor;
        return this;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public HonourAbilityView setTextSize(float textSize) {
        mTextSize = textSize;
        return this;
    }

    public float getCenterTextSize() {
        return mCenterTextSize;
    }

    public HonourAbilityView setCenterTextSize(float centerTextSize) {
        mCenterTextSize = centerTextSize;
        return this;
    }

    public float[] getDatas() {
        return datas;
    }

    public HonourAbilityView setDatas(float[] datas) {
        if (datas == null || datas.length < mDataCount) {
            throw new IllegalArgumentException("datas size can't be less than " + mDataCount);
        }
        for (int i = 0; i < datas.length; i++) {
            if (datas[i] > 1.0f) {
                throw new IllegalArgumentException("value in datas can't be more than 1.0f");
            }
        }
        this.datas = datas;
        return this;
    }

    public String[] getTitles() {
        return titles;
    }

    public HonourAbilityView setTitles(String[] titles) {
        if (titles == null || titles.length < mDataCount) {
            throw new IllegalArgumentException("titles size can't be less than " + mDataCount);
        }
        for (int i = 0; i < titles.length; i++) {
            if (StringUtils.notEmpty(titles[i]).equals("N/A")) {
                throw new IllegalArgumentException("value in titles can't be null or empty");
            }
        }
        this.titles = titles;
        return this;
    }

    private Paint.Style mDotPaintStyle = Paint.Style.FILL;

    public HonourAbilityView setDotPaintStyle(Paint.Style style) {
        this.mDotPaintStyle = style;
        return this;
    }
}
