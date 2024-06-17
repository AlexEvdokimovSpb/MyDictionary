package gb.myhomework.core.interact

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}