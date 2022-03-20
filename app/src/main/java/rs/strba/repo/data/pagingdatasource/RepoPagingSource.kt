package rs.strba.repo.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import rs.strba.repo.data.model.Item
import rs.strba.repo.networking.GitHubApi
import java.text.SimpleDateFormat
import java.util.*

class RepoPagingSource(private val gitHubApi: GitHubApi) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val response = gitHubApi.getRepos("created:>+${sevenDaysAgo()}", nextPage)
            val nextPageNumber: Int? = null
            LoadResult.Page(
                data = response.body()!!.items,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
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