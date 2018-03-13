package com.seeudin.intentinplisit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends HelperFunction {

    @BindView(R.id.btnvideo)
    Button btnvideo;
    Uri lokasiFileVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        10);
            }
            return;
        }
    }

    @OnClick(R.id.btnvideo)
    public void onViewClicked() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        String foldercamera="Video";
        File file = new File(Environment.getExternalStorageDirectory(),foldercamera);
        if (!file.exists()){
            file.mkdir();
        }
        File isifile= new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/"+foldercamera+"/VID"+currentDate()+".mp4");
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        lokasiFileVideo = Uri.fromFile(isifile);
        //lokasiFile = FileProvider.getUriForFile(c, c.getApplicationContext().getPackageName(), isifile);

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,lokasiFileVideo);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1) {
            if(resultCode==RESULT_OK){
                myToast("Berhasil Menyimpan Video \n Lokasi : "+lokasiFileVideo.toString());
            } else if (resultCode==RESULT_CANCELED) {
                myToast("Cancel");
            } else {
                myToast("Gagal Menyipan Video");
            }
        }
    }
}
