package com.greason.preventmultievent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greason.multievent.MultiControl;
import com.greason.multievent.listener.OnPreventClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @Bind(R.id.testa)
    TextView mTest;
    @Bind(R.id.layout)
    LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        mTest.setText(TestActivity.class.getSimpleName());
        MultiControl.getInstance().addView(mTest, new OnPreventClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        MultiControl.getInstance().addView(mLayout, new OnPreventClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new TestFragment()).
                commitAllowingStateLoss();
    }
}
