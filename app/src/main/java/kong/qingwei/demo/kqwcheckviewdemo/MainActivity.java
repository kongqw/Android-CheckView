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
    private TextView mShowPassViwe;
    private EditText mEditPass;
    private Button mSubmit;
    private Button mRef;
    //     验证码：
    private int[] checkNum = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initCheckNum();
    }


    public void initView() {
        mCheckView = (CheckView) findViewById(R.id.checkView);
        mShowPassViwe = (TextView) findViewById(R.id.checkpass);
        mEditPass = (EditText) findViewById(R.id.checkTest);
        mSubmit = (Button) findViewById(R.id.submit);
        mRef = (Button) findViewById(R.id.ref);
        mSubmit.setOnClickListener(this);
        mRef.setOnClickListener(this
        );
    }

    // 初始化验证码并且刷新界面
    public void initCheckNum() {
//        checkNum = CheckUtil.getCheckNum();
//        mCheckView.setCheckNum(checkNum);
        checkNum = mCheckView.getCheckNum();
        mCheckView.invaliChenkNum();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                String userInput = mEditPass.getText().toString();
                if (CheckUtil.checkNum(userInput, checkNum)) {
                    setPassString("通过");
                    Toast.makeText(this, "通过", Toast.LENGTH_SHORT).show();
                } else {
                    setPassString("未通过");
                    Toast.makeText(this, "未通过", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ref:
                initCheckNum();
                break;
            default:
                break;

        }

    }


    public void setPassString(String passString) {
        mShowPassViwe.setText(passString);
    }


}
