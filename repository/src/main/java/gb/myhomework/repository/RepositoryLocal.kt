package gb.myhomework.repository

interface RepositoryLocal<T> : gb.myhomework.repository.Repository<T> {

    suspend fun saveToDB(appState: gb.myhomework.model.AppState)
}
