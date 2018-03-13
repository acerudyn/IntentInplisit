package com.seeudin.intentinplisit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends HelperFunction {

    @BindView(R.id.btnaudiomanager)
    Button btnaudiomanager;
    @BindView(R.id.btnaudiorecorder)
    Button btnaudiorecorder;
    @BindView(R.id.btnbluetooth)
    Button btnbluetooth;
    @BindView(R.id.btnbrowser)
    Button btnbrowser;
    @BindView(R.id.btncamera)
    Button btncamera;
    @BindView(R.id.btnemail)
    Button btnemail;
    @BindView(R.id.btnphone)
    Button btnphone;
    @BindView(R.id.btnsms)
    Button btnsms;
    @BindView(R.id.btntts)
    Button btntts;
    @BindView(R.id.btnwifi)
    Button btnwifi;
    @BindView(R.id.btnvideo)
    Button btnvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnaudiomanager, R.id.btnaudiorecorder, R.id.btnbluetooth, R.id.btnbrowser, R.id.btncamera, R.id.btnemail, R.id.btnphone, R.id.btnsms, R.id.btntts, R.id.btnwifi, R.id.btnvideo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnaudiomanager:
                pindahClass(AudioManagerActivity.class);
                break;
            case R.id.btnaudiorecorder:
                pindahClass(AudioRecorderActivity.class);
                break;
            case R.id.btnbluetooth:
                break;
            case R.id.btnbrowser:
                pindahClass(BrowserActivity.class);
                break;
            case R.id.btncamera:
                pindahClass(CameraActivity.class);
                break;
            case R.id.btnemail:
                pindahClass(EmailActivity.class);
                break;
            case R.id.btnphone:
                pindahClass(CallPhoneActivity.class);
                break;
            case R.id.btnsms:
                pindahClass(SmsActivity.class);
                break;
            case R.id.btntts:
                pindahClass(TtsActivity.class);
                break;
            case R.id.btnwifi:
                pindahClass(WifiActivity.class);
                break;
            case R.id.btnvideo:
                pindahClass(VideoActivity.class);
                break;
        }
    }
}
