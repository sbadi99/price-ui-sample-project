package com.goodrx.android.viewmodel

import androidx.lifecycle.LiveData
import com.goodrx.android.GoodRxApp
import com.goodrx.android.api.RetrofitApiClient
import com.goodrx.android.di.component.DaggerMainActivityComponent
import com.goodrx.android.di.module.MainActivityContextModule
import com.goodrx.android.model.PriceResponse
import com.goodrx.android.util.Coordinates
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * PriceLiveData manages the API data handling
 * Allowing to decouple the API logic from the UI layer
 * Also using RxJava along with LiveData
 */
class PriceLiveData : LiveData<PriceResponse>(), ViewModelInterface {

  @Inject
  lateinit var retrofitApiClient: RetrofitApiClient

  private var error: Any? = null

  init {
    val applicationComponent = GoodRxApp.getApplicationComponent()
    val mainActivityComponent = DaggerMainActivityComponent.builder()
        .mainActivityContextModule(MainActivityContextModule(this))
        .applicationComponent(applicationComponent)
        .build()
    mainActivityComponent.injectMainActivity(this)
  }

  fun getPrice(zip: Int?, location: Coordinates?) {
    var latlng = location
    latlng = location?.getLatLng(latlng)

    retrofitApiClient?.getPrice(zip, latlng)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ responseModel ->
          Timber.i("success api reponse: $responseModel")
          postValue(responseModel)
        }, { error ->
          setError(error)
          Timber.e("showErrorAlertDialog api reponse: ", error)
          postValue( null)
        })

  }

  override fun setError(error: Any?) {
    this.error = error
  }

  override fun getError(): Any? {
    return error
  }

}