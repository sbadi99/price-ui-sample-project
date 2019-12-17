package com.goodrx.android.model

import com.google.gson.annotations.SerializedName

data class PharmacyObject(

	@field:SerializedName("number_of_locations")
	val numberOfLocations: Int? = null,

	@field:SerializedName("alternate_logo")
	val alternateLogo: Any? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("block_drug_name")
	val blockDrugName: Boolean? = null,

	@field:SerializedName("block_cash_price")
	val blockCashPrice: Boolean? = null,

	@field:SerializedName("closest_location")
	val closestLocation: Any? = null,

	@field:SerializedName("block_logo")
	val blockLogo: Boolean? = null,

	@field:SerializedName("has_24hr")
	val has24hr: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("use_discount_noun")
	val useDiscountNoun: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("block_pharmacy_name")
	val blockPharmacyName: Boolean? = null,

	@field:SerializedName("disclaimer")
	val disclaimer: Any? = null,

	@field:SerializedName("info")
	val info: String? = null
)