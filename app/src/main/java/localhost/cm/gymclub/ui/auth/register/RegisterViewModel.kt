package localhost.cm.gymclub.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    fun register(firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(firstName, lastName, email, password)
        }
    }
}