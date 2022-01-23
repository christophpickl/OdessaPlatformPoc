package odessa.view

import odessa.domain.Event
import odessa.domain.PlannedAssistance
import odessa.domain.PlannedAssistanceRepository
import odessa.domain.Service
import odessa.domain.User
import odessa.domain.UserRepository
import odessa.domain.UserRole
import odessa.templates.FutureTeamEventTD
import odessa.templates.HomeTDR
import odessa.templates.UserTD

object Controller {
    
    fun homeSpec(session: UserSession?): TemplateSpec =
        if (session == null) {
            TemplateSpec(TemplateFile.Login, null)
        } else {
            val user = UserRepository.getByEmail(session.email)
            buildHomeSpec(user)
        }
    
    fun login(email: String): Pair<TemplateSpec, UserSession?> {
        val user = UserRepository.findByShortId(email)
        return if (user == null) {
            TemplateSpec(TemplateFile.Login, null) to null
        } else {
            buildHomeSpec(user) to UserSession(user.email)
        }
    }
    
    fun planAssistance(userSession: UserSession, eventId: Int): TemplateSpec {
        val user = UserRepository.getByEmail(userSession.email)
        PlannedAssistanceRepository.add(PlannedAssistance(user.id, eventId))
        return buildHomeSpec(user)
    }
    
    fun removeAssistance(userSession: UserSession, eventId: Int): TemplateSpec {
        val user = UserRepository.getByEmail(userSession.email)
        PlannedAssistanceRepository.remove(PlannedAssistance(user.id, eventId))
        return buildHomeSpec(user)
    }
    
    private fun buildHomeSpec(user: User): TemplateSpec =
        TemplateSpec(user.role.toTemplateFile(), buildHomeTdr(user))
    
    private fun buildHomeTdr(user: User): HomeTDR {
        val events = Service.getFutureTeamEventsForUser(user)
        val eventsAndIsAssisting = Service.enrichEventAndIsAssisting(user.id, events)
        val eventsAndSpaceholders = Service.enrichEventAndSpaceholders(events)
        return HomeTDR(
            user = user.toUserTD(),
            teamEvents = events.map { event ->
                val isAssisting = eventsAndIsAssisting[event]!!
                val spaceholders = eventsAndSpaceholders[event]!!
                event.toFutureTeamEventTD(isAssisting, spaceholders)
            }
        )
    }
    
    private fun UserRole.toTemplateFile() = when (this) {
        UserRole.Spaceholder -> TemplateFile.HomeSpaceholder
        UserRole.Spacepirate -> TemplateFile.HomeSpacepirate
        UserRole.Admin -> TemplateFile.HomeAdmin
    }
    
    private fun Event.toFutureTeamEventTD(isAssisting: Boolean, spaceholders: List<String>) = FutureTeamEventTD(
        id = id,
        name = name,
        begin = begin,
        tokensCost = tokensCost,
        tokensReward = tokensReward,
        url = url,
        isAssisting = isAssisting,
        spaceholders = spaceholders
    )
    
    private fun User.toUserTD() = UserTD(
        name = name,
        email = email,
        tokens = tokens
    )
    
}
