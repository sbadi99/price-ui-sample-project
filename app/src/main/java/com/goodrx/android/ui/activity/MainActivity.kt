package com.goodrx.android.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.goodrx.android.R
import com.goodrx.android.R.string
import com.goodrx.android.model.PriceResponse
import com.goodrx.android.ui.adapter.PriceRecyclerViewAdapter
import com.goodrx.android.util.AlertDialogUtils
import com.goodrx.android.util.Coordinates
import com.goodrx.android.util.ViewUtils.Companion.toggleProgressIndicator
import com.goodrx.android.viewmodel.PriceViewModel
import kotlinx.android.synthetic.main.main_activity.main_progress
import kotlinx.android.synthetic.main.main_activity.price_list_recyclerView
import kotlinx.android.synthetic.main.main_activity.setLocation
import timber.log.Timber

/**
 *
 * @author Shakeel Badi
 * Price Page UI Project
 *
 * This Activity is the main class responsible for displaying the Price UI List for Amoxicillian
 * Interacts with the PriceViewModel & PriceLiveData (using Android Architectural Components)
 */

class MainActivity : UserAlertDialogActivity() {

  private var priceListAdapter: PriceRecyclerViewAdapter? = null
  private var priceListObserver: Observer<PriceResponse>? = null
  protected var viewModel: PriceViewModel? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    setupPriceListUi()

    //Setup ViewModel & LiveData Observer
    initPriceViewModel()
    setUpPriceListLiveDataObserver()

    setLocation.setOnClickListener {
      showLocationsCustomAlertDialog()
    }
  }

  override fun getDrugPriceListFromApi(zip:Int?, location:Coordinates?) {
    toggleProgressIndicator(main_progress,true)
    viewModel?.priceLiveData?.getPrice(zip, location)
  }

  /**
   * LiveData Observer with extra alert dialog error handling and progress indicator
   */
  private fun setUpPriceListLiveDataObserver() {
    priceListObserver = Observer {
      if (it == null) {
        toggleProgressIndicator(main_progress)
        Timber.e("null or showErrorAlertDialog response from livedata no price list fetched.")
        AlertDialogUtils.showErrorAlertDialog(this@MainActivity, getString(string.error),
            viewModel?.priceLiveData?.error as Throwable?, null)
      }
      it?.let { priceResponse ->
        priceResponse.prices?.let { priceList -> priceListAdapter?.setData(priceList) }
        toggleProgressIndicator(main_progress)
        Timber.i("success api reponse: $priceResponse.prices")
      }
    }

    viewModel?.priceLiveData?.observe(this,
        priceListObserver as Observer<PriceResponse>)
  }

  private fun initPriceViewModel() {
    viewModel = ViewModelProviders.of(this).get(PriceViewModel::class.java)
  }

  private fun setupPriceListUi() {
    val priceListRecyclerView = setupPriceListRecyclerView()
    setupPriceListAdaper(priceListRecyclerView)
  }

  private fun setupPriceListRecyclerView(): RecyclerView {
    val priceListRecyclerView = price_list_recyclerView
    priceListRecyclerView.addItemDecoration(
        DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    return priceListRecyclerView
  }

  private fun setupPriceListAdaper(priceListRecyclerView: RecyclerView) {
    priceListAdapter = PriceRecyclerViewAdapter(this@MainActivity)
    priceListRecyclerView.adapter = priceListAdapter
  }

}
