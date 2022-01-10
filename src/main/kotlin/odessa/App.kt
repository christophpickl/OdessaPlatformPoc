package odessa

import odessa.templates.Event
import odessa.templates.TestData
import odessa.templates.TestTemplate

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val engine = TemplateEngineFactory().build()

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
        
        println("=============")
        println(output)
        println("=============")
    }
}
