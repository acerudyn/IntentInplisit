package com.seeudin.intentinplisit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperFunction extends AppCompatActivity {
public Context c;
Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c=HelperFunction.this;
    }
    public void pindahClass(Class tujuan) {
        startActivity(new Intent(c, tujuan));
    }

        public void myAnimation(EditText editText){
            animation = AnimationUtils.loadAnimation(c, R.anim.animasigetar);
            editText.setAnimation(animation);
        }

        public void myToast(String isipesan){
            Toast.makeText(c, isipesan, Toast.LENGTH_SHORT).show();
        }

    public  String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    }