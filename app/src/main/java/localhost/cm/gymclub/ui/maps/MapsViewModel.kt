package localhost.cm.gymclub.ui.maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import localhost.cm.gymclub.data.entity.response.GymResponse
import localhost.cm.gymclub.data.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {
    val gyms = MutableLiveData<List<GymResponse>>()

    init {
        viewModelScope.launch {
            gyms.postValue(dataRepository.getGyms())
        }
    }
}