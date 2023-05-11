package localhost.cm.gymclub.ui.workouts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.request.WorkoutExerciseCreationRequest
import localhost.cm.gymclub.data.entity.response.ExerciseResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class AddWorkoutFragmentViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {
    fun createWork(planId: Int, name: String, description: String) {
       viewModelScope.launch {
           dataRepository.createWorkout(planId, name, description)
       }
    }

}