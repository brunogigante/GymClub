package localhost.cm.gymclub.data.entity.response

data class ExerciseResponse(
    val name: String,
    val description: String,
    val category: String,
    val id: Int
) {
    override fun toString(): String {
        return "$name - $description"
    }
}