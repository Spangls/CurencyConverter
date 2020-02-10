<#import "parts/body.ftlh" as body>
<#import "parts/userForms.ftlh" as userForms>

<@body.body>
    <h2>Регистрация</h2>
    <@userForms.login "/registration" "Зарегитрироваться"/>
</@body.body>