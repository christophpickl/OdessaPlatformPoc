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

data class TeamEventTD(
    val id: Int,
    val name: String,
    val begin: LocalDateTime,
    val tokensCost: Int?,
    val tokensReward: Int?,
    val url: String?,
    val isAssisting: Boolean
): TemplateData {
    companion object
}

data class EventTD(
    val id: Int,
    val name: String,
    val begin: LocalDateTime,
    val tokensCost: Int?,
    val tokensReward: Int?,
    val url: String?
): TemplateData {
    companion object
}
