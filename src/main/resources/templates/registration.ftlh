<#import "parts/body.ftlh" as body>


<@body.body>
    <h2>Регистрация</h2>
    <div>
        <#if message??>
            <div class="text-danger">${message}</div>
        </#if>
    </div>
    <form action="/registration" method="post">
        <div><p>Поля, отмеченные * обязательны к заполнению.</p></div>
        <div class="form-group">
            <label for="surname">Фамилия</label>
            <input id="surname" type="text" name="surname" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="surname">Имя*</label>
            <input id="firstName" type="text" name="firstName" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="surname">Отчество</label>
            <input id="secondName" type="text" name="secondName" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="surname">Пароль*</label>
            <input id="password" type="password" name="password" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="surname">Эл. почта*</label>
            <input id="email" type="email" name="email" class="form-control">
        </div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
        <button type="submit" class="btn btn-primary">Зарегитрироваться"</button>
    </form>
</@body.body>