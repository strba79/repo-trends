package rs.strba.repo.presentation.dependencyinjection.repomodule

import dagger.Module
import dagger.Provides
import rs.strba.repo.domain.usecase.GetReposUseCase
import rs.strba.repo.domain.usecase.ProgressBarStateUseCase
import rs.strba.repo.networking.GitHubApi
import rs.strba.repo.presentation.viewmodel.RepoViewModelFactory

@Module
class RepoModule {
    @RepoScope
    @Provides
    fun provideQuakeViewModelFactory(
        getReposUseCase: GetReposUseCase,
        gitHubApi: GitHubApi
    ): RepoViewModelFactory {
        return RepoViewModelFactory(getReposUseCase, gitHubApi)
    }
}