package gb.myhomework.mydictionary.model.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}
