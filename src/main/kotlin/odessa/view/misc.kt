package odessa.view

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import odessa.AppConfig

fun Application.configureKtor(appConfig: AppConfig) {
    install(FreeMarker) {
        appConfig.configureFreemarker(this)
    }
    install(Sessions) {
        cookie<UserSession>(KtorConstants.sessionID)
    }
    routing {
        installRoutes(appConfig)
    }
}


data class UserSession(
    val email: String,
)
