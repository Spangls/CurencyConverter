<#import "parts/body.ftlh" as body>
<#import "parts/userForms.ftlh" as userForms>

<@body.body>
    Sign In
    <@userForms.login "/login" "Sign In"/>
    <a href="/registration">Sign Up</a>
</@body.body>