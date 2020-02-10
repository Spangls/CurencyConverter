<#import "parts/body.ftlh" as body>
<#import "parts/userForms.ftlh" as userForms>

<@body.body>
    Sign Up
    @{message}
    <@userForms.login "/registration" "Sign Up"/>
</@body.body>