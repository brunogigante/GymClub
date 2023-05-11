package localhost.cm.gymclub.ui.exercises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.ExerciseResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {
    val exercises = MutableLiveData<List<ExerciseResponse>>()

    fun addNewWorkoutExercise(workoutId: Int, exerciseId: Int) {
        viewModelScope.launch {
            dataRepository.createWorkoutExercise(workoutId, exerciseId)
        }
    }

    fun getExercises() {
       viewModelScope.launch {
           exercises.value = dataRepository.getExercises()
       }
    }

}