package rs.strba.repo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import rs.strba.repo.data.model.Repo
import rs.strba.repo.domain.usecase.GetReposUseCase

class RepoViewModel(private val getReposUseCase: GetReposUseCase): ViewModel() {

    fun getRepos() = liveData {
        val repoFetchList = getReposUseCase.execute()
 /*       if (repoFetchList == null) {
            repoListModel.addAll(repoFetchList)
        }*/
        emit(repoFetchList)
    }
}