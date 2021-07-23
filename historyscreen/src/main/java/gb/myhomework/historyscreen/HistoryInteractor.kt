package gb.myhomework.historyscreen

import gb.myhomework.core.interact.Interactor
import gb.myhomework.model.AppState
import gb.myhomework.model.dto.SearchResultDto
import gb.myhomework.mydictionary.utils.mapSearchResultToResult
import gb.myhomework.repository.Repository
import gb.myhomework.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}