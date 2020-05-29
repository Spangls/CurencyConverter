<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Заказы</h2>
    <div class="row justify-content-center">
        <div class="col-md-4">
            <a href="/employee/order" class="btn btn-primary">все заказы</a>
        </div>
        <div class="col-md-4">
            <a href="/employee/order/processed" class="btn btn-primary text-wrap">незавершенные заказы</a>
        </div>
        <div class="col-md-4">
            <a href="/employee/order/ready" class="btn btn-primary text-wrap">завершенные заказы</a>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-4">
            <a href="/service/all" class="btn btn-primary">все заявки</a>
        </div>
        <div class="col-md-4">
            <a href="/service/all/processed" class="btn btn-primary text-wrap">незавершенные заявки</a>
        </div>
        <div class="col-md-4">
            <a href="/service/all/ready" class="btn btn-primary text-wrap">завершенные заявки</a>
        </div>
    </div>
    <div class="text text-danger"><#if message??>${message}</#if></div>
    <#if orders??>
        <table class="table">
            <thead>
            <tr>
                <th>
                    <div class="text-wrap">Статус</div>
                </th>
                <th>
                    <div class="text-wrap">ID</div>
                </th>
                <th>
                    <div>Пользователь</div>
                </th>
                <th>
                    <div class="text-wrap">Дата поступления заказа</div>
                </th>
                <th>
                    <div>Итоговая стоимость</div>
                </th>
            </tr>
            </thead>
            <tbody>
            <#list orders as order>
                <tr>
                    <td>
                        <#if order.status == "READY">
                            <span class="badge badge-success">выполнен</span>
                        <#else>
                            <form method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <input type="hidden" name="orderId" value="${order.id}">
                                <button type="submit" class="badge badge-secondary">не выполнен</button>
                            </form>
                        </#if>
                    </td>
                    <td>${order.id}</td>
                    <td>${order.user.email}</td>
                    <td>${order.date}</td>
                    <td>${order.cost}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <#if !services??>
            <h3>Заказы отсутствуют</h3>
        </#if>
    </#if>
    <#if services??>
        <table class="table">
            <thead>
            <tr>
                <th>
                    <div class="text-wrap">Статус</div>
                </th>
                <th>
                    <div class="text-wrap">ID</div>
                </th>
                <th>
                    <div>Пользователь</div>
                </th>
                <th>
                    <div class="text-wrap">Дата поступления заказа</div>
                </th>
                <th>
                    <div>Услуга</div>
                </th>
            </tr>
            </thead>
            <tbody>
            <#list services as service>
                <tr>
                    <td>
                        <#if service.active == false>
                            <span class="badge badge-success">выполнен</span>
                        <#else>
                            <form method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <input type="hidden" name="serviceId" value="${service.id}">
                                <button type="submit" class="badge badge-secondary">не выполнен</button>
                            </form>
                        </#if>
                    </td>
                    <td>${service.id}</td>
                    <td>${service.user.email}</td>
                    <td>${service.date}</td>
                    <td>${service.service.title}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <#if !orders??>
            <h3>Заявки отсутствуют</h3>
        </#if>
    </#if>
</@body.body>