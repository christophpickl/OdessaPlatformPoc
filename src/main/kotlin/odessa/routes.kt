package odessa

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.files
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import odessa.templates.Event
import odessa.templates.TestData

fun Routing.installRoutes(clearFreemarkerCache: () -> Unit) {
    static("/static") {
//        resources("static")
        files("src/main/resources/static")
    }
    get("/") {
        call.respondText(
            """
            <html>
            <body>
            <h1>Available pages:</h1>
            <ul>
            <li><a href="/test">test</a></li>
            </ul>
            </body>
            </html>
            """.trimIndent(), ContentType.Text.Html, HttpStatusCode.OK
        )
    }
    get("/test") {
        val data = TestData(
            username = "Yana",
            events = listOf(
                Event(
                    name = "Ecstatic Boat 1",
                    url = "http://odessa.com/1"
                )
            )
        )
        call.respond(FreeMarkerContent("test.ftlh", data.toMap()))
        clearFreemarkerCache()
    }
}
