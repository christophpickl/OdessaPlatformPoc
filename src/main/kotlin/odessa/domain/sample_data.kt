@file:Suppress("ObjectPropertyName", "unused")

package odessa.domain

import java.time.LocalDateTime

// USERS
// ==========================================================================================

private val _userYana = User(
    id = 1,
    name = "Yana",
    email = "yana@odessa.com",
    tokens = 50,
    role = UserRole.Spaceholder,
    lastLogin = null,
)
val UserRepository.userYana get() = _userYana

private val _userChristoph = User(
    id = 2,
    name = "Christoph",
    email = "christoph@odessa.com",
    tokens = 200,
    role = UserRole.Spacepirate,
    lastLogin = null,
)
val UserRepository.userChristoph get() = _userChristoph

private val _userAmber = User(
    id = 3,
    name = "Amber",
    email = "amber@odessa.com",
    tokens = 920,
    role = UserRole.Admin,
    lastLogin = null,
)
val UserRepository.userAmber get() = _userAmber

// TEAMS
// ==========================================================================================
private val _teamFriday = Team(
    id = 1,
    name = "Friday"
)

val TeamRepository.teamFriday get() = _teamFriday

// EVENTS
// ==========================================================================================

private fun LocalDateTime.evening() = withHour(19).withMinute(0).withSecond(0)

private val _danceFriday1 = Event(
    id = 1,
    name = "Ecstatic Dance Friday 1 Old",
    begin = LocalDateTime.now().minusDays(7).evening(),
    durationMinutes = 120,
    tokensCost = 30,
    tokensReward = 5,
    url = "https://odessa.amsterdam/friday1",
    notes = "",
)
val EventRepository.danceFriday1 get() = _danceFriday1

private val _danceFriday2 = Event(
    id = 2,
    name = "Ecstatic Dance Friday 2",
    begin = LocalDateTime.now().plusDays(1).evening(),
    durationMinutes = 120,
    tokensCost = 30,
    tokensReward = 5,
    url = "https://odessa.amsterdam/friday2",
    notes = "",
)
val EventRepository.danceFriday2 get() = _danceFriday2

private val _danceFriday3 = Event(
    id = 3,
    name = "Ecstatic Dance Friday 3",
    begin = LocalDateTime.now().plusDays(8).evening(),
    durationMinutes = 120,
    tokensCost = 30,
    tokensReward = 5,
    url = "https://odessa.amsterdam/friday3",
    notes = "",
)
val EventRepository.danceFriday3 get() = _danceFriday3

private val _danceSaturday = Event(
    id = 4,
    name = "Ecstatic Dance Saturday",
    begin = LocalDateTime.now().plusDays(3).evening(),
    durationMinutes = 120,
    tokensCost = 30,
    tokensReward = 5,
    url = "https://odessa.amsterdam/saturday",
    notes = "",
)
val EventRepository.danceSaturday get() = _danceSaturday

private val _yogaEvent = Event(
    id = 5,
    name = "Yoga Workshop",
    begin = LocalDateTime.parse("2022-06-16T18:00:00"),
    durationMinutes = 60,
    tokensCost = 100,
    tokensReward = null,
    url = null,
    notes = "",
)
val EventRepository.yogaEvent get() = _yogaEvent

// UserTeamRefRepository
// ==========================================================================================

private val _yanaFriday = UserTeamRef(
    userId = UserRepository.userYana.id,
    teamId = TeamRepository.teamFriday.id
)
val UserTeamRefRepository.yanaFriday get() = _yanaFriday

private val _christophFriday = UserTeamRef(
    userId = UserRepository.userChristoph.id,
    teamId = TeamRepository.teamFriday.id
)
val UserTeamRefRepository.christophFriday get() = _christophFriday

// EventTeamRefRepository
// ==========================================================================================

private val _friday1 = EventTeamRef(
    teamId = TeamRepository.teamFriday.id,
    eventId = EventRepository.danceFriday1.id
)
val EventTeamRefRepository.friday1 get() = _friday1

private val _friday2 = EventTeamRef(
    teamId = TeamRepository.teamFriday.id,
    eventId = EventRepository.danceFriday2.id
)
val EventTeamRefRepository.friday2 get() = _friday2
