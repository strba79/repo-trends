package rs.strba.repo.presentation.activities

import android.R
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import rs.strba.repo.MyApplication
import rs.strba.repo.data.model.Item
import rs.strba.repo.databinding.ActivityMainBinding
import rs.strba.repo.domain.repository.ProgressBarRepository
import rs.strba.repo.domain.usecase.ProgressBarStateUseCase
import rs.strba.repo.networking.GitHubApi
import rs.strba.repo.presentation.adapters.RecyclerViewAdapter
import rs.strba.repo.presentation.base.BaseActivity
import rs.strba.repo.presentation.dependencyinjection.ComponentInjector
import rs.strba.repo.presentation.viewmodel.RepoViewModel
import rs.strba.repo.presentation.viewmodel.RepoViewModelFactory
import javax.inject.Inject


class MainActivity : BaseActivity(), RecyclerViewAdapter.OnItemClickListener, ProgressBarRepository {
    private var progressBarUseCase: ProgressBarStateUseCase? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressbar: ProgressBar
    @Inject
    lateinit var factory: RepoViewModelFactory
    lateinit var model: RepoViewModel
    lateinit var gitHubApi: GitHubApi
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        progressBarUseCase=ProgressBarStateUseCase(this)
        progressbar=binding.progressBar
        setContentView(view)
        progressBarUseCase!!.showProgressBar()
        (application as MyApplication).appComponent.repoSubComponent().create()
        (application as ComponentInjector).createRepoSubComponent()
            .inject(this)
        setSupportActionBar(binding.repoToolbar)
        supportActionBar?.setIcon(R.drawable.star_on)
        model = ViewModelProvider(this, factory)[RepoViewModel::class.java]
        recyclerView = binding.rwRepos
        recyclerView.layoutManager = LinearLayoutManager(this)
        val myAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = myAdapter
        lifecycleScope.launchWhenCreated {
            model.getReposPaged(progressBarUseCase!!).collectLatest {
                myAdapter.submitData(it)
            }
        }
        binding.progressBar.visibility = View.INVISIBLE;
    }
    override fun onItemClick(position: Int, item: Item?) {
        val intent = Intent(this, RepoDetailsActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, position)
            if (item != null) {
                if (item.description != null) {
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
    override fun showProgressbar() {
        progressbar.visibility = View.VISIBLE;
    }
    override fun hideProgressBar() {
        progressbar.visibility = View.INVISIBLE;
    }
}
