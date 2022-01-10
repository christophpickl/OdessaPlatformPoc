package odessa.templates

import java.time.LocalDateTime

data class User(
    val name: String,
    val tokens: Int,
)

data class Event(
    val title: String,
    val startDate: LocalDateTime,
    val tokensCost: Int?,
    val tokensGain: Int?,
    val url: String?
)
