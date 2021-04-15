package org.techtown.capture.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                takePicture();
            }
        });
    }

    public void takePicture(){
        if (file==null){
            file = createFile();
        }

        //File 객체로 부터 Uri 객체 만들기
        Uri fileUri = FileProvider.getUriForFile(this, "org.techtown.capture.intent.fileprovider", file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        if(intent.resolveActivity(getPackageManager())!=null){
            //사진 찍기 화면 띄우기
            startActivityForResult(intent, 101);
        }
    }

    private File createFile(){
        String filename = "capture.jpg";
        File.storageDir = Environment.getExternalStorageDirectory();
        File outFile = new File(storageDir, filename);

        return outFile;
    }
}