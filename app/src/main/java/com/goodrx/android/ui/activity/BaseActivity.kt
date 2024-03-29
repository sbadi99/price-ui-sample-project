package com.goodrx.android.ui.activity

import android.Manifest.permission
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.location.LocationManagerCompat
import com.goodrx.android.R
import com.goodrx.android.ui.activity.BaseActivity.Companion.locationPermissions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks

import timber.log.Timber

/**
 * This class serves as the BaseActivity for the application.
 * This class encapsulates general items that could be inherited by other activities throughtout the application.
 * Currently, it primarily handles App permissions and location logic in terms of fetching user's current location.
 */

open class BaseActivity : AppCompatActivity(), PermissionCallbacks {

  private var fusedLocationClient: FusedLocationProviderClient? = null
  private var locationRequest: LocationRequest? = null
  private var locationCallback: LocationCallback? = null
  var currentLocation: Location? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    createLocationListener()
  }

  /**
   * Location listener that provides user's current location.
   */
  private fun createLocationListener() {
    createLocationRequest()
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    locationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult?) {
        locationResult ?: return
        locationResult.locations.forEach { location ->
          Timber.d("Current Location: $location")
          currentLocation = location
        }
      }
    }
  }

  /**
   * Create the location request
   * Note: PRIORITY_BALANCED_POWER_ACCURACY for battery life efficiency.
   */
  fun createLocationRequest() {
    locationRequest = LocationRequest.create()?.apply {
      interval = 10000
      fastestInterval = 5000
      priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }
  }

  /**
   * Note: This location updates approach is more reliable to fetch user's current location than the fusedLocationClient.getLastLocation()
   * fusedLocationClient.getLastLocation() will not return a location if Google Play services on the device has restarted,
   * and there is no active Fused Location Provider client that has requested location after the services restarted.
   */
  private fun startLocationUpdates() {
    fusedLocationClient?.requestLocationUpdates(locationRequest,
        locationCallback,
        Looper.getMainLooper())
  }

  /**
   * Remove Location updates Listener
   */
  override fun onDestroy() {
    super.onDestroy()
    fusedLocationClient?.removeLocationUpdates(locationCallback)
  }


  companion object {
    const val LOCATION_REQUEST_CODE = 1002
    val locationPermissions = arrayOf(permission.ACCESS_FINE_LOCATION,
        permission.ACCESS_COARSE_LOCATION)
  }

  /**
   * Using Google's EasyPermission's to handle App-Level permissions.
   */
  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
      grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
  }

  /**
   * EasyPermission callback when permission is granted.
   */
  override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    Timber.i("location permission granted")
    startLocationUpdates()
  }

  /**
   * EasyPermission callback when permission is granted.
   */
  override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    Timber.i("location permission denied")
    requestPermissionWithLocationRationale()
  }

  /**
   * Check Location Permission.
   */
  fun checkLocationPermission() {
    when {
      hasLocationPermission() -> {
        Timber.i("app already has location permission")
      }
      else -> {
        requestPermissionWithLocationRationale()
      }
    }
  }

  /**
   * Request Permission with rationale.
   */
  private fun requestPermissionWithLocationRationale() {
    EasyPermissions.requestPermissions(this@BaseActivity,
        getString(R.string.location_required_rationale),
        LOCATION_REQUEST_CODE, *locationPermissions)
  }

  /**
   * @param context is the context.
   * @param locationPermissions is the array of permissions.
   */
  fun hasLocationPermission(): Boolean = EasyPermissions.hasPermissions(this@BaseActivity,
      *locationPermissions)

  /**
   * When user clicks on remove location item.
   * Navigate them to app specific detail setting screen.
   */
  fun navigateToAppSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
  }

  /**
   * Note: This method provides only detects if location provider globally is enabled or not
   * It does not detect App-level location permission is enabled or not
   */
  fun isLocationEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (locationManager != null) {
      Timber.i("location is enabled")
      return LocationManagerCompat.isLocationEnabled(locationManager)
    }
    Timber.i("location is disabled")
    return false
  }

}