package com.cjp.crosswalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MainActivity";
    private XWalkView xWalkWebView;
    private ImageView mImage;
    private Button screenShotBtn;
    private Button recordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xWalkWebView = (XWalkView) findViewById(R.id.xwalkWebView);
        xWalkWebView.load("https://crosswalk-project.org", null);

        // turn on debugging
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
        xWalkWebView.load("http://www.baidu.com", null);
        // load local html file
//        xWalkWebView.load("http://www.w3.org/2010/05/video/mediaevents.html", null);

        screenShotBtn = (Button) findViewById(R.id.btn_screenshot);
        screenShotBtn.setOnClickListener(this);
        mImage = (ImageView) findViewById(R.id.img);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (xWalkWebView != null) {
            xWalkWebView.pauseTimers();
            xWalkWebView.onHide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (xWalkWebView != null) {
            xWalkWebView.resumeTimers();
            xWalkWebView.onShow();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xWalkWebView != null) {
            xWalkWebView.onDestroy();
        }
    }

    @Override
    public void onClick(View v) {
    }

}
