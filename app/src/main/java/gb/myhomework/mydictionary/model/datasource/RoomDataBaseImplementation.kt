package gb.myhomework.onlinedictionary.model.datasourse

import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.datasource.DataSource
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
