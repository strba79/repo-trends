package rs.strba.repo.presentation.dependencyinjection.modules

import dagger.Module
import dagger.Provides
import rs.strba.repo.data.repository.RepoRepositoryIMPL
import rs.strba.repo.data.repository.datasource.RepoCacheDataSource
import rs.strba.repo.data.repository.datasource.RepoRemoteDataSource
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideEarthQuakeRepository(
        repoRemoteDataSource: RepoRemoteDataSource,
        repoCacheDataSource: RepoCacheDataSource
    ): RepoRepositoryIMPL {
        return RepoRepositoryIMPL(
            repoRemoteDataSource,
            repoCacheDataSource
        )
    }
}