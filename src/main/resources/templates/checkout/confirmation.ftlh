<#import "../parts/body.ftlh" as body>

<@body.body>
    <#if createdOrder??>
        <h1>Ваш заказ подтверждён</h1>
        <div class="clearfix">
            <div>
                <p><b>Ваш заказ будет готов к выдаче в течение 24 часов.</b></p>
                <p>Номер вашего заказа: ${createdOrder.id}</p>
                <#if user??>
                    <p>Чек выслан на электронную почту ${user.email}</p>
                </#if>
                <p>Благодарим за заказ в нашем магазине!</p>
            </div>
        </div>
    </#if>

    <div class="row justify-content-center">
        <a class="btn btn-primary" href="/cart">в магазин</a>
    </div>
</@body.body>