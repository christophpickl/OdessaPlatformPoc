package odessa.templates

import java.time.LocalDateTime

val UserTD.Companion.yana
    get() = UserTD(
        name = "Yana",
        email = "ya@na.at",
        tokens = 21
    )

val EventTD.Companion.some get() = listOf(EventTD.dance, EventTD.tantra)

val EventTD.Companion.dance
    get() = EventTD(
        title = "Ecstatic Dance",
        startDate = LocalDateTime.parse("2020-01-13T18:30:00"),
        url = null,
        tokensCost = 5,
        tokensGain = 2
    )

val EventTD.Companion.tantra
    get() = EventTD(
        title = "Tantric Massage",
        url = "http://odessa.com/tantric",
        startDate = LocalDateTime.parse("2020-01-25T14:00:00"),
        tokensCost = 30,
        tokensGain = null
    )
