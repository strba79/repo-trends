package rs.strba.repo.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import rs.strba.repo.R
import rs.strba.repo.databinding.ActivityDetailBinding


class RepoDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.repoDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.scrollView.post { binding.scrollView.scrollTo(0, 0) }
        val description = intent.getStringExtra("DESCRIPTION")
        binding.tvDescriptionDetails.text = description

        val forks = intent.getIntExtra("FORKS", 0)
        binding.tvForkNumberDetails.text = forks.toString()

        val stars = intent.getIntExtra("STARS", 0)
        binding.tvStarsDetails.text = stars.toString()

        val avatarUrl = intent.getStringExtra("AVATAR")
        if (avatarUrl != null) {
            Glide.with(this).load(avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivAvatar)
        }
        val owner = intent.getStringExtra("OWNER")
        val repo = intent.getStringExtra("REPO")
        val branch = intent.getStringExtra("BRANCH")
        val readmeUrl = "https://raw.githubusercontent.com/$owner/$repo/$branch/README.md"
        binding.wvReadme.loadUrl(readmeUrl)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}