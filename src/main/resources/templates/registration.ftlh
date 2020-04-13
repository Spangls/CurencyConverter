<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Регистрация</h2>
    <form action="/registration" method="post">
        <div><input type="text" name="username" placeholder="Логин"/></div>
        <div><input type="password" name="password" placeholder="Пароль"/></div>
        <div><input type="text" name="email" placeholder="email"></div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
        <div><input type="submit" value="Зарегитрироваться"/></div>
    </form>
</@body.body>