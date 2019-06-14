package com.overswayit.plesnisavezsrbije.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StyleableRes

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
abstract class BaseCompoundView : FrameLayout {

    @get:LayoutRes
    protected abstract val layoutId: Int

    open val styleableId: IntArray?
        @StyleableRes
        get() = null

    constructor(context: Context) : super(context) {
        inflateView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inflateView(context)
        getParams(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflateView(context)
        getParams(attrs)
    }

    open fun getParams(ta: TypedArray) {}

    open fun bindViews(contentView: View) {}

    open fun bindingInflateView(inflater: LayoutInflater) {
        val view = inflater.inflate(layoutId, this, false)

        addView(view)

        bindViews(view)
    }

    private fun inflateView(ctx: Context) {
        val inflater = LayoutInflater.from(ctx)

        bindingInflateView(inflater)
    }

    private fun getParams(attrs: AttributeSet) {
        val styleableId = styleableId

        if (styleableId != null) {
            context.theme.obtainStyledAttributes(
                    attrs,
                    styleableId,
                    0, 0).apply {

                try {
                    getParams(this)
                } finally {
                    recycle()
                }
            }
        }


    }
}
