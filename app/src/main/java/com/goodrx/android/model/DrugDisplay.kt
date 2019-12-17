package com.goodrx.android.model

import com.google.gson.annotations.SerializedName

data class DrugDisplay(

	@field:SerializedName("secondary_title_html")
	val secondaryTitleHtml: String? = null,

	@field:SerializedName("secondary_title")
	val secondaryTitle: String? = null,

	@field:SerializedName("header_title_html")
	val headerTitleHtml: String? = null,

	@field:SerializedName("header_title")
	val headerTitle: String? = null,

	@field:SerializedName("primary_title_html")
	val primaryTitleHtml: String? = null,

	@field:SerializedName("primary_title")
	val primaryTitle: String? = null
)