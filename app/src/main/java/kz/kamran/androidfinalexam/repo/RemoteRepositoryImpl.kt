package kz.kamran.androidfinalexam.repo

import kz.kamran.androidfinalexam.data.RemoteRepository
import kz.kamran.androidfinalexam.data.model.Country
import kz.kamran.androidfinalexam.data.model.Report
import kz.kamran.androidfinalexam.data.remote.CovidApi
import kz.kamran.androidfinalexam.exception.InternalException
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val covidApi: CovidApi
):RemoteRepository {
    override suspend fun getAllCountries(): List<Country> {
        val response = covidApi.getAllCountries()
        if(!response.isSuccessful){
            throw InternalException("Server error")
        }

        return response.body()!!
    }

    override suspend fun getCountry(slug: String): Report {
        val response = covidApi.getCountry(slug)
        if(!response.isSuccessful){
            throw InternalException("Server error")
        }

        return response.body()!!.last()
    }
}