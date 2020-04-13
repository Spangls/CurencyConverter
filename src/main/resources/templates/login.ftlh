<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Вход</h2>
    <form action="/login" method="post">
        <div><input type="text" name="username" placeholder="Логин"/></div>
        <div><input type="password" name="password" placeholder="Пароль"/></div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
        <div><input type="submit" value="Войти"/></div>
    </form>
    <a href="/registration">Зарегистрироваться</a>
</@body.body>