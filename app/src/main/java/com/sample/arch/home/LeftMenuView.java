package com.sample.arch.home;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class LeftMenuView extends NavigationView {

    public LeftMenuView(Context context) {
        this(context, null);
    }

    public LeftMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
