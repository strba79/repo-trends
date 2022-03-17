package rs.strba.repo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rs.strba.repo.domain.usecase.GetReposUseCase

class RepoViewModelFactory(private val getReposUseCase: GetReposUseCase):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepoViewModel(getReposUseCase) as T
    }
}