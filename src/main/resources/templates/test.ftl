<html>
<head>
    <link rel="stylesheet" href="/static/main.css">
</head>
<body>
<!-- see src/main/kotlin/odessa/templates/test -->
<p>Hello ${user.name}! (${user.tokens} ⭐️)</p>
<ul>
<#list events as event>
    <!-- check if event.url is not null, then render as link -->
    <li>${event.title}</li>
</#list>
</ul>
</body>
</html>