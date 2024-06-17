package gb.myhomework.repository

import gb.myhomework.model.AppState
import gb.myhomework.model.DataModel
import gb.myhomework.mydictionary.utils.convertDataModelSuccessToEntity
import gb.myhomework.mydictionary.utils.mapHistoryEntityToSearchResult
import gb.myhomework.repository.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
