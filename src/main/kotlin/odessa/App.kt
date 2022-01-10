package odessa

import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import java.io.File
import java.io.OutputStreamWriter
import java.io.Writer

data class Event(
    val url: String,
    val name: String
)

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        println("hello odessa")
        
        val cfg = buildConfig()
    
        val root = mutableMapOf<String, Any?>()
        root["user"] = "Big Joe"
        root["events"] = listOf(
            Event(name = "Dance", url = "foobar.nl")
        )
        /* Get the template (uses cache internally) */
        val temp: Template = cfg.getTemplate("test.ftlh")
        /* Merge data-model with template */
        val out: Writer = OutputStreamWriter(System.out)
        temp.process(root, out)
    }
    
    private fun buildConfig(): Configuration {
        val cfg = Configuration() // Configuration.VERSION_2_3_29
        cfg.setClassForTemplateLoading(App::class.java, "/templates")
        cfg.defaultEncoding = "UTF-8"
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
//        cfg.setLogTemplateExceptions(false)
//        cfg.setWrapUncheckedExceptions(true)
//        cfg.setFallbackOnNullLoopVariable(false)
        return cfg
    }
}
