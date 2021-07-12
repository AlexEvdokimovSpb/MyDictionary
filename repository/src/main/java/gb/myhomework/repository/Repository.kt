package gb.myhomework.repository

interface Repository<T> {
    suspend fun getData(word: String): T?
}
