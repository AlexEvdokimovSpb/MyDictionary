package gb.myhomework.historyscreen

class HistoryInteractor(
    private val repositoryRemote: gb.myhomework.repository.Repository<List<gb.myhomework.model.DataModel>>,
    private val repositoryLocal: gb.myhomework.repository.RepositoryLocal<List<gb.myhomework.model.DataModel>>
) : gb.myhomework.core.interact.Interactor<gb.myhomework.model.AppState> {

    override suspend fun getData(
        word: String,
        fromRemoteSource: Boolean
    ): gb.myhomework.model.AppState {
        return gb.myhomework.model.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
