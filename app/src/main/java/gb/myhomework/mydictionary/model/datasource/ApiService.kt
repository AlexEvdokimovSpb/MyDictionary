package gb.myhomework.mydictionary.model.datasource

import gb.myhomework.mydictionary.model.data.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>
}
