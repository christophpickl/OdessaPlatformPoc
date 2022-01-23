<html>
<head>
    <#include "includes/header.ftl">
</head>
<body>
<h1>Odessa Platform - Login</h1>

enter as email (password will be ignored):
<ul>
    <li><a href="/quickLogin?userShortCode=a">a = spaceholder yana</a></li>
    <li><a href="/quickLogin?userShortCode=b">b = spacepirate christoph</a></li>
    <li><a href="/quickLogin?userShortCode=c">c = admin amber</a></li>
</ul>

<form method="post" action="/">
    <input type="text" name="email" placeholder="E-Mail"/><br/>
    <input type="text" name="password" placeholder="Password"/><br/>
    <input type="submit" value="Login">
</form>

</body>
</html>