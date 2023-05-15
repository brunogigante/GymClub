package localhost.cm.gymclub.ui.sets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.SetResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class AddSetsViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {
    val exercises = MutableLiveData<List<SetResponse>>()

    fun addNewSetToExercise(reps: Int, weight: Int, workoutId: Int, exerciseId: Int) {
        viewModelScope.launch {
            dataRepository.createExerciseSet(reps, weight, workoutId, exerciseId)
        }
    }
}