package localhost.cm.gymclub.ui.workouts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.WorkoutResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class PlanWorkoutsViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    val workouts = MutableLiveData<List<WorkoutResponse>>()

    fun getWorkoutsForPlanId(planId: Int) {
        viewModelScope.launch {
            workouts.postValue(dataRepository.getWorkoutsForPlan(planId))
        }
    }
}