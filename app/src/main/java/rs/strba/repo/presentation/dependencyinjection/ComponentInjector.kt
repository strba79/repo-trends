package rs.strba.repo.presentation.dependencyinjection

import rs.strba.repo.presentation.dependencyinjection.repomodule.RepoSubComponent

interface ComponentInjector {
    fun createRepoSubComponent(): RepoSubComponent
}