package localhost.cm.gymclub.ui.workouts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.TrainingPlanResponse
import localhost.cm.gymclub.data.entity.response.WorkoutResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class PlanWorkoutsViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    val workouts = MutableLiveData<List<WorkoutResponse>>()
    val plan = MutableLiveData<TrainingPlanResponse>()

    fun getWorkoutsAndPlanByPlanId(planId: Int) {
        viewModelScope.launch {
            plan.postValue(dataRepository.getPlan(planId))
            workouts.postValue(dataRepository.getWorkoutsForPlan(planId))
        }
    }

    fun deletePlanByPlanId(planId: Int) {
        viewModelScope.launch {
            dataRepository.deletePlan(planId)
        }
    }
}