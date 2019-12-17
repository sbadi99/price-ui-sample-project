package com.goodrx.android.model

import com.google.gson.annotations.SerializedName

data class PricesListItems(

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("pharmacy_locations_object")
	val pharmacyLocationsObject: List<Any?>? = null,

	@field:SerializedName("pharmacy_object")
	val pharmacyObject: PharmacyObject? = null,

	@field:SerializedName("logo_secondary")
	val logoSecondary: Any? = null,

	@field:SerializedName("extras")
	val extras: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("transfer_region")
	val transferRegion: Any? = null,

	@field:SerializedName("show_discount_card")
	val showDiscountCard: Boolean? = null,

	@field:SerializedName("type_display")
	val typeDisplay: String? = null,

	@field:SerializedName("coupon_network")
	val couponNetwork: String? = null,

	@field:SerializedName("is_transfer_eligible")
	val isTransferEligible: Boolean? = null,

	@field:SerializedName("pricing_params")
	val pricingParams: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("special_discount")
	val specialDiscount: Any? = null,

	@field:SerializedName("logo")
	val logo: Any? = null,

	@field:SerializedName("savings")
	val savings: String? = null,

	@field:SerializedName("other_price_data")
	val otherPriceData: Any? = null,

	@field:SerializedName("savings_percent")
	val savingsPercent: String? = null,

	@field:SerializedName("discount_program_url")
	val discountProgramUrl: String? = null,

	@field:SerializedName("discount_program_disclaimer")
	val discountProgramDisclaimer: String? = null,

	@field:SerializedName("discount_program_description")
	val discountProgramDescription: String? = null,

	@field:SerializedName("discount_program_name")
	val discountProgramName: String? = null,

	@field:SerializedName("discount_program_cost")
	val discountProgramCost: Int? = null,

	@field:SerializedName("discount_program_length")
	val discountProgramLength: String? = null
)