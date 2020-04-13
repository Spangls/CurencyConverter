<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Производители</h2>
<form action="manufacturer" method="post">
    <div><input type="text" name="title" placeholder="Наименование"/></div>
    <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
    <div><input type="submit" value="Добавить"/></div>
</form>
</@body.body>