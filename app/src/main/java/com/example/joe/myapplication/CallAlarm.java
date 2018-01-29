package com.example.joe.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by joe on 2018/1/29.
 */

public class CallAlarm extends BroadcastReceiver {
    private final static String ONCE_ALARM = "com.example.joe.ONCE_ALARM";
    private final static String TIMES_ALARM = "com.example.joe.ONCE_ALARM";
    @Override
     public void onReceive(Context context, Intent intent) {
                 // TODO Auto-generated method stub
                 Log.v("CAT", "I'm in BroadcastReceiver");
                 Intent i = new Intent(context, WakeUp.class);
                 i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                 if(intent.getAction().equals(ONCE_ALARM)){
//                         wakeUpIntent.setAction(ONCE_ALARM);
//                     }else if(intent.getAction().endsWith(TIMES_ALARM)){
//                         wakeUpIntent.setAction(TIMES_ALARM);
//                     }
                 context.startActivity(i);
             }

         }
//    Intent i = new Intent(context, PlayMusic.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);