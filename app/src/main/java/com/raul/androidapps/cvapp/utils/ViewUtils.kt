package com.raul.androidapps.cvapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.extensions.safe
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ViewUtils @Inject constructor() {

    fun hasEnoughContrast(color: Int): Boolean {

        var red = Color.red(color) / 255.0
        red = if (red < 0.03928) red / 12.92 else Math.pow((red + 0.055) / 1.055, 2.4)
        var green = Color.green(color) / 255.0
        green = if (green < 0.03928) green / 12.92 else Math.pow((green + 0.055) / 1.055, 2.4)
        var blue = Color.blue(color) / 255.0
        blue = if (blue < 0.03928) blue / 12.92 else Math.pow((blue + 0.055) / 1.055, 2.4)
        return (0.2126 * red + 0.7152 * green + 0.0722 * blue).toFloat() < 0.5
    }

    fun showAlertDialog(
        activity: WeakReference<Activity>, allowCancelWhenTouchingOutside: Boolean,
        title: String? = null, message: String? = null,
        positiveButton: String? = null, callbackPositive: ((m: Unit?) -> Any?)? = null,
        negativeButton: String? = null, callbackNegative: ((m: Unit?) -> Any?)? = null
    ) {
        activity.safe {
            val builder = AlertDialog.Builder(activity.get(), R.style.CustomDialogTheme)
            title?.let { builder.setTitle(title) }
            message?.let { builder.setMessage(message) }
            positiveButton?.let {
                builder.setPositiveButton(
                    positiveButton
                ) { _, _ ->
                    callbackPositive?.invoke(null)
                }
            }
            negativeButton?.let {
                builder.setNegativeButton(
                    negativeButton
                ) { _, _ ->
                    callbackNegative?.invoke(null)
                }
            }
            builder.setCancelable(allowCancelWhenTouchingOutside)
            builder.create().show()
        }

    }

}