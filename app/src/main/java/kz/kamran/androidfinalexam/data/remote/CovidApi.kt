package kz.kamran.androidfinalexam.data.remote

import kz.kamran.androidfinalexam.data.model.Country
import kz.kamran.androidfinalexam.data.model.Report
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {
    @GET("countries")
    suspend fun getAllCountries(): Response<List<Country>>

    @GET("country/{slug}")
    suspend fun getCountry(@Path("slug") slug: String): Response<List<Report>>
}