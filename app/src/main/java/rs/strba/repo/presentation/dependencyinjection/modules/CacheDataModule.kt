package rs.strba.repo.presentation.dependencyinjection.modules

import dagger.Module
import dagger.Provides
import rs.strba.repo.data.repository.datasource.RepoCacheDataSource
import rs.strba.repo.data.repository.repoIMPL.RepoCacheDataSourceIMPL
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideRepoCacheDataSource(): RepoCacheDataSource {
        return RepoCacheDataSourceIMPL()
    }
}