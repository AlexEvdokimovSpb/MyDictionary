package gb.myhomework.mydictionary.interactor

import gb.myhomework.core.interact.Interactor
import gb.myhomework.model.AppState
import gb.myhomework.model.dto.SearchResultDto
import gb.myhomework.mydictionary.utils.mapSearchResultToResult
import gb.myhomework.repository.Repository
import gb.myhomework.repository.RepositoryLocal

class MainInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}


