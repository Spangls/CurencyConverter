<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Управление пользователями</h2>
    <table>
        <thead>
        <tr>
            <td>id</td>
            <td>email</td>
            <td>roles</td>
            <td>ФИО</td>
        </tr>
        </thead>
        <tbody>
        <#list userList as user>
            <tr>
                <td>${user.id}</td>
                <td><a href="/admin/user/${user.id}">${user.email}</a></td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td>${user.surname} ${user.firstName} ${user.secondName}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@body.body>