package gb.myhomework.repository

import gb.myhomework.model.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}