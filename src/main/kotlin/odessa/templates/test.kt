package odessa.templates

data class TestTDR(
    val user: UserTD,
    val events: List<EventTD>
): TemplateDataRoot
