package kz.kamran.androidfinalexam.presentation.country_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.kamran.androidfinalexam.data.RemoteRepository
import kz.kamran.androidfinalexam.presentation.country_page.state.CountryState
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    private var job = Job()
        get() {
            if (field.isCancelled){
                field = Job()
            }
            return field
        }

    private val _countryState = MutableLiveData<CountryState>(CountryState.Empty)
    val countryState:LiveData<CountryState> = _countryState

    fun getReports(slug:String){
        _countryState.postValue(CountryState.Loading)
        viewModelScope.launch(job) {
            try{
                val report = remoteRepository.getCountry(slug)
                _countryState.postValue(CountryState.Success(report))
            }catch (e:Exception){
                _countryState.postValue(CountryState.Error(e.message.toString()))
            }
        }
    }

    fun cancel() = job.cancel()

}