package rs.strba.repo.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.strba.repo.MyApplication
import rs.strba.repo.R
import rs.strba.repo.presentation.dependencyinjection.ComponentInjector
import rs.strba.repo.presentation.viewmodel.RepoViewModel
import rs.strba.repo.presentation.viewmodel.RepoViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: RepoViewModelFactory
    lateinit var model: RepoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).appComponent.repoSubComponent().create()
        (application as ComponentInjector).createRepoSubComponent()
            .inject(this)
        model.getRepos()
    }
}