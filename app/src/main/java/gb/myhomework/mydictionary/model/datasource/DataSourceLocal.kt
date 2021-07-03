package gb.myhomework.mydictionary.model.datasource

import gb.myhomework.mydictionary.model.data.DataModel

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}
