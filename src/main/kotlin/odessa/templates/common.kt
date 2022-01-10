package odessa.templates

data class User(
    val name: String,
    val tokens: Int
)

data class Event(
    val title: String,
    val url: String?
)
