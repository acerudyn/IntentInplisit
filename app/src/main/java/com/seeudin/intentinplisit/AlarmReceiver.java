package com.seeudin.intentinplisit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * Created by macbookpro on 3/13/18.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm bunyi", Toast.LENGTH_SHORT).show();
        MediaPlayer player = MediaPlayer.create(context, R.raw.alarm);
        player.start();
    }
}
