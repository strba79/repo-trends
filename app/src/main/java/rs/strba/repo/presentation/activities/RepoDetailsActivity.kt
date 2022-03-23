package rs.strba.repo.presentation.activities

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import rs.strba.repo.R


class RepoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val scrollView: ScrollView = findViewById(R.id.scrollView)
        scrollView.post { scrollView.scrollTo(0, 0) }
        val description = intent.getStringExtra("DESCRIPTION")
        val textViewDescription = findViewById<TextView>(R.id.tvDescriptionDetails)
        textViewDescription.text = description

        val forks = intent.getIntExtra("FORKS", 0)
        val textViewForks = findViewById<TextView>(R.id.tvForkNumberDetails)
        textViewForks.text = forks.toString()

        val stars = intent.getIntExtra("STARS", 0)
        val textViewStars = findViewById<TextView>(R.id.tvStarsDetails)
        textViewStars.text = stars.toString()

        val avatarImageView = findViewById<ImageView>(R.id.ivAvatar)
        val avatarUrl = intent.getStringExtra("AVATAR")
        if (avatarUrl != null) {
            Glide.with(this).load(avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(avatarImageView);
        }

        val owner = intent.getStringExtra("OWNER")
        val repo = intent.getStringExtra("REPO")
        val branch = intent.getStringExtra("BRANCH")
        val readmeUrl = "https://raw.githubusercontent.com/$owner/$repo/$branch/README.md"
        val browser = findViewById<View>(R.id.wvReadme) as WebView
        if (readmeUrl != null) {
            browser.loadUrl(readmeUrl)
        };



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}