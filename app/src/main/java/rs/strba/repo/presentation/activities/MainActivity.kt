package rs.strba.repo.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import rs.strba.repo.MyApplication
import rs.strba.repo.R
import rs.strba.repo.data.model.Item
import rs.strba.repo.networking.GitHubApi
import rs.strba.repo.presentation.adapters.RecyclerViewAdapter
import rs.strba.repo.presentation.dependencyinjection.ComponentInjector
import rs.strba.repo.presentation.viewmodel.RepoViewModel
import rs.strba.repo.presentation.viewmodel.RepoViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    @Inject
    lateinit var factory: RepoViewModelFactory
    lateinit var model: RepoViewModel
    lateinit var gitHubApi: GitHubApi

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).appComponent.repoSubComponent().create()
        (application as ComponentInjector).createRepoSubComponent()
            .inject(this)
        model = ViewModelProvider(this, factory)[RepoViewModel::class.java]
        recyclerView = findViewById(R.id.rwRepos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val myAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = myAdapter
        lifecycleScope.launchWhenCreated {
            model.getReposPaged().collectLatest {
                myAdapter.submitData(it)
            }
        }
    }


    override fun onItemClick(position: Int, item: Item?) {
        val intent = Intent(this, RepoDetailsActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, position)
            if (item != null) {
                if (item.description!=null) {
                    val description = item.description.toString()
                    putExtra("DESCRIPTION", description)
                }
                putExtra("FORKS", item.forksCount)
                putExtra("STARS", item.stargazersCount)
                putExtra("AVATAR", item.owner.avatarUrl)
                putExtra("OWNER", item.owner.login)
                putExtra("REPO", item.name)
                putExtra("BRANCH", item.defaultBranch)
            }
        }
        startActivity(intent)
    }
}
