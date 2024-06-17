package gb.myhomework.mydictionary.presenter

import gb.myhomework.mydictionary.di.NAME_LOCAL
import gb.myhomework.mydictionary.di.NAME_REMOTE
import gb.myhomework.mydictionary.model.data.AppState
import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
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

