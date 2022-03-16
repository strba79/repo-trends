package rs.strba.repo.presentation.dependencyinjection.modules

import dagger.Component
import rs.strba.repo.presentation.dependencyinjection.repomodule.RepoSubComponent
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,CacheDataModule::class,NetModule::class,RemoteDataModule::class,RepositoryModule::class,UseCaseModule::class])
interface AppComponent {
    fun repoSubComponent(): RepoSubComponent.Factory
}