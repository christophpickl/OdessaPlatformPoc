<h2>Team Events</h2>
<#if teamEvents?size == 0>
    Empty :-/
<#else>
<table>
    <tr>
        <th>Name</th>
        <th>Token value</th>
        <th>Actions</th>
    </tr>
    <#list teamEvents as event>
        <tr>
            <td>${event.name}</td>
            <td>${event.tokensReward}</td>
            <!-- TODO provide state, to decide what exactly to render here -->
            <td>
                <#if event.isAssisting()==true>
                    <form method="post" action="/events/${event.id}/plannedAssistance/delete">
                        <input type="submit" value="Remove Assist" />
                    </form>
                <#else>
                    <form method="post" action="/events/${event.id}/plannedAssistance/add">
                        <input type="submit" value="Plan Assist" />
                    </form>
                </#if>
            </td>
        </tr>
    </#list>
</table>

</#if>