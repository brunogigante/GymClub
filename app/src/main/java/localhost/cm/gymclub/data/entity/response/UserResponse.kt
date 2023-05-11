package localhost.cm.gymclub.data.entity.response

data class UserResponse (
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String
) {
    val fullName
        get() = "$firstName $lastName"
}