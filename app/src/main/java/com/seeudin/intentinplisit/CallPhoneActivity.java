package com.seeudin.intentinplisit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallPhoneActivity extends HelperFunction {

    @BindView(R.id.btncall)
    Button btncall;
    @BindView(R.id.btntampilcall)
    Button btntampilcall;
    @BindView(R.id.btnlistcontact)
    Button btnlistcontact;
    @BindView(R.id.edtnumber)
    EditText editnumber;
    @BindView(R.id.activity_call)
    RelativeLayout activityCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btncall, R.id.btntampilcall, R.id.btnlistcontact, R.id.edtnumber, R.id.activity_call})
    public void onViewClicked(View view) {
        String nohp = editnumber.getText().toString();
        switch (view.getId()) {
            case R.id.btncall:
                if (TextUtils.isEmpty(nohp)) {
                    editnumber.setError("Harus Diisi !");
                    editnumber.requestFocus();
                    myAnimation(editnumber);
                } else {
                    int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
                    if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                                this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                3);
                    } else {
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + nohp)));
                    }
                }
                break;
            case R.id.btntampilcall:
                startActivity(new Intent(Intent.ACTION_DIAL));
                break;
            case R.id.btnlistcontact:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(i, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Cursor cursor = null;
            try {
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
                if (cursor != null && cursor.moveToNext()) {
                    String phone = cursor.getString(0);
                    editnumber.setText(phone);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

}


