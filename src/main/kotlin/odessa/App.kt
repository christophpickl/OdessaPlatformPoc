package odessa

import freemarker.cache.FileTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import odessa.templates.Event
import odessa.templates.TestData
import java.io.File

object App {
    private const val port = 8080
    
    @JvmStatic
    fun main(args: Array<String>) {
        println("⭐ ️Starting webserver at: http://localhost:$port ⭐️")
        embeddedServer(Netty, port = port) {
            var clearFreemarkerCache = {}
            install(FreeMarker) {
//                templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
                templateLoader = FileTemplateLoader(File("src/main/resources/templates"))
                clearFreemarkerCache = {
                    clearTemplateCache()
                }
            }
            
            routing {
                static("/static") {
                    resources("static")
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
        }.start(wait = true)
        // TODO shutdown
    }
}
