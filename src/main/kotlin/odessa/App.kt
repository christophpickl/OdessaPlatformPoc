package odessa

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import odessa.templates.Event
import odessa.templates.TestData
import odessa.templates.TestTemplate

object App {
    private val port = 8080
    @JvmStatic
    fun main(args: Array<String>) {
        println("⭐ ️Starting webserver at: http://localhost:$port ⭐️")
        
        embeddedServer(Netty, port = port) {
            val engine = TemplateEngineFactory().build()
            routing {
                get("/") {
                    call.respondText("""
                        <html>
                        <body>
                        <h1>Available pages:</h1>
                        <ul>
                        <li><a href="/test">test</a></li>
                        </ul>
                        </body>
                        </html>
                    """.trimIndent(), ContentType.Text.Html, HttpStatusCode.OK)
                }
                get("/test") {
                    val output = TestTemplate.merge(
                        engine, TestData(
                            username = "Yana",
                            events = listOf(
                                Event(
                                    name = "Ecstatic Boat 1",
                                    url = "http://odessa.com/1"
                                )
                            )
                        )
                    )
                    call.respondText(output, ContentType.Text.Html, HttpStatusCode.OK)
                }
            }
        }.start(wait = true)
        // TODO use ktor-freemarker plugin: https://ktor.io/docs/freemarker.html#use_template
        // TODO reload template while server is running
        // TODO shutdown
    }
}
