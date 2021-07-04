package gb.myhomework.mydictionary.model.datasource

import gb.myhomework.mydictionary.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}
