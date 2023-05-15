package localhost.cm.gymclub.data.repository

import localhost.cm.gymclub.data.entity.request.PlanCreationRequest
import localhost.cm.gymclub.data.entity.request.TrainingPlanCloneRequest
import localhost.cm.gymclub.data.entity.request.WorkoutCreationRequest
import localhost.cm.gymclub.data.entity.request.WorkoutExerciseCreationRequest
import localhost.cm.gymclub.data.entity.request.WorkoutExerciseSetCreationRequest
import localhost.cm.gymclub.data.service.DataService

class DataRepository(private val dataService: DataService) {
    suspend fun getPlans() = dataService.getPlans()

    suspend fun getPublicPlans() = dataService.getPublicPlans()

    suspend fun getPlan(planId: Int) = dataService.getPlan(planId)

    suspend fun createPlan(name: String, isPublic: Boolean, description: String) =
        dataService.createPlan(PlanCreationRequest(name, isPublic, description))

    suspend fun deletePlan(planId: Int) = dataService.deletePlan(planId)

    suspend fun clonePlan(planId: Int) = dataService.clonePlan(TrainingPlanCloneRequest(planId))

    suspend fun getWorkoutsForPlan(planId: Int) = dataService.getWorkoutForPlan(planId)

    suspend fun getGyms() = dataService.getGyms()

    suspend fun getAuthenticatedUser() = dataService.getAuthenticatedUser()

    suspend fun createWorkout(planId: Int, name: String, description: String) = dataService.createWorkout(
        planId,
        WorkoutCreationRequest(name, description)
    )

    suspend fun createWorkoutExercise(workoutId: Int, exerciseId: Int) = dataService.createWorkoutExercises(workoutId, WorkoutExerciseCreationRequest(exerciseId))

    suspend fun getExercises() = dataService.getExercises()

    suspend fun getWorkout(workoutId: Int) = dataService.getWorkout(workoutId)

    suspend fun getWorkoutExercises(workoutId: Int) = dataService.getWorkoutExercises(workoutId)

    suspend fun getSets() = dataService.getSets()

    suspend fun getWorkoutExerciseSets(workoutId: Int, exerciseId: Int) = dataService.getWorkoutExerciseSets(workoutId, exerciseId)

    suspend fun createExerciseSet(setId: Int, reps: Int, weight: Int, workoutId: Int, exerciseId: Int) = dataService.createExerciseSet(setId, reps, weight, workoutId, exerciseId, WorkoutExerciseSetCreationRequest(workoutId, exerciseId))

}