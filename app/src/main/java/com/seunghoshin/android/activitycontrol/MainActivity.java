package com.seunghoshin.android.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btnResult;
    Intent intent;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, SubActivity.class);

        btnResult = (Button)findViewById(R.id.btnResult);
        btnStart = (Button) findViewById(R.id.btnStart);
        editText = (EditText) findViewById(R.id.editText);

        btnStart.setOnClickListener(this);
        btnResult.setOnClickListener(this);
        editText.setOnClickListener(this);
    }
    public static final int BUTTON_RESULT = 99;
    public static final int BUTTON_Start = 98;
    @Override
    public void onClick(View v) {

         switch (v.getId()){
             //일반적인 Activity start
             case R.id.btnStart:
                 //여기서 값을 설정해줄때에는 항상 고정이 되는데 이떄 remove exatra를 해서 값을 지운뒤 putextra로 다시 넣어주면 된다
                 intent.putExtra("key",editText.getText().toString());
                 startActivityForResult(intent, BUTTON_Start);
                 break;
             // 값을 돌려받는 Activity start
             case R.id.btnResult:
                // key 가 같으면 계속 덮어씌워진다
                 intent.putExtra("key", editText.getText().toString());
                                              //변수   = "값"
                 startActivityForResult(intent, BUTTON_RESULT);
                 // start SubActivity > SubActivity.finish() > 결과값을 돌려준다 > MainActivity.onActivityResult(결과값)
                 break;

         }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                                                                     //결과값이 담겨온다
        super.onActivityResult(requestCode, resultCode, data);



        if(resultCode == RESULT_OK) {
            //requestCode 는 BUTTON_RESULT가 넘어온다
            switch (requestCode) {
                case BUTTON_RESULT:
                                    // Intent 인 data에서 result 변수로 값을 꺼내는데
                                    // 값이 없을경우 디폴트값으로 0을 사용한다
                    int result = data.getIntExtra("result", 0);

                    editText.setText("결과값="+result);
                    break;

                case BUTTON_Start:

                    int start_result = data.getIntExtra("start_result",0);
                    editText.setText(start_result);
                    break;

            }
        }
    }
}
