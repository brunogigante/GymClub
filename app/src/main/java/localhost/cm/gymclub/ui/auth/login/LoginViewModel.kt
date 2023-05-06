package localhost.cm.gymclub.ui.auth.login

import android.app.Application
import android.content.Context
import android.util.EventLog
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import localhost.cm.gymclub.HiltApplication
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.UserAuthResponse
import localhost.cm.gymclub.data.exception.WrongCredentialsException
import localhost.cm.gymclub.data.repository.AuthRepository
import localhost.cm.gymclub.data.service.AuthService
import localhost.cm.gymclub.shared.AuthSharedPref
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val authSharedPref: AuthSharedPref,
    private val authRepository: AuthRepository,
) : /*TODO: change 'as' casting */ AndroidViewModel(context.applicationContext as Application) {

    val shouldClose = MutableLiveData<Boolean>()
    val toasts = MutableLiveData<String>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val userAuth = authRepository.authenticate(email, password)
                shouldClose.postValue(true)
                authSharedPref.jwt = userAuth.accessToken
            } catch (err: WrongCredentialsException) {
                toasts.postValue(getApplication<Application>().getString(R.string.wrong_credentials_exception))
            }
        }
    }
}