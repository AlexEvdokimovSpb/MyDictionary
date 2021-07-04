package gb.myhomework.mydictionary.model.repository

import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    private val cache = mutableMapOf<String, List<DataModel>>()

    override suspend fun getData(word: String): List<DataModel>? {
        return if (cache.containsKey(word)) {
            cache[word]
        } else {
            cache[word] = dataSource.getData(word)
            dataSource.getData(word)
        }
    }
}
