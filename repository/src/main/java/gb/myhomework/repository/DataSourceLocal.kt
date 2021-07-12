package gb.myhomework.repository

interface DataSourceLocal<T> : gb.myhomework.repository.DataSource<T> {
    suspend fun saveToDB(appState: gb.myhomework.model.AppState)
}
