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
    Test("test.ftl")
}
