package gb.myhomework.mydictionary.model.datasource

import gb.myhomework.mydictionary.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented")
    }
}

