<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Производители</h2>
    <a href="/manufacturer/new">Добавить</a>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>Наименование</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <#list manufacturers as manufacturer>
            <tr>
                <td>${manufacturer.id}</td>
                <td>${manufacturer.title}</td>
                <td><a href="/manufacturer/${manufacturer.id}">Изменить</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@body.body>