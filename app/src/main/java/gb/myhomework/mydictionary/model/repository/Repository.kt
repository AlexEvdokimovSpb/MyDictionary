package gb.myhomework.mydictionary.model.repository

interface Repository<T> {
    suspend fun getData(word: String): T?
}
