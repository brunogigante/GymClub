package localhost.cm.gymclub.ui.sets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.ExerciseResponse
import localhost.cm.gymclub.data.entity.response.SetResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class SetsViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    val workoutExercisesSets = MutableLiveData<List<SetResponse>>()
    val workoutExercises = MutableLiveData<List<ExerciseResponse>>()

    fun getSetsFromExercises(workoutId: Int, exerciseId: Int) {
        viewModelScope.launch {
            workoutExercises.value = dataRepository.getWorkoutExercises(workoutId)
            workoutExercisesSets.value = dataRepository.getWorkoutExerciseSets(workoutId, exerciseId)
        }
    }
}