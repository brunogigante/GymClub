package localhost.cm.gymclub.data.service

import localhost.cm.gymclub.data.entity.response.GymResponse
import localhost.cm.gymclub.data.entity.response.TrainingPlanResponse
import localhost.cm.gymclub.data.entity.response.WorkoutResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataService {
    @GET("/plans")
    suspend fun getPlans(): List<TrainingPlanResponse>

    @GET("/plans/{planId}/workouts")
    suspend fun getWorkoutForPlan(@Path("planId") planId: Int): List<WorkoutResponse>

    @GET("/gyms")
    suspend fun getGyms(): List<GymResponse>
}