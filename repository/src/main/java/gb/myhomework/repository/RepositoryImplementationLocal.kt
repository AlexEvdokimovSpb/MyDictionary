package gb.myhomework.repository

class RepositoryImplementationLocal(private val dataSource: gb.myhomework.repository.DataSourceLocal<List<gb.myhomework.model.DataModel>>) :
    RepositoryLocal<List<gb.myhomework.model.DataModel>> {

    override suspend fun getData(word: String): List<gb.myhomework.model.DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: gb.myhomework.model.AppState) {
        dataSource.saveToDB(appState)
    }
}
