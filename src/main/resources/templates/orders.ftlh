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
    <div class="text text-danger"><#if message??>${message}</#if></div>
    <#if orders??>
        <table class="table">
            <thead>
            <tr>
                <th><div class="text-wrap">Статус</div></th>
                <th><div class="text-wrap">ID</div></th>
                <th><div>Пользователь</div></th>
                <th><div class="text-wrap">Дата поступления заказа</div></th>
                <th><div>Итоговая стоимость</div></th>
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
        <h3>Заказы отсутствуют</h3>
    </#if>

</@body.body>