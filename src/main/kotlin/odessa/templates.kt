package odessa

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond


suspend fun ApplicationCall.respondFreemarker(file: TemplateFile, data: Any?) {
    respond(FreeMarkerContent(file.path, data))
}

enum class TemplateFile(
    val path: String
) {
    Login("login.ftl"),
    HomeSpaceholder("home_spaceholder.ftl"),
    HomeSpacepirate("home_spacepirate.ftl"),
    HomeAdmin("home_admin.ftl"),
    Test("test.ftl")
}
