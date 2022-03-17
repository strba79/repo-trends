package rs.strba.repo.data.repository.repoIMPL

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rs.strba.repo.data.model.Item
import rs.strba.repo.data.repository.datasource.RepoRemoteDataSource
import rs.strba.repo.networking.GitHubApi
import java.util.*

class RepoRemoteDataSourceIMPL(private val gitHubApi: GitHubApi):RepoRemoteDataSource {

    override suspend fun getRepos(): List<Item> {
        return withContext(Dispatchers.IO) {
            try {
                val response = gitHubApi.getRepos(sevenDaysAgo())
                if (response.body() != null) {
                    return@withContext response.body()!!.items
                } else {
                    Log.i(TAG, "fetch else")
                    return@withContext response.body()!!.items
                }
            } catch (t: Throwable) {
                throw t
            }
        }
    }
    private fun sevenDaysAgo(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        return calendar.time
    }
}