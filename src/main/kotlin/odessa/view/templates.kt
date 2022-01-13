package odessa.view

import io.ktor.application.ApplicationCall
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond

suspend fun ApplicationCall.respondTemplate(spec: TemplateSpec) {
    respond(FreeMarkerContent(spec.file.path, spec.data))
}

data class TemplateSpec(
    val file: TemplateFile,
    val data: Any?
)

enum class TemplateFile(
    val path: String
) {
    Login("login.ftl"),
    HomeSpaceholder("home_spaceholder.ftl"),
    HomeSpacepirate("home_spacepirate.ftl"),
    HomeAdmin("home_admin.ftl")
}
