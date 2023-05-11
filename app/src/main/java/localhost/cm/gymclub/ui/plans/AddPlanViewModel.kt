package localhost.cm.gymclub.ui.plans

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class AddPlanViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
    fun createPlan(name: String, isPublic: Boolean) {
        viewModelScope.launch {
            dataRepository.createPlan(name, isPublic)
        }
    }
}