<#import "parts/body.ftlh" as body>
<#import "parts/userForms.ftlh" as userForms>

<@body.body>
    <h2>Вход</h2>
    <@userForms.login "/login" "Войти"/>
    <a href="/registration">Зарегистрироваться</a>
</@body.body>