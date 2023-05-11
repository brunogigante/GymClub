package localhost.cm.gymclub.data.entity.response

data class WorkoutResponse(
    val id: Long,
    val name: String,
    val description: String,
    val plan: TrainingPlanResponse
)