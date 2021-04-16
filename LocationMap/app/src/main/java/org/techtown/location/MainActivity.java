package org.techtown.location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에 추가했던 프래그먼트 객체 참조하고 getMapAsync()메서드 호출하기
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map","지도 준비됨.");
                map = googleMap;
            }
        });

        try{
            MapsInitializer.initialize(this);
        }catch(Exception e){
            e.printStackTrace();
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startLocationService();
            }
        });
    }

    public void startLocationService() {
        //LocationManager 객체 참조
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {//이전 위치 확인
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치-> Latitude : " + latitude + "\nLongitude : " + longitude;

                textView.setText(message);
            }

            //리스너 객체 생성
            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;//10초마다 위치 정보 전달
            float minDistance = 0;

            //위치 요청
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
            Toast.makeText(getApplicationContext(), "내 위치확인 요청함.", Toast.LENGTH_LONG).show();

        }catch(SecurityException e){
            e.printStackTrace();
        }
    }
}