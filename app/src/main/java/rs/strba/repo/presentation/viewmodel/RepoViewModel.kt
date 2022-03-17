package rs.strba.repo.presentation.viewmodel

import androidx.lifecycle.liveData
import rs.strba.repo.data.model.Repo
import rs.strba.repo.domain.usecase.GetReposUseCase

class RepoViewModel(private val getReposUseCase: GetReposUseCase) {

    fun getRepos() = liveData {
        val repoFetchList = getReposUseCase.execute()
 /*       if (repoFetchList == null) {
            repoListModel.addAll(repoFetchList)
        }*/
        emit(repoFetchList)
    }
}