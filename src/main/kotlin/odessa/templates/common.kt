package odessa.templates

import java.time.LocalDateTime

interface TemplateDataRoot
interface TemplateData

data class UserTD(
    val name: String,
    val email: String,
    val tokens: Int,
): TemplateData {
    companion object
}

data class EventTD(
    val title: String,
    val startDate: LocalDateTime,
    val tokensCost: Int?,
    val tokensGain: Int?,
    val url: String?
): TemplateData {
    companion object
}
