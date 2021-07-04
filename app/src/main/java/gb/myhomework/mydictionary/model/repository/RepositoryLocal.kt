package gb.myhomework.mydictionary.model.repository

import gb.myhomework.mydictionary.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
