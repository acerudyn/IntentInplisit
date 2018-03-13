package com.seeudin.intentinplisit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioRecorderActivity extends HelperFunction {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.btnRecordStop)
    Button btnRecordStop;
    @BindView(R.id.activity_aaudio_recorder)
    RelativeLayout activityAaudioRecorder;
    MediaRecorder recorder;
    String outputfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);
        ButterKnife.bind(this);
        //btnPlay.setEnabled(false);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        110);


            }
            return;
        }
    }

    @OnClick({R.id.textView, R.id.btnPlay, R.id.btnRecordStop, R.id.activity_aaudio_recorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView:
                break;
            case R.id.btnPlay:
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setDataSource(outputfile);
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnRecordStop:

                String folderRecorder="MyRecord";
                File file = new File(Environment.getExternalStorageDirectory(),folderRecorder);
                if (!file.exists()){
                    file.mkdir();
                }
//                File isifile= new File(Environment.getExternalStorageDirectory()
//                        .getAbsolutePath()+"/"+folderRecorder+"/PIC"+currentDate()+".jpg");

                if (btnRecordStop.getText().toString().equalsIgnoreCase("RECORD")){
                    try{
                        recorder = new MediaRecorder();
                        outputfile = Environment.getExternalStorageDirectory().getAbsolutePath()
                                +folderRecorder+"/REC"+currentDate()+".3gp";
                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                        recorder.setOutputFile(outputfile);
                        recorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    recorder.start();
                    btnRecordStop.setText("STOP");
                }
                else  if (btnRecordStop.getText().toString().equalsIgnoreCase("STOP"))
                {
                    recorder.stop();
                    recorder.release();
                    recorder=null;
                    btnPlay.setEnabled(true);
                    btnRecordStop.setText("RECORD");
                }
                break;
            case R.id.activity_aaudio_recorder:
                break;
        }
    }
}
