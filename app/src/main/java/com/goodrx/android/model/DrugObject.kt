package com.goodrx.android.model

import com.google.gson.annotations.SerializedName

data class DrugObject(

	@field:SerializedName("dosage")
	val dosage: String? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("drug_slug")
	val drugSlug: String? = null,

	@field:SerializedName("dosage_form_display")
	val dosageFormDisplay: String? = null,

	@field:SerializedName("display")
	val display: String? = null,

	@field:SerializedName("drug_display")
	val drugDisplay: DrugDisplay? = null,

	@field:SerializedName("drug_market_type")
	val drugMarketType: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("drug_page_type")
	val drugPageType: String? = null,

	@field:SerializedName("is_default")
	val isDefault: Boolean? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("dosage_form_display_short")
	val dosageFormDisplayShort: String? = null,

	@field:SerializedName("dosage_display")
	val dosageDisplay: String? = null,

	@field:SerializedName("form")
	val form: String? = null,

	@field:SerializedName("form_plural")
	val formPlural: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("form_display")
	val formDisplay: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)