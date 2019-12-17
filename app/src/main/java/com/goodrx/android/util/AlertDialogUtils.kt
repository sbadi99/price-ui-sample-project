package com.goodrx.android.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.goodrx.android.R
import com.goodrx.android.R.drawable
import com.goodrx.android.R.string
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * This class is a utility for various modes and types of AlertDialogs
 */
open class AlertDialogUtils {

  companion object {

    private const val EMPTY = ""

    fun setupCustomAlertDialog(context: Context,
        layout: Int = R.layout.custom_alert_dialog): Dialog {
      val dialog = Dialog(context, R.style.Theme_AppCompat_Light_Dialog_MinWidth)
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
      dialog.setContentView(layout)
      dialog.setCancelable(false)
      val view = dialog.window?.decorView
      view?.setBackgroundResource(drawable.rectangle_radius_white_bubbly)
      return dialog
    }

    fun showAlertDialog(activity: Activity, title: String = EMPTY, message: String): AlertDialog {
      return showAlertDialog(activity, title, message, null)
    }

    fun showErrorAlertDialog(activity: Activity, title: String,
        error: Throwable?, callback: OnClickListener?): Dialog {
      var message: String? = null
      when {
        error != null -> when (error) {
          is HttpException -> message = error.message()
          is UnknownHostException -> message = activity.getString(string.error_connection)
          else -> message = error.message
        }
      }
      when (error) {
        null -> message = activity.getString(string.error_retry)
      }
      return showAlertDialog(activity, title, message, callback)
    }


    private fun showAlertDialog(activity: Activity, title: String,
        message: String?, callback: OnClickListener?): AlertDialog {
      val dialog = AlertDialog.Builder(activity)
      dialog.setTitle(title)
      dialog.setMessage(message)
      dialog.setPositiveButton(activity.getString(string.ok), callback)
      return dialog.show()

    }

  }

}
