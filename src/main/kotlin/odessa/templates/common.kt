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

interface IEventTD {
    val id: Int
    val name: String
    val begin: LocalDateTime
}

data class FutureTeamEventTD(
    override val id: Int,
    override val name: String,
    override val begin: LocalDateTime,
    val tokensCost: Int?,
    val tokensReward: Int?,
    val url: String?,
    val isAssisting: Boolean,
    val spaceholders: List<String>,
): TemplateData, IEventTD {
    companion object
}

data class EventTD(
    override val id: Int,
    override val name: String,
    override val begin: LocalDateTime,
    val tokensCost: Int?,
    val tokensReward: Int?,
    val url: String?
): TemplateData, IEventTD {
    companion object
}
