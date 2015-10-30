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
