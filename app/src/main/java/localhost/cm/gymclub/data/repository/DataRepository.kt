package localhost.cm.gymclub.data.repository

import localhost.cm.gymclub.data.service.DataService

class DataRepository(private val dataService: DataService) {
    suspend fun getPlans() = dataService.getPlans()

    suspend fun getWorkoutsForPlan(planId: Int) = dataService.getWorkoutForPlan(planId)

    suspend fun getGyms() = dataService.getGyms()
}