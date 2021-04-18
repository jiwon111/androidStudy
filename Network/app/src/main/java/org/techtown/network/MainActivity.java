package org.techtown.network;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkConnectivity();
            }
        });
    }

    public void checkConnectivity(){
        //ConnectivityManager 객체 확인하기
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info!=null){
            if(info.getType() == ConnectivityManager.TYPE_WIFI){
                println("Wifi로 설정됨");
            }else if(info.getType()==ConnectivityManager.TYPE_MOBILE){
                println("일반망으로 설정됨");
            }

            //연결 여부 확인하기
            println("연결 여부 : "+info.isConnected());
        }else{
            println("데이터통신 불가");
        }
    }

    public void println(String data){
        textView.append(data+"\n");
    }
}