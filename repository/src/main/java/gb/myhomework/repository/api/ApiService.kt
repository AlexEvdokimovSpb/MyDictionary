package gb.myhomework.repository.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<gb.myhomework.model.DataModel>
}
