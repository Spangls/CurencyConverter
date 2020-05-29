<#import "parts/body.ftlh" as body>

<@body.body>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
    <h2>Управление пользоватем</h2>
    <#if message??>
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
    </#if>
    <form action="<#if !customEdit??>/admin/user/save<#else>/user/edit</#if>" method="post">
        <div>
            <table>
                <tbody>
                <tr>
                    <td><label for="id"/>Id</td>
                    <td><input type="text" name="userId" id="userId" readonly value="${user.id}"></td>
                </tr>
                <tr>
                    <td><label for="email"/>Email</td>
                    <td><input type="text" name="email" id="email" value="${user.email}"></td>
                </tr>
                <tr>
                    <td><label for="password"/>Новый пароль</td>
                    <td><input type="password" name="password" id="password" value=""></td>
                </tr>
                <tr>
                    <td><label for="surname"/>Фамилия</td>
                    <td><input type="text" name="surname" id="surname" value="${user.surname}"></td>
                </tr>
                <tr>
                    <td><label for="first-name"/>Имя</td>
                    <td><input type="text" name="firstName" id="first-name" value="${user.firstName}"></td>
                </tr>
                <tr>
                    <td><label for="second-name"/>Отчество</td>
                    <td><input type="text" name="secondName" id="second-name" value="${user.secondName}"></td>
                </tr>
                <#if !customEdit??>
                    <@security.authorize access="hasAuthority('ADMIN')">
                        <tr>
                            <td>Уровни<br>доступа</td>
                            <td>
                                <#list  roles as role>
                                    <div>
                                        <label><input type="checkbox" name="${role}"
                                                      value="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                                        </label>
                                    </div>
                                </#list>
                            </td>
                        </tr>
                    </@security.authorize>
                </#if>
                </tbody>
            </table>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Сохранить</button>
    </form>
</@body.body>

