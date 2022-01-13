package odessa.domain

import java.time.LocalDateTime

enum class UserRole {
    Spaceholder,
    Spacepirate,
    Admin
}

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val tokens: Int,
    val role: UserRole
)

data class Team(
    val id: Int,
    val name: String
)

data class UserTeamRef(
    val userId: Int,
    val teamId: Int
)

data class Event(
    val id: Int,
    val name: String,
    val begin: LocalDateTime,
    val tokensCost: Int?,
    val tokensReward: Int?,
    val url: String?
)

data class EventTeamRef(
    val eventId: Int,
    val teamId: Int
)

data class PlannedAssistance(
    val userId: Int,
    val eventId: Int
)

data class Assistance(
    val userId: Int,
    val eventId: Int
)

sealed class TokenHistory(
    val id: Int,
    val tokenChange: Int,
    val created: LocalDateTime
)

class AssistanceTokenHistory(
    id: Int,
    tokenChange: Int,
    created: LocalDateTime,
    val assitanceId: Int
) : TokenHistory(
    id, tokenChange, created
)

class TicketTokenHistory(
    id: Int,
    tokenChange: Int,
    created: LocalDateTime,
    val ticketId: Int
) : TokenHistory(
    id, tokenChange, created
)

class ManualTokenHistory(
    id: Int,
    tokenChange: Int,
    created: LocalDateTime,
    val comment: String,
    val userId: Int
) : TokenHistory(
    id, tokenChange, created
)

data class TicketRequest(
    val id: Int,
    val userId: Int,
    val eventId: Int,
    val created: LocalDateTime,
    val state: TicketRequestState
)

enum class TicketRequestState {
    New,
    Active,
    Confirmed,
    Rejected
}

data class Ticket(
    val id: Int,
    val ticketRequestId: Int,
    val created: LocalDateTime,
)
