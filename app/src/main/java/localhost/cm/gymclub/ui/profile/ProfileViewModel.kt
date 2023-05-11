package localhost.cm.gymclub.ui.profile

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import localhost.cm.gymclub.shared.AuthSharedPref
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val authSharedPref: AuthSharedPref) : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.N)
    fun logout() {
        authSharedPref.destroy()
    }
}