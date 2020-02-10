<#macro login path button>
    <form action="${path}" method="post">
        <div><input type="text" name="username" placeholder="Логин"/></div>
        <div><input type="password" name="password" placeholder="Пароль"/></div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
        <div><input type="submit" value="${button}"/></div>
    </form>
</#macro>