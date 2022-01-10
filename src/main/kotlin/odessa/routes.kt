package odessa

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.content.static
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import odessa.templates.TestData
import odessa.templates.Event
import odessa.templates.User

fun Routing.installRoutes(appConfig: AppConfig) {
    static("/static") {
        appConfig.configureStaticFolder(this)
    }
    get("/") {
        call.respond(FreeMarkerContent("home.ftlh", null))
        appConfig.clearFreemarkerCache()
    }
    get("/test") {
        val data = TestData(
            user = User(name = "Yana"),
            events = listOf(
                Event(
                    name = "Ecstatic Boat 1",
                    url = "http://odessa.com/1"
                ),
                Event(
                    name = "Ecstatic Boat 2",
                    url = "http://odessa.com/2"
                )
            )
        )
        call.respond(FreeMarkerContent("test.ftlh", data))
        appConfig.clearFreemarkerCache()
    }
}
