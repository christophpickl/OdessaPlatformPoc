package odessa

import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        start(LocalAppConfig)
    }
    
    private fun start(appConfig: AppConfig) {
        println("⭐ Starting webserver at: http://localhost:${appConfig.port} ⭐️")
        
        embeddedServer(Netty, port = appConfig.port) {
            install(FreeMarker) {
                appConfig.configureFreemarker(this)
            }
            routing {
                installRoutes(appConfig)
            }
        }.start(wait = true)
    }
}
