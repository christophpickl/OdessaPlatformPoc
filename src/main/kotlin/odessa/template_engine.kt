package odessa

import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import java.io.StringWriter

//class TemplateEngineFactory {
//    fun build(): TemplateEngine =
//        TemplateEngine(buildConfig())
//
//    private fun buildConfig(): Configuration {
//        val cfg = Configuration() // Configuration.VERSION_2_3_29
//        cfg.setClassForTemplateLoading(App::class.java, "/templates")
//        cfg.defaultEncoding = "UTF-8"
//        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
////        cfg.setLogTemplateExceptions(false)
////        cfg.setWrapUncheckedExceptions(true)
////        cfg.setFallbackOnNullLoopVariable(false)
//        return cfg
//    }
//}
//
//class TemplateEngine(
//    private val config: Configuration
//) {
//    fun merge(templateFilePath: String, data: Map<String, Any?>): String {
//        val temp = config.getTemplate(templateFilePath)
//        val string = StringWriter()
//        temp.process(data, string)
//        return string.toString()
//    }
//}
