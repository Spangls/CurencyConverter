<#macro login path button>
    <form action="${path}" method="post">
        <div><label> Login : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
        <div><input type="submit" value="${button}"/></div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <div><input type="submit" value="Sign Out"/></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>