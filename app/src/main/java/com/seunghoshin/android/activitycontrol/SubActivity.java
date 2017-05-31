package com.seunghoshin.android.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {


    TextView textView;
    EditText editText;
    Button button;

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        // 1. 이전 activity에서 넘어온 intent 객체
        final Intent intent = getIntent(); // 여기는 null이 안된다
        // 2. 값의 묶음을 꺼낸다
        Bundle bundle = intent.getExtras(); // 여기는 전달된 값이 없으면 null이 된다 , putextra가 아무것도 없기 때문에
        // 3. 단일 값을 꺼낸다 꺼내기전에 null 체크를 해줘야 한다
        if(bundle != null) {
            value = bundle.getString("key");
            // 3.1 값이 있으면 textview에 출력한다
            textView.setText(value);
        }
        //위 의 두줄(2,3번)을 합쳐놓은 method
        //자체적으로 bundle에 대한 null 처리 로직을 포함하고 있다
        //String value = intent.getStringExtra("key");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity에서 넘겨받은 값을 int로 변환
                int num1 = Integer.parseInt(value);
                // 현재 Activity에 입력된 값을 받아서
                String temp = editText.getText().toString();
                String temp2 = editText.getText().toString();
                // int 로 변환
                int num2 = Integer.parseInt(temp);
                int num3 = Integer.parseInt(temp2);
                int result = num1 + num2 ;
                int result2 = num3 + num1;

                /* 값 반환하기 */

                // 1. 결과값을 intent에 담아서
                Intent intent = new Intent();
                intent.putExtra("result", result);
                intent.putExtra("start_result", result2);
                // 2. setResult에 넘겨준다
                // RESULT_OK 커맨드로 들어가보면 -1로 상수로 정의 되어있다
                // 데이터가 subActivity에 있다 값을 담아주기만 한다
                setResult(RESULT_OK, intent);

                // 3. 현재 activity를 종료한다
                finish();

            }
        });

    }
}
