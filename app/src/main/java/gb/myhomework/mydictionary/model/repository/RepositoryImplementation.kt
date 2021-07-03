package gb.myhomework.mydictionary.model.repository

import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    private val cache = mutableMapOf<String, List<DataModel>>()

    override suspend fun getData(word: String): List<DataModel>? {
        if (cache.containsKey(word)) {
           return cache[word]
        } else {
            cache[word] = dataSource.getData(word)
            return dataSource.getData(word)
        }
    }
}
