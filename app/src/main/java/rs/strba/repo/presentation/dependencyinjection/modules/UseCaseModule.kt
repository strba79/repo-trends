package rs.strba.repo.presentation.dependencyinjection.modules

import dagger.Provides
import rs.strba.repo.domain.repository.RepoRepository
import rs.strba.repo.domain.usecase.GetReposUseCase
import javax.inject.Singleton

class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetRepoUseCase(repoRepository: RepoRepository): GetReposUseCase {
        return GetReposUseCase(repoRepository)
    }
}