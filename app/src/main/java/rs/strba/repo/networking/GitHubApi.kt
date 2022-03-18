package rs.strba.repo.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rs.strba.repo.data.model.Repo

interface GitHubApi {
    @GET("/search/repositories")
    suspend fun getRepos(
      @Query("q") q: String,
      @Query("page") page: Int,
      @Query("per_page") pageSize: Int
        ): Response<Repo>
}