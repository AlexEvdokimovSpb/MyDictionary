package gb.myhomework.repository

interface DataSource<T> {
    suspend fun getData(word: String): T
}
