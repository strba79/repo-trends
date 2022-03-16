package rs.strba.repo.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rs.strba.repo.data.model.Repo
import java.util.*

interface GitHubApi {
    @GET("?q=created:>lastSevenDays")
    suspend fun getRepos(
        @Query("lastSevenDays") lastSevenDays: Date?,
    ): Response<Repo>
}