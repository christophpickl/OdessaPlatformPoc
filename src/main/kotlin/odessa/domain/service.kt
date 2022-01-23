package odessa.domain

object Service {
    fun getFutureTeamEventsForUser(user: User): List<Event> {
        val teamIds = UserTeamRefRepository.getTeamIdsForUser(user.id)
        if(teamIds.isEmpty()) return emptyList()
        val eventIds = EventTeamRefRepository.getEventIdsForTeams(teamIds)
        if(eventIds.isEmpty()) return emptyList()
        return EventRepository.getFutureEventsById(eventIds)
    }
    
    fun enrichEventAndIsAssisting(userId: Int, events: List<Event>): Map<Event, Boolean> {
        val attendingByEventId = PlannedAssistanceRepository.computeEventIdAndIsAssisting(userId, events)
        return events.associateWith {
            attendingByEventId[it.id]!!
        }
    }
    
    fun enrichEventAndSpaceholders(events: List<Event>): Map<Event, List<String>> =
        events.associateWith { event ->
            val userIds = PlannedAssistanceRepository.getUserIdsFor(event.id)
            val users = UserRepository.fetchUsers(userIds)
            users.map { it.name }
        }
}

