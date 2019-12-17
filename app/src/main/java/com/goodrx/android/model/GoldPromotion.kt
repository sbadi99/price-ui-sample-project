package com.goodrx.android.model

import com.google.gson.annotations.SerializedName

data class GoldPromotion(

	@field:SerializedName("savings_amount")
	val savingsAmount: Any? = null,

	@field:SerializedName("price_tier")
	val priceTier: Int? = null,

	@field:SerializedName("pharmacy_display")
	val pharmacyDisplay: Any? = null,

	@field:SerializedName("price")
	val price: Double? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("image_alt")
	val imageAlt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("drug_display")
	val drugDisplay: String? = null,

	@field:SerializedName("outbound_link")
	val outboundLink: String? = null,

	@field:SerializedName("button_text")
	val buttonText: String? = null,

	@field:SerializedName("savings_percent")
	val savingsPercent: Any? = null,

	@field:SerializedName("title")
	val title: String? = null
)