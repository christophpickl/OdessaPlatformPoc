package odessa

enum class UserRole {
    Spaceholder,
    Spacepirate,
    Admin
}

data class User(
    val name: String,
    val email: String,
    val tokens: Int,
    val role: UserRole
)

object Repository {
    private val users = listOf(
        User(
            name = "Yana",
            email = "yana@odessa.com",
            tokens = 50,
            role = UserRole.Spaceholder
        ),
        User(
            name = "Christoph",
            email = "christoph@odessa.com",
            tokens = 200,
            role = UserRole.Spacepirate
        ),
        User(
            name = "Amber",
            email = "amber@odessa.com",
            tokens = 920,
            role = UserRole.Admin
        )
    )
    
    fun findUserByShortId(shortId: String): User? =
        when (shortId) {
            "a" -> users[0]
            "b" -> users[1]
            "c" -> users[2]
            else -> null
        }
    
    fun getUserByEmail(email: String): User =
        users.firstOrNull { it.email == email } ?: throw Exception("User not found by email '$email'!")
}
