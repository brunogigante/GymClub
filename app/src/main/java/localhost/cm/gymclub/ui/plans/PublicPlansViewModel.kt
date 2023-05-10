package localhost.cm.gymclub.ui.plans

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.TrainingPlanResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class PublicPlansViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    var publicPlans = MutableLiveData<List<TrainingPlanResponse>>()

    init {
        viewModelScope.launch {
            publicPlans.value = dataRepository.getPublicPlans()
        }
    }

    fun clonePlan(planId: Int) {
        viewModelScope.launch {
            dataRepository.clonePlan(planId)
        }
    }
}