package gb.myhomework.historyscreen

import androidx.lifecycle.LiveData
import gb.myhomework.mydictionary.utils.parseLocalSearchResults
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    gb.myhomework.core.viewmodel.BaseViewModel<gb.myhomework.model.AppState>() {

    private val liveDataForViewToObserve: LiveData<gb.myhomework.model.AppState> = mutableLiveData

    fun subscribe(): LiveData<gb.myhomework.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        mutableLiveData.value = gb.myhomework.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(gb.myhomework.model.AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value =
            gb.myhomework.model.AppState.Success(null)//Set View to original state in onStop
        super.onCleared()
    }
}
