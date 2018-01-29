package com.example.joe.myapplication;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WakeUp extends AppCompatActivity {
//    private Button okButton;
//    private SharedPreferences prefs;
//    private SharedPreferences.Editor editor;
//    private final static String ONCE_ALARM = "com.example.joe.ONCE_ALARM";
//    private MediaPlayer mediaPlayer;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wake_up);
//        prefs = getSharedPreferences("alarmClock", MODE_PRIVATE);
//        mediaPlayer = new MediaPlayer();
//        try {
//            //mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/mp3/trhxn.mp3");
//            mediaPlayer = MediaPlayer.create(this, R.raw.y);
//            //mediaPlayer.prepare();
//            mediaPlayer.setLooping(true);
//            mediaPlayer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        okButton = (Button) findViewById(R.id.okButtonId);
//        okButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                if(getIntent().getAction().equals(ONCE_ALARM)){
//                    editor = prefs.edit();
//                    editor.putString("_onceTV", "暂无设置");
//                    editor.commit();
//                    MainActivity.onceTV.setText("暂无设置");
//                }
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                finish();
//            }
//        });
//
//
//
//    }
//
//
//}

    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);
        mp = MediaPlayer.create(this, R.raw.y);
        mp.start();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mp.stop();
        mp.release();
    }


}