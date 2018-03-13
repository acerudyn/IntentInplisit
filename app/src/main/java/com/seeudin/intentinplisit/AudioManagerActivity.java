package com.seeudin.intentinplisit;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends HelperFunction {

    @BindView(R.id.ring)
    Button ring;
    @BindView(R.id.vibrate)
    Button vibrate;
    @BindView(R.id.silent)
    Button silent;

    AudioManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);
        manager= (AudioManager)getSystemService(c.AUDIO_SERVICE);
    }

    @OnClick({R.id.ring, R.id.vibrate, R.id.silent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ring:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //nougat
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0);
                } else {

                    manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Toast.makeText(c, "dalam mode normal", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.vibrate:
                manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(c, "dalam mode vibrate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.silent:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //nougat
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);
                } else {

                    manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    Toast.makeText(c, "dalam mode silent", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void mytoast(String s) {
    }
}
