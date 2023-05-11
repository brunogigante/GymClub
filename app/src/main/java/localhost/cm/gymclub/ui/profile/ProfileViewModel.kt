package localhost.cm.gymclub.ui.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.UserResponse
import localhost.cm.gymclub.data.repository.DataRepository
import localhost.cm.gymclub.shared.AuthSharedPref
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authSharedPref: AuthSharedPref,
private val dataRepository: DataRepository) : ViewModel() {
    val user = MutableLiveData<UserResponse>()

    @RequiresApi(Build.VERSION_CODES.N)
    fun logout() {
        authSharedPref.destroy()
    }

    fun getUser(){
        viewModelScope.launch {
            user.value = dataRepository.getAuthenticatedUser()
        }
    }
}