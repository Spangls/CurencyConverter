<#import "parts/body.ftlh" as body>


<@body.body>
    <h2>Вход</h2>
    <form action="/login" method="post" class="mb-5">
        <div class="form-group">
            <label for="username">Эл. почта</label>
            <input id="username" type="text" name="username" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input id="password" type="password" name="password" class="form-control"/>
        </div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
    <a href="/registration">Зарегистрироваться</a>
    <br>
    <a href="/user/reset">Восстановление пароля</a>
</@body.body>