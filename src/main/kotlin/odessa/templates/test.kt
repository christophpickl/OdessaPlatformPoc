package odessa.templates

import odessa.TemplateEngine

object TestTemplate {
    fun merge(engine: TemplateEngine, data: TestData): String {
        return engine.merge("test.ftlh", data.toMap())
    }
}

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
