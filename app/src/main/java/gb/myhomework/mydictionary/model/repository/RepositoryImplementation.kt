package gb.myhomework.mydictionary.model.repository

import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.datasource.DataSource
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    private val cache = mutableMapOf<String, List<DataModel>>()

    override fun getData(word: String): Observable<List<DataModel>> {
        return if (cache.containsKey(word)) {
            Observable.just(cache[word])
        } else {
            dataSource.getData(word).doOnNext { cache[word] = it }
        }
    }
}
