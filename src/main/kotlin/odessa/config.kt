package odessa

import freemarker.cache.ClassTemplateLoader
import freemarker.cache.FileTemplateLoader
import freemarker.template.Configuration
import io.ktor.http.content.files
import io.ktor.http.content.resources
import io.ktor.routing.Route
import java.io.File

sealed class AppConfig {
    val port = 8080
    
    abstract fun configureFreemarker(configuration: Configuration)
    abstract fun configureStaticFolder(route: Route)
    abstract fun clearFreemarkerCache()
}

object LocalAppConfig : AppConfig() {
    private var clearFreemarkerCacheFunction = {}
    
    override fun configureFreemarker(configuration: Configuration) {
        configuration.templateLoader = FileTemplateLoader(File("src/main/resources/templates"))
        clearFreemarkerCacheFunction = {
            configuration.clearTemplateCache()
        }
    }
    
    override fun configureStaticFolder(route: Route) {
        route.files("src/main/resources/static")
    }
    
    override fun clearFreemarkerCache() {
        clearFreemarkerCacheFunction()
    }
}

object RealAppConfig : AppConfig() {
    override fun configureFreemarker(configuration: Configuration) {
        configuration.templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    
    override fun configureStaticFolder(route: Route) {
        route.resources("static")
    }
    
    override fun clearFreemarkerCache() {
        // not needed
    }
}
