package kz.kamran.androidfinalexam.presentation.home_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.kamran.androidfinalexam.data.RemoteRepository
import kz.kamran.androidfinalexam.presentation.home_page.state.HomeState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
): ViewModel() {
    private var job = Job()
        get() {
            if (field.isCancelled){
                field = Job()
            }
            return field
        }

    private val _homeState = MutableLiveData<HomeState>(HomeState.Empty)
    val homeState:LiveData<HomeState> = _homeState

    init {
        getList()
    }

    fun getList(){
        cancel()
        _homeState.postValue(HomeState.Loading)
        viewModelScope.launch(job) {
            try{
                val countries = remoteRepository.getAllCountries()
                _homeState.postValue(HomeState.Success(countries))
            }catch (e:Exception){
                _homeState.postValue(HomeState.Error(e.message.toString()))
            }
        }
    }

    fun cancel() = job.cancel()
}