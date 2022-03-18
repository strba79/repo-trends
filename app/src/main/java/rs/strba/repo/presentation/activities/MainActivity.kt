package rs.strba.repo.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rs.strba.repo.MyApplication
import rs.strba.repo.R
import rs.strba.repo.presentation.adapters.RepoAdapter
import rs.strba.repo.presentation.dependencyinjection.ComponentInjector
import rs.strba.repo.presentation.viewmodel.RepoViewModelFactory
import javax.inject.Inject
import rs.strba.repo.presentation.viewmodel.RepoViewModel as RepoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var factory: RepoViewModelFactory
    lateinit var model: RepoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).appComponent.repoSubComponent().create()
        (application as ComponentInjector).createRepoSubComponent()
            .inject(this)
        model = ViewModelProvider(this, factory)[RepoViewModel::class.java]
        liveDataObserver()
        recyclerView = findViewById(R.id.rwRepos)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun liveDataObserver() {
        val responseLiveDataGet = model.getRepos()
        //Log.i("checkLiveData", responseLiveDataGet.value?.size.toString())
        responseLiveDataGet.observe(this) {
            if (it != null) {
                Log.i("checkLiveData!", it.size.toString())
                recyclerView.adapter = RepoAdapter(it)
            }
        }
    }
}
