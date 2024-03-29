package org.techtown.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.versionedparcelable.ParcelField;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    BackgroundTask task;
    int value;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //테스크 객체 만들어 실행
                task = new BackgroundTask();
                task.execute();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                task.cancel(true);
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onProExecute(){
            value = 0;
            progressBar.setProgress(value);
        }

        protected Integer doInBackground(Integer ... values){
            while(isCancelled() == false){
                value++;
                if(value>=100){
                    break;
                }else{
                    publishProgress(value);
                }

                try{
                    Thread.sleep(100);
                }catch(InterruptedException ex){}
            }
            return value;
        }

        protected void onProgressUpdate(Integer ... values){
            progressBar.setProgress(values[0].intValue());
        }

        protected void onPostExecute(Integer result){
            progressBar.setProgress(0);
        }

        protected void onCancelled(){
            progressBar.setProgress(0);
        }
    }
}