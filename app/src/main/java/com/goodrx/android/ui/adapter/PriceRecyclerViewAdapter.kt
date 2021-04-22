package com.goodrx.android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goodrx.android.R
import com.goodrx.android.model.PricesListItems
import kotlinx.android.synthetic.main.price_list_items.view.drugPrice
import kotlinx.android.synthetic.main.price_list_items.view.paymentTypeDisplay
import kotlinx.android.synthetic.main.price_list_items.view.pharmacyName

/**
 * The PriceRecyclerViewAdapter item adapter class extends RecyclerView.Adapter
 */
class PriceRecyclerViewAdapter
/**
 * Constructor
 * @param context Activity context
 */
(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private val drugPriceList = arrayListOf<PricesListItems>()

  /**
   * @param parent The ViewGroup into which the new View will be added after it is bound to an
   * adapter position.
   * @param viewType The view type of the new View.
   * @return viewHolder
   */
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    var viewHolder: RecyclerView.ViewHolder? = null
    val inflater = LayoutInflater.from(parent.context)
    viewHolder = getViewHolder(parent, inflater)
    return viewHolder
  }

  /**
   * @param parent The ViewGroup into which the new View will be added after it is bound to an
   * adapter position.
   * @param inflater The LayoutInflater
   * @return viewHolder
   */
  private fun getViewHolder(parent: ViewGroup,
      inflater: LayoutInflater): RecyclerView.ViewHolder {
    val viewHolder: RecyclerView.ViewHolder
    val view = inflater.inflate(R.layout.price_list_items, parent, false)
    viewHolder = PriceViewHolder(view)
    return viewHolder
  }

  /**
   * @param holder The ViewHolder which should be updated to represent the contents of the item
   * at the given position in the data set.
   * @param position The position of the item within the adapter's data set.
   */
  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val pricesListItems = drugPriceList[position]
    val priceViewHolder = holder as PriceViewHolder
    priceViewHolder.pharmacyName.text = pricesListItems.pharmacyObject?.name
    priceViewHolder.paymentTypeDisplay.text = pricesListItems.typeDisplay
    priceViewHolder.drugPrice.text = pricesListItems.price
    //test
  }

  override fun getItemCount(): Int {
    return drugPriceList.size
  }

  /**
   * Add the drugPriceList to the adapter
   * @param priceList is the price items list
   */
  fun setData(priceList: List<PricesListItems>) {
    when {
      !priceList.isNullOrEmpty() -> {
        this.drugPriceList.addAll(priceList)
        notifyDataSetChanged()
      }
    }
  }

  /**
   * Content ViewHolder containing PriceList item elements
   */
  private inner class PriceViewHolder(itemView: View) : RecyclerView.ViewHolder(
      itemView) {
    val pharmacyName: TextView = itemView.pharmacyName
    val paymentTypeDisplay: TextView = itemView.paymentTypeDisplay
    val drugPrice: TextView = itemView.drugPrice
  }
}
