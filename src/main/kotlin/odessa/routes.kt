package odessa

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.sessions
import odessa.templates.EventTD
import odessa.templates.HomeTDR
import odessa.templates.TestTDR
import odessa.templates.UserTD
import odessa.templates.some
import odessa.templates.yana

object KtorConstants {
    const val sessionID = "user_session2"
}

fun Routing.installRoutes(appConfig: AppConfig) {
    static("/static") {
        appConfig.configureStaticFolder(this)
    }
    
    get("/") {
        val session = call.sessions.get(KtorConstants.sessionID) as UserSession?
        if (session == null) {
            call.respondFreemarker(TemplateFile.Login, null)
        } else {
            val user = Repository.getUserByEmail(session.email)
            call.respondHomeFor(user)
        }
        appConfig.clearFreemarkerCache()
    }
    
    get("/logout") {
        call.sessions.clear(KtorConstants.sessionID)
        call.respondFreemarker(TemplateFile.Login, null)
        appConfig.clearFreemarkerCache()
    }
    
    post("/") { // login
        val params = call.receiveParameters()
        val email = params["email"] ?: ""
        val user = Repository.findUserByShortId(email)
        if (user == null) {
            call.respondFreemarker(TemplateFile.Login, null)
        } else {
            call.sessions.set(KtorConstants.sessionID, UserSession(user.email))
            call.respondHomeFor(user)
        }
        appConfig.clearFreemarkerCache()
    }
    
    get("/test") {
        val data = TestTDR(
            user = UserTD.yana,
            events = EventTD.some
        )
        call.respondFreemarker(TemplateFile.Test, data)
        appConfig.clearFreemarkerCache()
    }
}

private suspend fun ApplicationCall.respondHomeFor(user: User) {
    val templateFile = when (user.role) {
        UserRole.Spaceholder -> TemplateFile.HomeSpaceholder
        UserRole.Spacepirate -> TemplateFile.HomeSpacepirate
        UserRole.Admin -> TemplateFile.HomeAdmin
    }
    val homeTdr = HomeTDR(
        user = user.toUserTD()
    )
    respondFreemarker(templateFile, homeTdr)
}

private fun User.toUserTD() = UserTD(
    name = name,
    email = email,
    tokens = tokens
)
