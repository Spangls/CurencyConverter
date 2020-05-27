<#import "parts/body.ftlh" as body>

<@body.body>
<#if message??><p>${message}</p></#if>

<#if checkEmail??>
    <h2>На указанную почту было выслано письмо с подтверждением и дальнейшими инструкциями.</h2>
<#else>
    <form method="post" action="/user/<#if userId??>saveNew<#else>reset</#if>">
        <table>
            <tbody>
            <#if userId??>
                <tr>
                    <td><label for="password">Новый пароль</label></td>
                    <td><input type="password" name="password" id="password"></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="userId" id="userId" value="${userId}"></td>
                </tr>
            <#else>
                <tr>
                    <td><label for="email">Эл. почта</label></td>
                    <td><input type="email" name="email" id="email"></td>
                </tr>
            </#if>
            </tbody>
        </table>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="<#if userId??>Сохранить<#else>Выслать</#if>">
    </form>
</#if>
</@body.body>