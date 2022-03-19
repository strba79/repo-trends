package rs.strba.repo.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import rs.strba.repo.data.model.Item
import rs.strba.repo.data.model.Repo
import rs.strba.repo.domain.usecase.GetReposUseCase

class RepoViewModel(private val getReposUseCase: GetReposUseCase): ViewModel() {
    var repoListModel = mutableStateListOf<Item>()
    fun getRepos() = liveData {
        val repoFetchList = getReposUseCase.execute()
        repoListModel.addAll(repoFetchList)
        Log.i("checkLIst",repoListModel.size.toString())
        emit(repoFetchList)
    }
}