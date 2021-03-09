package org.techtown.callintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//뷰 객체 참조

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String data = editText.getText().toString();//입력상자에 입력된 전화번호 확인
                //전화걸기 화면을 보여줄 인텐트 객체 생성
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                startActivity(intent);//액티비티 띄우기
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                ComponentName name = new ComponentName("org.techtown.callintent",
                        "org.techtown.callintent.MenuActivity");
                intent.setComponent(name);
                startActivityForResult(intent, 101);
            }
        });
    }
}