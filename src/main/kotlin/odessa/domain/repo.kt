package odessa.domain

import mu.KotlinLogging.logger
import java.time.LocalDateTime

object UserRepository {
    
    private val users = mutableListOf(userYana, userChristoph, userAmber)
    
    fun findByShortId(shortId: String): User? =
        when (shortId) {
            "a" -> users[0]
            "b" -> users[1]
            "c" -> users[2]
            else -> null
        }
    
    fun getByEmail(email: String): User =
        users.firstOrNull { it.email == email } ?: throw Exception("User not found by email '$email'!")
    
    fun fetchUsers(userIds: List<Int>): List<User> =
        userIds.map { userId ->
            users.first { it.id == userId }
        }
}

object TeamRepository {
    private val teams = mutableListOf(teamFriday)
    
    fun loadTeams(): List<Team> = teams
}

object UserTeamRefRepository {
    private val refs = mutableListOf(yanaFriday, christophFriday)
    
    fun getTeamIdsForUser(userId: Int): List<Int> =
        refs.filter { it.userId == userId }.map { it.teamId }
}

object EventRepository {
    
    private val events = mutableListOf(
        danceFriday1,
        danceFriday2,
        danceFriday3,
        danceSaturday,
        yogaEvent
    )
    
    fun getFutureEventsById(eventIds: List<Int>): List<Event> {
        val now = LocalDateTime.now()
        return events.filter { event ->
            event.begin.isAfter(now)
            eventIds.contains(event.id)
        }
    }
}


object EventTeamRefRepository {
    private val refs = mutableListOf(friday1, friday2)
    
    fun getEventIdsForTeams(teamIds: List<Int>): List<Int> =
        refs.filter { teamIds.contains(it.teamId) }.map { it.eventId }
}

object PlannedAssistanceRepository {
    private val log = logger {}
    private val assistances = mutableListOf<PlannedAssistance>()
    private val userIdsByEventIds = mutableMapOf<Int, MutableList<Int>>()
    
    fun add(assistance: PlannedAssistance) {
        log.debug { "add: $assistance" }
        assistances += assistance
        userIdsByEventIds.getOrPut(assistance.eventId, { mutableListOf() }).add(assistance.userId)
    }
    
    fun remove(assistance: PlannedAssistance) {
        assistances.remove(assistance)
        userIdsByEventIds[assistance.eventId]!!.remove(assistance.userId)
    }
    
    fun computeEventIdAndIsAssisting(userId: Int, events: List<Event>): Map<Int, Boolean> =
        events.associate { it.id to (userIdsByEventIds[it.id]?.contains(userId) ?: false) }
    
    fun getUserIdsFor(eventId: Int): List<Int> =
        userIdsByEventIds[eventId] ?: emptyList()
    
}