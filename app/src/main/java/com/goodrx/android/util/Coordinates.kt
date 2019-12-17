package com.goodrx.android.util

/**
 *  Utility that formats the lat/lng before passing it to the endpoint
 */

class Coordinates
(private val lat: Double = 0.0, private val lng: Double = 0.0) {

  override fun toString(): String {
    return formatLatLng().toString()
  }

  private fun formatLatLng(): String? {
    return if (lat.equals(0.0) || lng.equals(0.0)) null
    else "%.6f,%.6f".format(lat,lng)
  }

  fun getLatLng(
      latlng: Coordinates?): Coordinates? {
    var location = latlng
    when {
      location?.lat == 0.0 || location?.lng == 0.0 -> location = null
    }
    return location
  }
}