package rs.strba.repo.presentation

import android.app.Activity
import rs.strba.repo.presentation.activities.RepoDetailsActivity

class ScreensNavigator(private val activity: Activity) {

    fun navigateBack() {
        activity.onBackPressed()
    }

    fun toQuestionDetails(repoId: String) {

    }
}