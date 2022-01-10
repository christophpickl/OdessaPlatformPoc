package odessa

import freemarker.cache.FileTemplateLoader
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
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
                installRoutes(clearFreemarkerCache)
            }
        }.start(wait = true)
        // TODO shutdown
    }
}
