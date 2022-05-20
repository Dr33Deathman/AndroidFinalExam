package kz.kamran.androidfinalexam.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Report (
        @SerializedName("ID")
        val id:String,
        @SerializedName("Country")
        val country:String,
        @SerializedName("CountryCode")
        val countryCode:String,
        @SerializedName("Province")
        val province:String,
        @SerializedName("City")
        val city:String,
        @SerializedName("CityCode")
        val cityCode:String,
        @SerializedName("Lat")
        val lat:String,
        @SerializedName("Lon")
        val lon:String,
        @SerializedName("Confirmed")
        val confirmed:Int,
        @SerializedName("Deaths")
        val deaths:Int,
        @SerializedName("Recovered")
        val recovered:Int,
        @SerializedName("Active")
        val active:Int,
        @SerializedName("Date")
        val date:Date
        )