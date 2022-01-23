<html>
<head>
    <#include "includes/header.ftl">
</head>
<body>
<h1>Home for Admin ${user.name}</h1>

Content:
<ul>
    <li>Same as spaceholder (but for all users); maybe no team events as (usually!) not part of team?!</li>
    <li>Same as spacepirate (for all team/events)</li>
    <li>Manage: users, teams, events</li>
    <li>Process exchange requests (active, confirmed, declined)</li>
</ul>
<br/>

<#include "includes/team_events.ftl">
<#include "includes/regular_events.ftl">

<hr/>
<a href="/logout">Logout</a>
</body>
</html>