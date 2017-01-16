package com.greason.preventmultievent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greason.multievent.MultiControl;
import com.greason.multievent.listener.OnPreventClickListener;
import com.greason.multievent.listener.OnPreventTouchListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Greason on 17/1/15.
 */

public class TestFragment extends Fragment {

    @Bind(R.id.testa)
    TextView mTest;
    @Bind(R.id.layout)
    LinearLayout mLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragmet_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mTest.setText(TestActivity.class.getSimpleName());
        MultiControl.getInstance().addView(mTest, new OnPreventClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        MultiControl.getInstance().addView(mLayout, new OnPreventTouchListener() {


            @Override
            public void onTouch(View view, MotionEvent motionEvent) {

            }
        });

    }
}
