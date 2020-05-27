<#import "parts/body.ftlh" as body>

<@body.body>
    <h2><#if manufacturer??>Изменение производителя<#else>Добавление производителя</#if></h2>
    <form action="save" method="post">
    <table>
        <tbody>
        <#if manufacturer??>
            <tr>
                <td><label for="manufacturerId">Id</label></td>
                <td><input type="text" id="manufacturerId" name="manufacturerId" placeholder="Id" value="${manufacturer.id}" readonly/></td>
            </tr>
        </#if>
        <tr>
            <td><label for="title">Наименование</label></td>
            <td><input type="text" id="title" name="title" placeholder="Наименование" value="<#if manufacturer??>${manufacturer.title}</#if>"/></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="<#if manufacturer??>Сохранить<#else>Добавить</#if>"/>
    </form>
    <#if manufacturer??>
    <form action="/manufacturer/delete/" method="POST">
        <input type="hidden" id="manufacturerId" name="manufacturerId" placeholder="Id" value="${manufacturer.id}" readonly/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Удалить"/>
    </form>
    </#if>
    <#if message??><p>${message}</p></#if>
</@body.body>