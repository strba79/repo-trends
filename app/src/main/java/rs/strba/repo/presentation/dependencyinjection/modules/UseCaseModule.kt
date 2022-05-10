package rs.strba.repo.presentation.dependencyinjection.modules

import dagger.Module
import dagger.Provides
import rs.strba.repo.domain.repository.ProgressBarRepository
import rs.strba.repo.domain.repository.RepoRepository
import rs.strba.repo.domain.usecase.GetReposUseCase
import rs.strba.repo.domain.usecase.ProgressBarStateUseCase
import rs.strba.repo.presentation.activities.MainActivity
import javax.inject.Singleton


@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetRepoUseCase(repoRepository: RepoRepository): GetReposUseCase {
        return GetReposUseCase(repoRepository)
    }
}