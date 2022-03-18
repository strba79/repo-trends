package rs.strba.repo.data.repository.repoIMPL

import android.util.Log
import androidx.compose.runtime.snapshots.Snapshot.Companion.current
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rs.strba.repo.data.model.Item
import rs.strba.repo.data.repository.datasource.RepoRemoteDataSource
import rs.strba.repo.networking.GitHubApi
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class RepoRemoteDataSourceIMPL(private val gitHubApi: GitHubApi) : RepoRemoteDataSource {

    override suspend fun getRepos(): List<Item> {
        return withContext(Dispatchers.IO) {
            try {
                Log.i("checkDate", sevenDaysAgo())
                val response = gitHubApi.getRepos("created:>+${sevenDaysAgo()}")
                Log.i("checkFetch", "after")
                if (response.body() != null) {
                    Log.i("checkFetch", response.body()!!.items.size.toString())
                    return@withContext response.body()!!.items
                } else {
                    Log.i("checkFetch", response.body()!!.items.size.toString())
                    return@withContext response.body()!!.items
                }
            } catch (t: Throwable) {
                throw t
            }
        }
    }

    private fun sevenDaysAgo(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.format(calendar.time)
        return date.toString()
    }
}