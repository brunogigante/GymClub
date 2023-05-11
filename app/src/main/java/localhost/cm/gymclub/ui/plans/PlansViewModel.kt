package localhost.cm.gymclub.ui.plans

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.TrainingPlanResponse
import localhost.cm.gymclub.data.entity.response.UserResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class PlansViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    var plans = MutableLiveData<List<TrainingPlanResponse>>()
    var user = MutableLiveData<UserResponse>()

    fun getData() {
        viewModelScope.launch {
            plans.value = dataRepository.getPlans()
            user.value = dataRepository.getAuthenticatedUser()
        }
    }
}