package odessa

import io.ktor.application.call
import io.ktor.http.content.static
import io.ktor.routing.Routing
import io.ktor.routing.get
import odessa.templates.Event
import odessa.templates.TestData
import odessa.templates.User
import java.time.LocalDateTime

val userYana = User(
    name = "Yana",
    tokens = 21
)

val eventDance = Event(
    title = "Ecstatic Dance",
    startDate = LocalDateTime.parse("2020-01-13T18:30:00"),
    url = null,
    tokensCost = 5,
    tokensGain = 2
)

val eventTantra = Event(
    title = "Tantric Massage",
    url = "http://odessa.com/tantric",
    startDate = LocalDateTime.parse("2020-01-25T14:00:00"),
    tokensCost = 30,
    tokensGain = null
)

fun Routing.installRoutes(appConfig: AppConfig) {
    static("/static") {
        appConfig.configureStaticFolder(this)
    }
    
    get("/") {
        call.respondFreemarker(TemplateFile.Home, null)
        appConfig.clearFreemarkerCache()
    }
    
    get("/test") {
        val data = TestData(
            user = userYana,
            events = listOf(eventDance, eventTantra)
        )
        call.respondFreemarker(TemplateFile.Test, data)
        appConfig.clearFreemarkerCache()
    }
}
