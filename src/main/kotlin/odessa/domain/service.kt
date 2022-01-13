package odessa.domain

object Service {
    fun getTeamEventsForUser(user: User): List<Event> {
        val teamIds = UserTeamRefRepository.getTeamIdsForUser(user.id)
        if(teamIds.isEmpty()) return emptyList()
        val eventIds = EventTeamRefRepository.getEventIdsForTeams(teamIds)
        if(eventIds.isEmpty()) return emptyList()
        return EventRepository.getByIds(eventIds)
    }
    
    fun enrichEventAndIsAssisting(userId: Int, events: List<Event>): List<EventAndIsAssisting> {
        val attendingByEventId = PlannedAssistanceRepository.computeEventIdAndIsAssisting(userId, events)
        return events.map {
            EventAndIsAssisting(it, attendingByEventId[it.id]!!)
        }
    }
}

data class EventAndIsAssisting(
    val event: Event,
    val isAssisting: Boolean
)
