package localhost.cm.gymclub.shared

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


class AuthSharedPref(private val context: Context)  {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(USER_SHARED_PREF, Context.MODE_PRIVATE)

    companion object Keys {
        const val USER_SHARED_PREF = "localhost.cm.gymclub.USER_JWT_PREFERENCE"
        const val USER_JWT = "localhost.cm.gymclub.USER_JWT_PREFERENCE"
    }

    var jwt
        get() = sharedPref.getString(USER_JWT, null)
        set(value) {
            sharedPref.edit().apply {
                if (value != null){
                    putString(USER_JWT, value)
                }
                apply()
            }
        }
    @RequiresApi(Build.VERSION_CODES.N)
    fun destroy() {
        context.deleteSharedPreferences(USER_SHARED_PREF)
    }
}