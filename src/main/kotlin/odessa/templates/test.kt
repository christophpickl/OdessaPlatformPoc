package odessa.templates

data class TestData(
    val username: String,
    val events: List<Event>
) {
    fun toMap() = mapOf(
        "username" to username,
        "events" to events
    )
}

data class Event(
    val url: String,
    val name: String
)
