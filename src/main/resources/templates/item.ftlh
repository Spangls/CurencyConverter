<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Товар</h2>
    <form action="item" method="post">
        <div><input type="text" name="title" placeholder="Наименование"/></div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
        <div><input type="submit" value="Добавить"/></div>
        <select name="sourceCurrency">
            <#list manufacturerList as manufacturer>
                <option name="manufacturer" value="${manufacturer.title}">${manufacturer.title}</option>
            </#list>
        </select>
        <select name="sourceCurrency">
            <#list manufacturerList as manufacturer>
                <option name="manufacturer" value="${manufacturer.title}">${manufacturer.title}</option>
            </#list>
        </select>
    </form>
</@body.body>