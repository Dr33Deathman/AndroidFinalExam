package kz.kamran.androidfinalexam.data

import kz.kamran.androidfinalexam.data.model.Country
import kz.kamran.androidfinalexam.data.model.Report

interface RemoteRepository {
    suspend fun getAllCountries(): List<Country>

    suspend fun getCountry(slug: String): Report
}