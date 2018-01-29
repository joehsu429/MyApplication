package com.example.joe.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView timeTV;
      static TextView onceTV;
      private TextView timesTV;
      private Button onceSetBT;
      private Button onceDeleBT;
      private Button timesSetBT;
      private Button timesDelBT;
      private Calendar c;
      private Handler mHandler;
      private SharedPreferences prefs;
      private SharedPreferences.Editor editor;

      private final static String ONCE_ALARM = "com.example.joe.ONCE_ALARM";
      private final static String TIMES_ALARM = "com.example.joe.ONCE_ALARM";
      @Override
      public void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_main);
                 timeTV = (TextView) findViewById(R.id.timeId);
                 onceTV = (TextView) findViewById(R.id.onceTextId);
                 timesTV = (TextView) findViewById(R.id.timesTextId);
                 mHandler = new Handler();
                 mHandler.post(timeThread);

                 prefs = getSharedPreferences("alarmClock", MODE_PRIVATE);
                 editor = prefs.edit();
                 if(prefs != null){
                         onceTV.setText(prefs.getString("_onceTV", "無設定"));
                         timesTV.setText(prefs.getString("_timesTV", "無設定"));
                     }

                 onceSetBT = (Button) findViewById(R.id.onceSetId);
                 onceSetBT.setOnClickListener(new ButtonListener());
                 onceDeleBT = (Button) findViewById(R.id.onceDeleId);
                 onceDeleBT.setOnClickListener(new ButtonListener());
                 timesSetBT = (Button) findViewById(R.id.timesSetId);
                 timesSetBT.setOnClickListener(new ButtonListener());
                 timesDelBT = (Button) findViewById(R.id.timesDeleId);
                 timesDelBT.setOnClickListener(new ButtonListener());

             }

              class ButtonListener implements View.OnClickListener {

                  @Override
          public void onClick(View v) {
                         // TODO Auto-generated method stub
                         switch(v.getId()){
                             case R.id.onceSetId:
                                     c = Calendar.getInstance();
                                     int hour = c.get(Calendar.HOUR_OF_DAY);
                                     int minute = c.get(Calendar.MINUTE);
                                     new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {

                              @Override
                      public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                     // TODO Auto-generated method stub
                                                     onceTV.setText("已設定成：" + changeTime(hourOfDay) + ":" + changeTime(minute));
                                                     c = timePicker(hourOfDay, minute);
                                                     AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                     Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                                     intent.setAction(ONCE_ALARM);
                                                     PendingIntent senderPI = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                                                     am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), senderPI);
                        }
                  }, hour, minute, true).show();
                                     break;
                             case R.id.onceDeleId:
                                     AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                     Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                     PendingIntent senderPI = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                                     am.cancel(senderPI);
                                     onceTV.setText("無設定");
                                    break;
                             case R.id.timesSetId:
                                     c = Calendar.getInstance();
                                     int hour1 = c.get(Calendar.HOUR_OF_DAY);
                                     int minute1 = c.get(Calendar.MINUTE);
                                     new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {

                             @Override
                     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                     // TODO Auto-generated method stub
                                                     timesTV.setText("已設定成：" + changeTime(hourOfDay) + ":" + changeTime(minute));
                                                     c = timePicker(hourOfDay, minute);
                                                     AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                     Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                                                     intent.setAction(TIMES_ALARM);
                                                     PendingIntent senderPI = PendingIntent.getBroadcast(MainActivity.this, 1, intent, 0);
                                                     am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 24*60*60*1000, senderPI);
                                                 }
                 }, hour1, minute1, true).show();
                                     break;
                             case R.id.timesDeleId:
                                     AlarmManager am1 = (AlarmManager) getSystemService(ALARM_SERVICE);
                                     Intent intent1 = new Intent(MainActivity.this, CallAlarm.class);
                                     PendingIntent senderPI1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent1, 0);
                                     am1.cancel(senderPI1);
                                     timesTV.setText("無設定");
                                     break;
                             }

                     }

             }

             private Calendar timePicker(int hourOfDay, int minute){
                 c.setTimeInMillis(System.currentTimeMillis());
                 c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                 c.set(Calendar.MINUTE, minute);
                 c.set(Calendar.SECOND, 0);
                 c.set(Calendar.MILLISECOND, 0);
                 //避免设置时间比当前时间小时 马上响应的情况发生
                 if(c.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()){
                         c.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH + 1);
                     }
                 return c;
             }


             @Override
     protected void onDestroy() {
                 // TODO Auto-generated method stub
                 super.onDestroy();
                 editor.putString("_onceTV", onceTV.getText().toString());
                 editor.putString("_timesTV", timesTV.getText().toString());
                 editor.commit();
             }


             Runnable timeThread = new Runnable() {

                 @Override
         public void run() {
                         // TODO Auto-generated method stub
                         timeTV.setText(changeTime(Calendar.getInstance().get(Calendar.HOUR))
                                         + ":" +changeTime(Calendar.getInstance().get(Calendar.MINUTE))
                                         + "  " + changeAMPM(Calendar.getInstance().get(Calendar.AM_PM)));
                         mHandler.postDelayed(timeThread, 100);
                     }
     };

             private String changeTime(int a){
                 String num = null;
                 if(a < 10){
                         num = "0" + a;
                     }else if(a > 9){
                         num = "" + a;
                     }
                 return num;
             }
     private String changeAMPM(int ap){
                 String apStr = null;
                 if(ap == 0){
                         apStr = "AM";
                     }else if(ap == 1){
                         apStr = "PM";
                     }
                 return apStr;
             }

         }