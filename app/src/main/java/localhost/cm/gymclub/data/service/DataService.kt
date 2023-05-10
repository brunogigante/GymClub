package localhost.cm.gymclub.data.service

import localhost.cm.gymclub.data.entity.request.TrainingPlanClone
import localhost.cm.gymclub.data.entity.response.GymResponse
import localhost.cm.gymclub.data.entity.response.TrainingPlanResponse
import localhost.cm.gymclub.data.entity.response.WorkoutResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DataService {
    @GET("/plans/me")
    suspend fun getPlans(): List<TrainingPlanResponse>

    @GET("/plans/public")
    suspend fun getPublicPlans(): List<TrainingPlanResponse>

    @GET("/plans/{planId}")
    suspend fun getPlan(@Path("planId") planId: Int): TrainingPlanResponse

    @DELETE("/plans/{planId}")
    suspend fun deletePlan(@Path("planId") planId: Int)


    @POST("/plans/clone")
    suspend fun clonePlan(@Body trainingPlanClone: TrainingPlanClone)

    @GET("/plans/{planId}/workouts")
    suspend fun getWorkoutForPlan(@Path("planId") planId: Int): List<WorkoutResponse>

    @GET("/gyms")
    suspend fun getGyms(): List<GymResponse>
}