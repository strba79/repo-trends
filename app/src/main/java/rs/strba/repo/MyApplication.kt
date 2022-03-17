package rs.strba.repo

import android.app.Application
import rs.strba.repo.presentation.dependencyinjection.ComponentInjector
import rs.strba.repo.presentation.dependencyinjection.modules.*
import rs.strba.repo.presentation.dependencyinjection.repomodule.RepoSubComponent

class MyApplication: Application(),ComponentInjector {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule())
            .remoteDataModule(RemoteDataModule())
            .build()
    }

    override fun createRepoSubComponent(): RepoSubComponent {
        return appComponent.repoSubComponent().create()
    }
}