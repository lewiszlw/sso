<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authentication Service</title>
</head>
<body>
<div align="center">
    <form method="POST" action="/sso/login">
        <h2>SSO Login</h2>
        <input name="username" type="text" placeholder="Username"
               autofocus="true"/>
        <input name="password" type="password" placeholder="Password"/>
        <input name="redirectUri" type="text" hidden value="${redirectUri!}" />
        <input name="clientId" type="text" hidden value="${clientId!}" />
        <div style="color: red">${error!}</div>
        <br/>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>