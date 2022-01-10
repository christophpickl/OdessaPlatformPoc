<html>
<body>
<!-- see src/main/kotlin/odessa/templates/test -->
<p>Hello ${username}!</p>
<ul>
<#list events as event>
    <li><a href="${event.url}">${event.name}</a></li>
</#list>
</ul>
</body>
</html>