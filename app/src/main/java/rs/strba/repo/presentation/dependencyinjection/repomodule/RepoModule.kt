package rs.strba.repo.presentation.dependencyinjection.repomodule

import dagger.Provides
import rs.strba.repo.domain.usecase.GetReposUseCase
import rs.strba.repo.presentation.viewmodel.RepoViewModelFactory

class RepoModule {
    @RepoScope
    @Provides
    fun provideQuakeViewModelFactory(
        getReposUseCase: GetReposUseCase
    ): RepoViewModelFactory {
        return RepoViewModelFactory(getReposUseCase)
    }
}