package gb.myhomework.repository

class RepositoryImplementation(private val dataSource: gb.myhomework.repository.DataSource<List<gb.myhomework.model.DataModel>>) :
    gb.myhomework.repository.Repository<List<gb.myhomework.model.DataModel>> {

    private val cache = mutableMapOf<String, List<gb.myhomework.model.DataModel>>()

    override suspend fun getData(word: String): List<gb.myhomework.model.DataModel>? {
        return if (cache.containsKey(word)) {
            cache[word]
        } else {
            cache[word] = dataSource.getData(word)
            dataSource.getData(word)
        }
    }
}
