package org.techtown.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback {
    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        //프래그먼트를 찾아 변수에 할당
        listFragment = (ListFragment)manager.findFragmentById(R.id.fragment);
        viewerFragment = (ViewerFragment)manager.findFragmentById(R.id.fragment2);
    }

    @Override
    //ImageSelectionCallback 인터페이스에 정의된 메서드 구현
    public void onImageSelected(int position){
        viewerFragment.setImage(images[position]);
    }
}