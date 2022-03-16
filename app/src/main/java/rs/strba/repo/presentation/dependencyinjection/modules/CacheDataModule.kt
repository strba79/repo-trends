package rs.strba.repo.presentation.dependencyinjection.modules

import dagger.Module
import dagger.Provides
import rs.strba.repo.data.repository.repoIMPL.RepoRemoteCacheSourceIMPL
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideRepoCacheDataSource(): RepoRemoteCacheSourceIMPL {
        return RepoRemoteCacheSourceIMPL()
    }
}