package odessa.domain

import mu.KotlinLogging.logger
import java.time.LocalDateTime

object UserRepository {
    val userYana = User(
        id = 1,
        name = "Yana",
        email = "yana@odessa.com",
        tokens = 50,
        role = UserRole.Spaceholder,
        lastLogin = null,
    )
    val userChristoph = User(
        id = 2,
        name = "Christoph",
        email = "christoph@odessa.com",
        tokens = 200,
        role = UserRole.Spacepirate,
        lastLogin = null,
    )
    val userAmber = User(
        id = 3,
        name = "Amber",
        email = "amber@odessa.com",
        tokens = 920,
        role = UserRole.Admin,
        lastLogin = null,
    )
    
    private val users = listOf(
        userYana,
        userChristoph,
        userAmber
    )
    
    fun findByShortId(shortId: String): User? =
        when (shortId) {
            "a" -> users[0]
            "b" -> users[1]
            "c" -> users[2]
            else -> null
        }
    
    fun getByEmail(email: String): User =
        users.firstOrNull { it.email == email } ?: throw Exception("User not found by email '$email'!")
}

object TeamRepository {
    val teamFriday = Team(
        id = 1,
        name = "Friday"
    )
    private val teams = listOf(
        teamFriday
    )
}

object UserTeamRefRepository {
    private val refs = listOf(
        UserTeamRef(
            userId = UserRepository.userYana.id,
            teamId = TeamRepository.teamFriday.id
        )
    )
    
    fun getTeamIdsForUser(userId: Int): List<Int> =
        refs.filter { it.userId == userId }.map { it.teamId }
}

object EventRepository {
    val ecstaticDance1Event = Event(
        id = 1,
        name = "Ecstatic Dance Friday 1",
        begin = LocalDateTime.parse("2022-01-14T19:00:00"),
        tokensCost = 30,
        tokensReward = 5,
        url = "https://odessa.amsterdam/friday1"
    )
    val ecstaticDance2Event = Event(
        id = 2,
        name = "Ecstatic Dance Friday 2",
        begin = LocalDateTime.parse("2022-01-21T19:00:00"),
        tokensCost = 30,
        tokensReward = 5,
        url = "https://odessa.amsterdam/friday21"
    )
    
    private val yogaEvent = Event(
        id = 3,
        name = "Yoga Workshop",
        begin = LocalDateTime.parse("2022-01-16T18:00:00"),
        tokensCost = 100,
        tokensReward = null,
        url = null
    )
    private val events = listOf(
        ecstaticDance1Event,
        ecstaticDance2Event,
        yogaEvent
    )
    
    fun getByIds(eventIds: List<Int>): List<Event> =
        events.filter { eventIds.contains(it.id) }
}

object EventTeamRefRepository {
    private val refs = listOf(
        EventTeamRef(
            teamId = TeamRepository.teamFriday.id,
            eventId = EventRepository.ecstaticDance1Event.id
        ),
        EventTeamRef(
            teamId = TeamRepository.teamFriday.id,
            eventId = EventRepository.ecstaticDance2Event.id
        )
    )
    
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
        userIdsByEventIds.getOrPut(assistance.eventId, { mutableListOf()}).add(assistance.userId)
    }
    
    fun remove(assistance: PlannedAssistance) {
        assistances.remove(assistance)
        userIdsByEventIds[assistance.eventId]!!.remove(assistance.userId)
    }
    
    fun computeEventIdAndIsAssisting(userId: Int, events: List<Event>): Map<Int, Boolean> =
        events.associate { it.id to (userIdsByEventIds[it.id]?.contains(userId) ?: false) }
    
}