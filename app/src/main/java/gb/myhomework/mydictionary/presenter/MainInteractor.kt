package gb.myhomework.mydictionary.presenter

import gb.myhomework.mydictionary.model.data.AppState
import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.repository.Repository
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        if (word.isBlank()) {
            return Observable.error(Throwable())
        }
        if (word.contains(";")) {
            return Observable.error(Throwable())
        }
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}

