package com.greason.preventmultievent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greason.multievent.MultiControl;
import com.greason.multievent.listener.OnPreventClickListener;
import com.greason.multievent.listener.OnPreventListener;
import com.greason.multievent.listener.OnPreventTouchListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.test)
    TextView mTest;
    @Bind(R.id.layout)
    LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MultiControl.getInstance().setClickDelayTime(1000);
        MultiControl.getInstance().setTouchDelayTime(1000);
        MultiControl.getInstance().setForceCleanNum(80);

        mTest.setText(MainActivity.class.getSimpleName());
        MultiControl.getInstance().addView(mTest, new OnPreventClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplication(), TestActivity.class);
                startActivityForResult(intent, 0);

            }
        });
        MultiControl.getInstance().addView(mTest, new OnPreventTouchListener() {

            @Override
            public void onTouch(View view, MotionEvent motionEvent) {

            }

        });

        MultiControl.getInstance().addView(mLayout, new OnPreventListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplication(), TestActivity.class);
                startActivityForResult(intent, 1);

            }

            @Override
            public void onTouch(View view, MotionEvent motionEvent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MultiControl.getInstance().onDestroy();
    }
}
