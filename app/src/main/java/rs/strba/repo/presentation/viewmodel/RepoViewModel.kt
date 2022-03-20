package rs.strba.repo.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import rs.strba.repo.data.model.Item
import rs.strba.repo.data.pagingdatasource.RepoPagingSource
import rs.strba.repo.domain.usecase.GetReposUseCase
import rs.strba.repo.networking.GitHubApi

class RepoViewModel(
    private val getReposUseCase: GetReposUseCase,
    private val gitHubApi: GitHubApi
) : ViewModel() {
    var repoListModel = mutableStateListOf<Item>()

    fun getRepos() = liveData {
        val repoFetchList = getReposUseCase.execute()
        repoListModel.addAll(repoFetchList)
        Log.i("checkLIst", repoListModel.size.toString())
        emit(repoFetchList)
    }


    fun getReposPaged(): Flow<PagingData<Item>> {
        return Pager(config = PagingConfig(pageSize = 30, maxSize = 200),
            pagingSourceFactory = { RepoPagingSource(gitHubApi) }).flow.cachedIn(viewModelScope)
    }
}