package com.goodrx.android.ui.activity

import android.app.Dialog
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.TextView
import com.goodrx.android.R
import com.goodrx.android.util.AlertDialogUtils
import com.goodrx.android.util.Coordinates
import com.goodrx.android.util.ViewUtils
import com.goodrx.android.util.ZipValidator
import kotlinx.android.synthetic.main.custom_alert_dialog.cancel
import kotlinx.android.synthetic.main.custom_alert_dialog.current_location
import kotlinx.android.synthetic.main.custom_alert_dialog.enter_location
import kotlinx.android.synthetic.main.custom_alert_dialog.remove_location
import kotlinx.android.synthetic.main.custom_alert_dialog_zip.enter_zip_code
import kotlinx.android.synthetic.main.custom_alert_dialog_zip.zip_code_submit

/**
 * This class handles the logic of various user selection on the Custom AlertDialog
 */
abstract class UserAlertDialogActivity : BaseActivity() {

  abstract fun getDrugPriceListFromApi(zip:Int? = null, location: Coordinates? = null)

  override fun onResume() {
    super.onResume()
    refreshDrugPricesFromApi()
  }

  fun showLocationsCustomAlertDialog() {
    val dialog = AlertDialogUtils.setupCustomAlertDialog(this@UserAlertDialogActivity)
    toggleRemoveLocation(dialog)
    dialog.window?.setGravity(Gravity.BOTTOM)
    setupDialogClickListeners(dialog)
    dialog.show()
  }

  fun showZipCodeCustomAlertDialog() {
    val dialog = AlertDialogUtils.setupCustomAlertDialog(this@UserAlertDialogActivity,
        R.layout.custom_alert_dialog_zip)
    dialog.show()
    zipCodeTextWatcher(dialog)
  }

  /**
   * Zip code watcher which handles extra logic of toggling the submit button
   * This is based on Zip code validation
   */
  private fun zipCodeTextWatcher(dialog: Dialog) {
    val zipCode = dialog.enter_zip_code
    dialog.zip_code_submit.isEnabled = false

    zipCode.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable) {
        if (ZipValidator.isValidZipCode(zipCode.text.toString())) {
          dialog.zip_code_submit.isEnabled = true
          zipCodeSubmitClicked(dialog,zipCode.text.toString())
        } else {
          dialog.zip_code_submit.isEnabled = false
        }
      }

      override fun beforeTextChanged(s: CharSequence, start: Int,
          count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence, start: Int,
          before: Int, count: Int) {
      }
    })
  }

  private fun zipCodeSubmitClicked(dialog: Dialog, zipCode:String) {
    dialog.zip_code_submit.setOnClickListener {
      getDrugPriceListFromApi(zipCode.toInt(), null)
      dialog.dismiss()
    }
  }

  private fun setupDialogClickListeners(dialog: Dialog) {
    cancelClicked(dialog)
    currentLocationClicked(dialog)
    enterLocationClicked(dialog)
    removeLocationClicked(dialog)
  }

  fun removeLocationClicked(dialog: Dialog) {
    dialog.remove_location.setOnClickListener {
      navigateToAppSettings()
    }
  }


  fun enterLocationClicked(dialog: Dialog) {
    dialog.enter_location.setOnClickListener {
      dialog.dismiss()
      showZipCodeCustomAlertDialog()
    }
  }

  fun currentLocationClicked(dialog: Dialog) {
    dialog.current_location.setOnClickListener {
      dialog.dismiss()
      checkLocationPermission()
      refreshDrugPricesFromApi()

    }
  }

   fun refreshDrugPricesFromApi() {
     when {
       hasLocationPermission() -> getDrugPriceListFromApi(null,
           currentLocation?.latitude?.let { lat ->
             currentLocation?.longitude?.let { lng -> Coordinates(lat, lng) }
           })
       else -> {
         /**
          *  Default: (No location set or user removed location).
          *  Retrofit will auto-hide optional query params here if null values are passed here as a special case.
          */
         getDrugPriceListFromApi()
       }
     }


  }

  fun cancelClicked(dialog: Dialog) {
    dialog.cancel.setOnClickListener {
      dialog.dismiss()
    }
  }

  /**
   * Show/hide remove location based on whether user has location has location enabled or not.
   */
  private fun toggleRemoveLocation(dialog: Dialog) {
    when {
      hasLocationPermission() -> {
        val removeLocation: TextView = dialog.remove_location
        ViewUtils.toggleProgressIndicator(removeLocation, true)
      }
      else -> {
        ViewUtils.toggleProgressIndicator(remove_location)
        //User removed location. Refresh data from API
        refreshDrugPricesFromApi()
      }
    }
  }

}