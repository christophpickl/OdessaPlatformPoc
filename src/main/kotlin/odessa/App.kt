package odessa

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import odessa.view.configureKtor

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        start(LocalAppConfig)
    }
    
    private fun start(appConfig: AppConfig) {
        println("⭐ Starting webserver at: http://localhost:${appConfig.port} ⭐️")
        embeddedServer(Netty, port = appConfig.port) {
            configureKtor(appConfig)
        }.start(wait = true)
    }
}
