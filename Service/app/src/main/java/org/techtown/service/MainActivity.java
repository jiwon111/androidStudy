package org.techtown.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name = editText.getText().toString();

                //인텐트 객체 생성하고 부가 데이터 넣기
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command", "show");//command 키
                intent.putExtra("name", name);//name 키

                startService(intent);//서비스 시작
            }
        });
    }
}