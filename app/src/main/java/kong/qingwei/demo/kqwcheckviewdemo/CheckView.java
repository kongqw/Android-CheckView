package kong.qingwei.demo.kqwcheckviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kongqw on 2015/10/23.
 */
public class CheckView extends View {
    Context mContext;
    int[] CheckNum = null;
    Paint mTempPaint = new Paint();
    private final int mPointNum;
    private final int mLineNum;
    private final int mTextLength;
    private final float mTextSize;
    private final int mTextColor;
    private final int mBgColor;

    // 验证码
    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mTempPaint.setAntiAlias(true);
        mTempPaint.setTextSize(Config.TEXT_SIZE);
        mTempPaint.setStrokeWidth(3);


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckView);
        // 获取随机点的个数
        mPointNum = a.getInteger(R.styleable.CheckView_point_num, 100);
        // 获取随机线的条数
        mLineNum = a.getInteger(R.styleable.CheckView_line_num, 10);
        // 获取验证码长度
        mTextLength = a.getInteger(R.styleable.CheckView_text_length, 4);
        // 获取验证码字体大小
        mTextSize = a.getDimension(R.styleable.CheckView_text_size, 30);
        // 获取验证码字体颜色
        mTextColor = a.getColor(R.styleable.CheckView_text_color, 0XFFFFFFFF);
        // 获取背景颜色
        mBgColor = a.getColor(R.styleable.CheckView_bg_color, 0XFFFFFFFF);


        // 获取验证码类型（纯数字/纯字母/混合）
        Log.d("CheckView", "point_num = " + mPointNum);
        Log.d("CheckView", "line_num = " + mLineNum);
        Log.d("CheckView", "text_length = " + mTextLength);
        Log.d("CheckView", "text_color = " + mTextColor);
        Log.d("CheckView", "text_size = " + mTextSize);
        Log.d("CheckView", "bg_color = " + mBgColor);

        a.recycle();
    }


    public void onDraw(Canvas canvas) {

        getCheckNum();

        canvas.drawColor(mBgColor);
        final int height = getHeight();
        // 获得CheckView控件的高度
        final int width = getWidth();
        // 获得CheckView控件的宽度
        int dx = width / mTextLength / 2;
        for (int i = 0; i < mTextLength; i++) {
            // 绘制验证控件上的文本
            canvas.drawText("" + CheckNum[i], dx, getPositon(height), mTempPaint);
            dx += width / (mTextLength + 1);
        }
        int[] line;
        for (int i = 0; i < Config.LINE_NUM; i++) {
            // 划线
            line = CheckUtil.getLine(height, width);
            canvas.drawLine(line[0], line[1], line[2], line[3], mTempPaint);
        }
        // 绘制小圆点
        int[] point;
        for (int i = 0; i < Config.POINT_NUM; i++) {
            // 画点
            point = CheckUtil.getPoint(height, width);
            canvas.drawCircle(point[0], point[1], 1, mTempPaint);
        }
    }

    public void invaliChenkNum() {
        invalidate();
    }

    /**
     * 产生随机数字
     *
     * @return
     */
    public int[] getCheckNum() {
        int[] tempCheckNum = new int[mTextLength];
        for (int i = 0; i < mTextLength; i++) {
            tempCheckNum[i] = (int) (Math.random() * 10);
        }
        CheckNum = tempCheckNum;
        return tempCheckNum;
    }


    /**
     * 计算验证码的绘制y点位置
     *
     * @param height 传入CheckView的高度值
     * @return
     */
    public int getPositon(int height) {
        int tempPositoin = (int) (Math.random() * height);
        if (tempPositoin < 20) {
            tempPositoin += 20;
        }
        return tempPositoin;
    }

}
