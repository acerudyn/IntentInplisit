package com.seeudin.intentinplisit;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WifiActivity extends HelperFunction {

    @BindView(R.id.wifi)
    Switch wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);
        wifi.setChecked(status());
        wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wifiChangeStatus(isChecked);
            }
        });
    }

    private void wifiChangeStatus(boolean isChecked) {
        WifiManager manager = (WifiManager)getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_WIFI_STATE,
                                Manifest.permission.CHANGE_WIFI_STATE},
                        110);


            }
            return;
        }
        else if (isChecked==true&& !manager.isWifiEnabled()){
            myToast("wifi aktif");
            manager.setWifiEnabled(true);
        }else if (isChecked==false& manager.isWifiEnabled()){
            myToast("wifi tidak aktif");
            manager.setWifiEnabled(false);
        }
    }

    private boolean status() {
        WifiManager manager = (WifiManager)getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        return manager.isWifiEnabled();
    }

    @OnClick(R.id.wifi)
    public void onViewClicked() {
    }
}
