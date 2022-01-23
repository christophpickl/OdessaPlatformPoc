<html>
<head>
    <#include "includes/header.ftl">
</head>
<body>
<h1>Home for Spaceholder ${user.name}</h1>
Content:
<ul>
    <li>His user details: Picture, Name, Team-membership, token balance</li>
    <li>Messages (badge for notification alert)</li>
    <li>Token history: each row like a log, +/- amount tokens, reason (participation, exchange, manual)</li>
    <li>Past/future team events: name, date; link to register participation</li>
    <li>Other events: link to request exchange</li>
</ul>
<br/>

<a href="javascript:notify('Notify me!')">Notify</a><br/>

<!-- with import directive, we can create our own tags, and parametrize them even -->
<!-- only render team events when user is associated with at least one team -->
<#include "includes/team_events.ftl">

<#include "includes/regular_events.ftl">

<br/>
<hr/>
<a href="/logout">Logout</a>
</body>
</html>