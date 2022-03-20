package rs.strba.repo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rs.strba.repo.domain.usecase.GetReposUseCase
import rs.strba.repo.networking.GitHubApi

class RepoViewModelFactory(private val getReposUseCase: GetReposUseCase,private val gitHubApi: GitHubApi):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepoViewModel(getReposUseCase,gitHubApi) as T
    }
}