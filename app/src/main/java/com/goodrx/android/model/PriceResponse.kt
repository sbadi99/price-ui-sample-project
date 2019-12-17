package com.goodrx.android.model

import com.google.gson.annotations.SerializedName

data class PriceResponse(

	@field:SerializedName("price_disclaimer_text")
	val priceDisclaimerText: String? = null,

	@field:SerializedName("formatted_average_price")
	val formattedAveragePrice: String? = null,

	@field:SerializedName("drug_id")
	val drugId: Int? = null,

	@field:SerializedName("seo_savings")
	val seoSavings: Int? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("drug_object")
	val drugObject: DrugObject? = null,

	@field:SerializedName("banner")
	val banner: Any? = null,

	@field:SerializedName("no_price_disclaimer")
	val noPriceDisclaimer: String? = null,

	@field:SerializedName("formatted_low_price")
	val formattedLowPrice: String? = null,

	@field:SerializedName("drug_error_disclaimer")
	val drugErrorDisclaimer: String? = null,

	@field:SerializedName("gold_promotion")
	val goldPromotion: GoldPromotion? = null,

	@field:SerializedName("sponsored_listing")
	val sponsoredListing: Any? = null,

	@field:SerializedName("formatted_percent_saved")
	val formattedPercentSaved: String? = null,

	@field:SerializedName("low_price")
	val lowPrice: String? = null,

	@field:SerializedName("prices")
	val prices: List<PricesListItems>?=null
)