package localhost.cm.gymclub.data.repository

import localhost.cm.gymclub.data.service.DataService

class DataRepository(private val dataService: DataService) {
    suspend fun getPlans() = dataService.getPlans()

    suspend fun getPublicPlans() = dataService.getPublicPlans()

    suspend fun getPlan(planId: Int) = dataService.getPlan(planId)

    suspend fun deletePlan(planId: Int) = dataService.deletePlan(planId)

    suspend fun getWorkoutsForPlan(planId: Int) = dataService.getWorkoutForPlan(planId)

    suspend fun getGyms() = dataService.getGyms()
}