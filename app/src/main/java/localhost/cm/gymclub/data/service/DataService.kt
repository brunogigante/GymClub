package localhost.cm.gymclub.data.service

import localhost.cm.gymclub.data.entity.request.PlanCreationRequest
import localhost.cm.gymclub.data.entity.request.TrainingPlanCloneRequest
import localhost.cm.gymclub.data.entity.request.WorkoutCreationRequest
import localhost.cm.gymclub.data.entity.request.WorkoutExerciseCreationRequest
import localhost.cm.gymclub.data.entity.request.WorkoutExerciseSetCreationRequest
import localhost.cm.gymclub.data.entity.response.ExerciseResponse
import localhost.cm.gymclub.data.entity.response.GymResponse
import localhost.cm.gymclub.data.entity.response.SetResponse
import localhost.cm.gymclub.data.entity.response.TrainingPlanResponse
import localhost.cm.gymclub.data.entity.response.UserResponse
import localhost.cm.gymclub.data.entity.response.WorkoutResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DataService {
    @GET("/plans/me")
    suspend fun getPlans(): List<TrainingPlanResponse>

    @GET("/plans/public")
    suspend fun getPublicPlans(): List<TrainingPlanResponse>

    @GET("/plans/{planId}")
    suspend fun getPlan(@Path("planId") planId: Int): TrainingPlanResponse

    @POST("/plans")
    suspend fun createPlan(@Body() planCreationRequest: PlanCreationRequest)

    @DELETE("/plans/{planId}")
    suspend fun deletePlan(@Path("planId") planId: Int)

    @POST("/plans/clone")
    suspend fun clonePlan(@Body trainingPlanClone: TrainingPlanCloneRequest)

    @GET("/plans/{planId}/workouts")
    suspend fun getWorkoutForPlan(@Path("planId") planId: Int): List<WorkoutResponse>

    @GET("/gyms")
    suspend fun getGyms(): List<GymResponse>

    @GET("/users/authenticated")
    suspend fun getAuthenticatedUser(): UserResponse

    // workouts
    @POST("/plans/{planId}/workouts")
    suspend fun createWorkout(@Path("planId") planId: Int, @Body workoutCreationRequest: WorkoutCreationRequest): Int

    @POST("/plans/workouts/{workoutId}/exercises")
    suspend fun createWorkoutExercises(@Path("workoutId") workoutId: Int, @Body workoutCreationRequest: WorkoutExerciseCreationRequest)

    @GET("/exercises")
    suspend fun getExercises(): List<ExerciseResponse>

    @GET("/exercises/categories")
    suspend fun getExerciseCategories(): List<String>

    @GET("/plans/workouts/{workoutId}/exercises")
    suspend fun getWorkoutExercises(@Path("workoutId") workoutId: Int): List<ExerciseResponse>

    @GET("plans/workouts/{workoutId}")
    suspend fun getWorkout(@Path("workoutId") workoutId: Int): WorkoutResponse

    //Sets
    @GET("/plans/workouts/{workoutId}/exercises/{exerciseId}/sets")
    suspend fun getWorkoutExerciseSets(@Path("workoutId") workoutId:Int, @Path("exerciseId") exerciseId: Int): List<SetResponse>

    @GET("/sets")
    suspend fun getSets(): List<SetResponse>

    @POST("/plans/workouts/{workoutId}/exercises/{exerciseId}/sets")
    suspend fun createExerciseSet(@Path("workoutId") workoutId: Int, @Path("exerciseId") exerciseId: Int, @Body workoutExerciseSetCreationRequest: WorkoutExerciseSetCreationRequest)

}
