package rs.strba.repo.presentation.activities

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import rs.strba.repo.R
import rs.strba.repo.databinding.ActivityDetailBinding

class RepoDetailsActivity : AppCompatActivity() {
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var binding: ActivityDetailBinding
    private lateinit var progressbar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.repoDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressbar = binding.progressBarDetails
        binding.scrollView.post { binding.scrollView.scrollTo(0, 0) }
        progressbar.visibility = View.VISIBLE
        Handler().postDelayed({
            val description = intent.getStringExtra("DESCRIPTION")
            binding.tvDescriptionDetails.text = description
            val forks = intent.getIntExtra("FORKS", 0)
            binding.tvForkNumberDetails.text = forks.toString()
            val stars = intent.getIntExtra("STARS", 0)
            binding.tvStarsDetails.text = stars.toString()
            val avatarUrl = intent.getStringExtra("AVATAR")
            if (avatarUrl != null) {
                Glide.with(this).load(avatarUrl)
                    .placeholder(R.drawable.ic_baseline_baby_changing_station)
                    .error(R.drawable.ic_baseline_baby_changing_station)
                    .into(binding.ivAvatar)
            }
            val owner = intent.getStringExtra("OWNER")
            val repo = intent.getStringExtra("REPO")
            val branch = intent.getStringExtra("BRANCH")
            val readmeUrl = "https://raw.githubusercontent.com/$owner/$repo/$branch/README.md"
            binding.wvReadme.loadUrl(readmeUrl)
        }, 2000)
        progressbar.visibility = View.INVISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}