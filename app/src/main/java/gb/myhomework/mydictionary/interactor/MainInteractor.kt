package gb.myhomework.mydictionary.interactor

import gb.myhomework.model.AppState
import gb.myhomework.model.DataModel
import gb.myhomework.repository.Repository
import gb.myhomework.repository.RepositoryLocal

class MainInteractor(
    private val repositoryRemote: gb.myhomework.repository.Repository<List<gb.myhomework.model.DataModel>>,
    private val repositoryLocal: gb.myhomework.repository.RepositoryLocal<List<gb.myhomework.model.DataModel>>
) : gb.myhomework.core.interact.Interactor<gb.myhomework.model.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): gb.myhomework.model.AppState {
        val appState: gb.myhomework.model.AppState
        if (fromRemoteSource) {
            appState = gb.myhomework.model.AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = gb.myhomework.model.AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}

