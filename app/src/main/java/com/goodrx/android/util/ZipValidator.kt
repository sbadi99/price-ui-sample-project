package com.goodrx.android.util

import java.util.regex.Pattern

/**
 * Zip Validator is a utility class that validates a valid US zip code
 */
class ZipValidator {

  companion object {
    private const val zipCodeRegex:String = "^\\d{5}([-]|\\s*)?(\\d{4})?\$"

    fun isValidZipCode(zipCode: String): Boolean {
      return Pattern.matches(zipCodeRegex, zipCode)
    }
  }
}
