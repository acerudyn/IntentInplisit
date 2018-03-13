package com.seeudin.intentinplisit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.ACTION_VIEW;

public class BrowserActivity extends AppCompatActivity {

    @BindView(R.id.btnakseslink)
    Button btnakseslink;
    @BindView(R.id.activity_browser_actvity)
    RelativeLayout activityBrowserActvity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnakseslink, R.id.activity_browser_actvity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnakseslink:
                startActivity(new Intent(ACTION_VIEW, Uri.parse(getString(R.string.webku))));
                break;
            case R.id.activity_browser_actvity:
                break;
        }
    }
}
