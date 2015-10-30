#Android实现验证码
##效果图
![P1](http://img.blog.csdn.net/20151030184952477)

##使用

```
<kong.qingwei.demo.kqwcheckviewdemo.CheckView
        android:id="@+id/checkView"
        android:layout_width="200dp"
        android:layout_height="50dp"
        kongqw:bg_color="#FFFFFF00"
        kongqw:line_num="10"
        kongqw:point_num="100"
        kongqw:text_length="6"
        kongqw:text_size="30dp" />
```

###设置验证码宽度
```
android:layout_width="200dp"
```
###设置验证码高度
```
android:layout_height="50dp"
```
###设置验证码背景颜色
```
kongqw:bg_color="#FFFFFF00"
```
###设置验证码线的条数
```
kongqw:line_num="10"
```
###设置验证码点的个数
```
kongqw:point_num="100"
```
###设置验证码长度
```
kongqw:text_length="6"
```
###设置验证码字符大小
```
kongqw:text_size="30dp"
```


##验证码的类

```
package kong.qingwei.demo.kqwcheckviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by kongqw on 2015/10/23.
 */
public class CheckView extends View {
    Context mContext;
    String mCheckCode = null;
    Paint mTempPaint = new Paint();
    private final int mPointNum;
    private final int mLineNum;
    private final int mTextLength;
    private final float mTextSize;
//    private final int mTextColor;
    private final int mBgColor;

    // 验证码
    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckView);
        // 获取随机点的个数
        mPointNum = a.getInteger(R.styleable.CheckView_point_num, 0);
        // 获取随机线的条数
        mLineNum = a.getInteger(R.styleable.CheckView_line_num, 0);
        // 获取验证码长度
        mTextLength = a.getInteger(R.styleable.CheckView_text_length, 4);
        // 获取验证码字体大小
        mTextSize = a.getDimension(R.styleable.CheckView_text_size, 30);
        // 获取验证码字体颜色
//        mTextColor = a.getColor(R.styleable.CheckView_text_color, 0XFFFFFFFF);
        // 获取背景颜色
        mBgColor = a.getColor(R.styleable.CheckView_bg_color, 0XFFFFFFFF);
        a.recycle();

        mTempPaint.setAntiAlias(true);
        mTempPaint.setTextSize(mTextSize);
        mTempPaint.setStrokeWidth(3);
//        Log.d("CheckView", "point_num = " + mPointNum);
//        Log.d("CheckView", "line_num = " + mLineNum);
//        Log.d("CheckView", "text_length = " + mTextLength);
//        Log.d("CheckView", "text_color = " + mTextColor);
//        Log.d("CheckView", "text_size = " + mTextSize);
//        Log.d("CheckView", "bg_color = " + mBgColor);
    }


    public void onDraw(Canvas canvas) {
        // 生成验证码
        mCheckCode = makeCheckCode();
        // 设置二维码背景色
        canvas.drawColor(mBgColor);

        final int height = getHeight();
        // 获得CheckView控件的高度
        final int width = getWidth();
        // 获得CheckView控件的宽度
        int dx = width / mTextLength / 2;

        char[] checkNum = mCheckCode.toCharArray();
        for (int i = 0; i < mTextLength; i++) {
            // 绘制验证控件上的文本
            canvas.drawText("" + checkNum[i], dx, getPositon(height), mTempPaint);
            dx += width / (mTextLength + 1);
        }
        int[] line;
        for (int i = 0; i < mLineNum; i++) {
            // 划线
            line = getLine(height, width);
            canvas.drawLine(line[0], line[1], line[2], line[3], mTempPaint);
        }
        // 绘制小圆点
        int[] point;
        for (int i = 0; i < mPointNum; i++) {
            // 画点
            point = getPoint(height, width);
            canvas.drawCircle(point[0], point[1], 1, mTempPaint);
        }
    }

    /**
     * 生成新的验证码
     */
    public void invaliChenkCode() {
        invalidate();
    }

    public String getCheckCode() {
        return mCheckCode;
    }

    /**
     * 产生随机验证码
     *
     * @return
     */
    public String makeCheckCode() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mTextLength; i++) {
            int temp = (int) (Math.random() * 10);
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 计算验证码的绘制y点位置
     *
     * @param height 传入CheckView的高度值
     * @return
     */
    public int getPositon(int height) {
        int tempPositoin = (int) (Math.random() * height);
        if (tempPositoin < 50) {
            tempPositoin += 50;
        }
        return tempPositoin;
    }


    /**
     * 随机产生划线的起始点坐标和结束点坐标
     *
     * @param height 传入CheckView的高度值
     * @param width  传入CheckView的宽度值
     * @return 起始点坐标和结束点坐标
     */
    public static int[] getLine(int height, int width) {
        int[] tempCheckNum = {0, 0, 0, 0};
        for (int i = 0; i < 4; i += 2) {
            tempCheckNum[i] = (int) (Math.random() * width);
            tempCheckNum[i + 1] = (int) (Math.random() * height);
        }
        return tempCheckNum;
    }

    /**
     * 随机产生点的圆心点坐标
     *
     * @param height 传入CheckView的高度值
     * @param width  传入CheckView的宽度值
     * @return
     */
    public static int[] getPoint(int height, int width) {
        int[] tempCheckNum = {0, 0, 0, 0};
        tempCheckNum[0] = (int) (Math.random() * width);
        tempCheckNum[1] = (int) (Math.random() * height);
        return tempCheckNum;
    }
}
```


##测试类

```
package kong.qingwei.demo.kqwcheckviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    private CheckView mCheckView;
    private EditText mEditPass;
    private Button mSubmit;
    private Button mRef;
    //     验证码：
    private String mCheckCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控件
        mCheckView = (CheckView) findViewById(R.id.checkView);
        mEditPass = (EditText) findViewById(R.id.checkTest);
        mSubmit = (Button) findViewById(R.id.submit);
        mRef = (Button) findViewById(R.id.ref);
        mSubmit.setOnClickListener(this);
        mRef.setOnClickListener(this);

        // 生成验证码
        mCheckView.invaliChenkCode();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                if (mEditPass.getText().toString().equals(mCheckView.getCheckCode())) {
                    Toast.makeText(this, "通过", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "未通过", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ref:
                // 生成新的验证码
                mCheckView.invaliChenkCode();
                break;
            default:
                break;
        }
    }
}
```


##XML布局

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:kongqw="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <kong.qingwei.demo.kqwcheckviewdemo.CheckView
        android:id="@+id/checkView"
        android:layout_width="200dp"
        android:layout_height="50dp"
        kongqw:bg_color="#FFFFFF00"
        kongqw:line_num="10"
        kongqw:point_num="100"
        kongqw:text_length="6"
        kongqw:text_size="30dp" />

    <EditText
        android:id="@+id/checkTest"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请出入验证码" />

    <Button
        android:id="@+id/submit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="验证" />

    <Button
        android:id="@+id/ref"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="换一张" />

</LinearLayout>
```

