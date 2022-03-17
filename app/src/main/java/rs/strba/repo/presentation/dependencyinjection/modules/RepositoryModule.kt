package rs.strba.repo.presentation.dependencyinjection.modules

import dagger.Module
import dagger.Provides
import rs.strba.repo.data.repository.RepoRepositoryIMPL
import rs.strba.repo.data.repository.datasource.RepoCacheDataSource
import rs.strba.repo.data.repository.datasource.RepoRemoteDataSource
import rs.strba.repo.domain.repository.RepoRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepoRepository(
        repoRemoteDataSource: RepoRemoteDataSource,
        repoCacheDataSource: RepoCacheDataSource
    ): RepoRepository {
        return RepoRepositoryIMPL(
            repoRemoteDataSource,
            repoCacheDataSource
        )
    }
}