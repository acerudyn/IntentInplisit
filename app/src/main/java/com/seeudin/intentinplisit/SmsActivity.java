package com.seeudin.intentinplisit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsActivity extends HelperFunction {

    private static final int REQUESTSMS = 2;
    @BindView(R.id.edt)
    EditText edtnohp;
    @BindView(R.id.edtmessage)
    EditText edtmessage;
    @BindView(R.id.btnsmsintent)
    Button btnsmsintent;
    @BindView(R.id.btnkirimsms)
    Button btnkirimsms;
    @BindView(R.id.activity_sms)
    LinearLayout activitySms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edt, R.id.edtmessage, R.id.btnsmsintent, R.id.btnkirimsms, R.id.activity_sms})
    public void onViewClicked(View view) {
        String nohp = edtnohp.getText().toString();
        String pesan = edtmessage.getText().toString();
        switch (view.getId()) {
            case R.id.edt:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(i, 3);
                break;
            case R.id.edtmessage:
                break;
            case R.id.btnsmsintent:
                if (TextUtils.isEmpty(nohp)) {
                    edtnohp.setError("Harus Diisi !");
                    edtnohp.requestFocus();
                    myAnimation(edtnohp);
                }
                else if (TextUtils.isEmpty(pesan)) {
                    edtmessage.setError("Harus Diisi Tidak Boleh Kosong!");
                    edtmessage.requestFocus();
                    myAnimation(edtmessage);
                }
                else
                {
                    Intent j = new Intent(Intent.ACTION_VIEW);
                    j.putExtra("address", nohp);
                    j.putExtra("sms_body", pesan);
                    j.setType("vnd.android-dir/mms-sms");
                    startActivity(j);
                }
                break;
            case R.id.btnkirimsms:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS)
                  != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.SEND_SMS)) {
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.SEND_SMS},
                                REQUESTSMS);
                    }
                }else {
                    try {
                        SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(nohp, null, pesan, null, null);
                        Toast.makeText(this, "Berhasil Mengirim SMS", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.activity_sms:
                Intent k = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                k.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(k, 1);
                break;
        }
    }
}
