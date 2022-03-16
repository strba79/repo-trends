package rs.strba.repo.presentation.dependencyinjection.modules

import dagger.Module
import dagger.Provides
import rs.strba.repo.data.repository.repoIMPL.RepoRemoteDataSourceIMPL
import rs.strba.repo.networking.GitHubApi
import javax.inject.Singleton
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRepoRemoteDataSource(gitHubApi: GitHubApi): RepoRemoteDataSourceIMPL {
        return RepoRemoteDataSourceIMPL(gitHubApi)
    }
}