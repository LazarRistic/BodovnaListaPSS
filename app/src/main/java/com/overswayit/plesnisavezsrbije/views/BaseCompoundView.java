package com.overswayit.plesnisavezsrbije.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.StyleableRes;

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public abstract class BaseCompoundView extends FrameLayout {

    public BaseCompoundView(Context context) {
        super(context);
        inflateView(context);
    }

    public BaseCompoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
        getParams(attrs);
    }

    public BaseCompoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(context);
        getParams(attrs);
    }

    protected abstract
    @LayoutRes
    int getLayoutId();

    protected
    @StyleableRes
    int[] getStyleableId() {
        return null;
    }

    protected void getParams(TypedArray ta) {
    }

    protected void bindViews(View contentView) {
    }

    private void inflateView(Context ctx) {
        LayoutInflater inflater = LayoutInflater.from(ctx);

        View view = inflater.inflate(getLayoutId(), this, false);

        addView(view);

        bindViews(view);
    }

    private void getParams(AttributeSet attrs) {
        int[] styleableId = getStyleableId();

        if (styleableId != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, styleableId);
            getParams(ta);
            ta.recycle();
        }
    }


}
