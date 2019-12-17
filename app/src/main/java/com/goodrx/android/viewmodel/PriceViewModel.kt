package com.goodrx.android.viewmodel

import androidx.lifecycle.ViewModel

class PriceViewModel : ViewModel() {

  var priceLiveData: PriceLiveData? = null

  init {
    priceLiveData = PriceLiveData()
  }
}

