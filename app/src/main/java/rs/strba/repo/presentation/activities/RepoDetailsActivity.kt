package rs.strba.repo.presentation.activities

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import rs.strba.repo.R
import java.net.URL


class RepoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val message = intent.getIntExtra(EXTRA_MESSAGE, 0)
        val textView = findViewById<TextView>(R.id.tvDescriptionDetails)
        textView.text = message.toString()

        val forks = intent.getIntExtra("FORKS", 0)
        val textViewForks = findViewById<TextView>(R.id.tvForkNumberDetails)
        textViewForks.text = forks.toString()

        val stars = intent.getIntExtra("STARS", 0)
        val textViewStars = findViewById<TextView>(R.id.tvStarsDetails)
        textViewStars.text = stars.toString()

        val avatarView = findViewById<ImageView>(R.id.ivAvatar)
        val avatarUrl=intent.getStringExtra("AVATAR")
        if (avatarUrl != null) {
            Glide.with(this).load(avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(avatarView);
        }

/*        val owner =intent.getStringExtra("OVNER")
        val repo =
        val branch =
        val readmeUrl = "https://raw.githubusercontent.com/{$owner}/{$repo}/{$branch}/README.md"
        val browser = findViewById<View>(R.id.wvReadme) as WebView
        if (urlTest != null) {
            browser.loadUrl(urlTest)
        };*/

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}