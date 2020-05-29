<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Выполняемые услуги</h2>
    <div>Наша компания предоставляет ряд услуг, оставить заявку на выполнение которых вы можете ниже</div>
    <div class="mt-5">
        <form method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <select class="form-control form-control-lg" name="serviceType">
                <#if serviceTypes??>
                    <#list serviceTypes as type>
                        <option name="type" value="${type}">${type.title}</option>
                    </#list>
                </#if>
            </select>
            <button type="submit" class="mt-3 btn btn-primary">отправить заявку</button>
        </form>
    </div>
    <div class="mt-3">
        <#if success??>
            <#if success>
                <div class="text-info">Заявка отправлена успешно!</div>
            <#else>
                <div class="text-danger">При создании заявки произошла непредвиденная ошибка, попробуйте позже!</div>
            </#if>
        </#if>
    </div>
</@body.body>