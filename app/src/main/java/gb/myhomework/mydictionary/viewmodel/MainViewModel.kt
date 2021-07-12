package gb.myhomework.mydictionary.viewmodel

import androidx.lifecycle.LiveData
import gb.myhomework.mydictionary.interactor.MainInteractor
import gb.myhomework.model.AppState
import gb.myhomework.mydictionary.utils.parseSearchResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(private val interactor: MainInteractor) : gb.myhomework.core.viewmodel.BaseViewModel<gb.myhomework.model.AppState>() {

    private val liveDataForViewToObserve: LiveData<gb.myhomework.model.AppState> = mutableLiveData

    fun subscribe(): LiveData<gb.myhomework.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        mutableLiveData.value = gb.myhomework.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            val result = interactor.getData(word, isOnline)
            val mapped = parseSearchResults(result)
            mutableLiveData.postValue(mapped)
        }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(gb.myhomework.model.AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = gb.myhomework.model.AppState.Success(null)
        super.onCleared()
    }
}
