package rs.strba.repo.presentation.dependencyinjection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import rs.strba.repo.presentation.dependencyinjection.repomodule.RepoSubComponent
import javax.inject.Singleton

@Module(subcomponents = [RepoSubComponent::class])
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}