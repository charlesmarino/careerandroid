package com.charlesmarino.tustle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * View for the spash activity
 * Created by kirstiebooras on 8/14/15.
 */
public class SplashView extends LinearLayout {

    private Button mButton;

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_splash, this);
        mButton = (Button) findViewById(R.id.button);
    }

    public void setSplashOnClickListener(OnClickListener listener) {
        mButton.setOnClickListener(listener);
    }
}
