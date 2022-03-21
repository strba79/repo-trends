package rs.strba.repo.presentation.activities

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import rs.strba.repo.R

class RepoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val message = intent.getIntExtra(EXTRA_MESSAGE,0)
        val textView = findViewById<TextView>(R.id.tvDescriptionDetails)
        textView.text=message.toString()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}