package gb.myhomework.mydictionary.model.datasource

import gb.myhomework.mydictionary.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}
