package localhost.cm.gymclub.ui.exercises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.ExerciseResponse
import localhost.cm.gymclub.data.entity.response.WorkoutResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    val workoutExercises = MutableLiveData<List<ExerciseResponse>>()
    val workout = MutableLiveData<WorkoutResponse>()

    fun getWorkoutExercisesByWorkoutId(workoutId: Int) {
        viewModelScope.launch {
            workout.value = dataRepository.getWorkout(workoutId)
            workoutExercises.value = dataRepository.getWorkoutExercises(workoutId)
        }
    }
}