package rs.strba.repo.data.pagingdatasource


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import rs.strba.repo.data.model.Item
import rs.strba.repo.domain.repository.ProgressBarRepository
import rs.strba.repo.domain.usecase.ProgressBarStateUseCase
import rs.strba.repo.networking.GitHubApi
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RepoPagingSource(
    private val gitHubApi: GitHubApi,
    private val progressBarUseCase: ProgressBarStateUseCase
) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            progressBarUseCase.showProgressBar()
            //delay(1000)
            val position = params.key ?: 0
            val response = gitHubApi.getRepos("created:>+${sevenDaysAgo()}", position)
            progressBarUseCase.hideProgressBar()
            LoadResult.Page(
                data = response.body()!!.items,
                prevKey = if (position == 0) null else position - 1,
                nextKey = position + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } finally {

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