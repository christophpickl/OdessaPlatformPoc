package odessa.view

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.sessions
import io.ktor.util.pipeline.PipelineContext
import odessa.AppConfig

private fun ApplicationCall.clearUserSession() {
    sessions.clear(KtorConstants.sessionID)
}

var ApplicationCall.userSession: UserSession?
    get() = sessions.get(KtorConstants.sessionID) as UserSession?
    set(value) {
        sessions.set(KtorConstants.sessionID, value)
    }

object KtorConstants {
    const val sessionID = "user_session2"
}

fun Routing.installRoutes(appConfig: AppConfig) {
    suspend fun PipelineContext<Unit, ApplicationCall>.doLogin(userShortCode: String) {
        val (templateSpec, userSession) = Controller.login(userShortCode)
        call.userSession = userSession
        call.respondTemplate(templateSpec)
        appConfig.clearFreemarkerCache()
    }
    
    static("/static") {
        appConfig.configureStaticFolder(this)
    }
    
    get("/") {
        call.respondTemplate(Controller.homeSpec(call.userSession))
        appConfig.clearFreemarkerCache()
    }
    
    get("/logout") {
        call.clearUserSession()
        call.respondTemplate(TemplateSpec(TemplateFile.Login, null))
        appConfig.clearFreemarkerCache()
    }
    
    get("/quickLogin") {
        val userShortCode = call.parameters["userShortCode"]!!
        
        doLogin(userShortCode)
    }
    
    post("/") {
        val params = call.receiveParameters()
        val email = params["email"] ?: "" // via POST form
    
        doLogin(email)
    }
    
    post("/events/{eventId}/plannedAssistance/add") {
        val eventId = call.parameters["eventId"]?.toInt() ?: throw Exception("Invalid eventId!")
        val templateSpec = Controller.planAssistance(call.userSession!!, eventId)
        call.respondTemplate(templateSpec)
        appConfig.clearFreemarkerCache()
    }
    
    post("/events/{eventId}/plannedAssistance/delete") {
        val eventId = call.parameters["eventId"]?.toInt() ?: throw Exception("Invalid eventId!")
        val templateSpec = Controller.removeAssistance(call.userSession!!, eventId)
        call.respondTemplate(templateSpec)
        appConfig.clearFreemarkerCache()
    }
    

}

