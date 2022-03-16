package rs.strba.repo.presentation.dependencyinjection.repomodule

import dagger.Subcomponent
import rs.strba.repo.presentation.activities.MainActivity

@RepoScope
@Subcomponent(modules = [RepoModule::class])
interface RepoSubComponent {
    fun inject(mainActivity: MainActivity)
    @Subcomponent.Factory
    interface Factory{
        fun create():RepoSubComponent
    }
}