package odessa.templates

/** Applies to all three home pages for now. */
data class HomeTDR(
    val user: UserTD,
    val teamEvents: List<FutureTeamEventTD>
) : TemplateDataRoot
