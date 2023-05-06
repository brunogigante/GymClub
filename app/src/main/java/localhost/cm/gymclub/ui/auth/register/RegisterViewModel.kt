package localhost.cm.gymclub.ui.auth.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import localhost.cm.gymclub.data.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(authRepository: AuthRepository): ViewModel() {
}