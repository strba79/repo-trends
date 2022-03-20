package rs.strba.repo.data.repository.repoIMPL

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.snapshots.Snapshot.Companion.current
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import rs.strba.repo.data.model.Item
import rs.strba.repo.data.repository.datasource.RepoRemoteDataSource
import rs.strba.repo.networking.GitHubApi
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class RepoRemoteDataSourceIMPL(private val gitHubApi: GitHubApi) : RepoRemoteDataSource {


    sealed class Result {
        data class Success(val quakes: List<Item>) : Result()
        object Failure : Result()
    }

    override suspend fun getReposTest(): Result {
        return withContext(Dispatchers.IO) {
            try {
                Log.i("checkData","ok")
                val response = gitHubApi.getRepos("created:>+${sevenDaysAgo()}",1)
                Log.i("response", response.body()?.items?.size.toString())
                if (response.isSuccessful && response.body() != null) {
                    Log.i(TAG, response.body()!!.items.size.toString())
                    return@withContext Result.Success(response.body()!!.items)
                } else {
                    Log.i("failure", "fetch else")
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }

    override suspend fun getRepos(): List<Item> {
        return withContext(Dispatchers.IO) {
            try {
                val response = gitHubApi.getRepos("created:>+${sevenDaysAgo()}",1)
                if (response.body() != null) {
                    return@withContext response.body()!!.items
                } else {
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