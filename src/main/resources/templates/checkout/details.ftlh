<#import "../parts/body.ftlh" as body>

<@body.body>
    <h1>Проверка заказа</h1>
    <p>Пожалуйста, проверьте еще раз позиции заказа.</p>

    <div class="clearfix">
        <div class="col-md-8 p-0">
            <div class="panel panel-default mr-20">
                <div class="panel-heading">Позиции заказа</div>
                <table class="table table-small-text w-100">
                    <thead>
                    <tr>
                        <td>наименование</td>
                        <td>цена за ед.</td>
                        <td>кол-во</td>
                        <td>цена</td>
                    </tr>
                    </thead>
                    <tbody>
                    <#list cart.cartItems as cartItem>
                        <#assign item = itemsById[cartItem.itemId?string]>
                        <tr>
                            <td>${item.manufacturer.title} ${item.name}</td>
                            <td>${item.price}</td>
                            <td>${cartItem.quantity}</td>
                            <td>${cartItem.quantity*item.price}</td>
                        </tr>
                    </#list>
                    <tr>
                        <td colspan="3" align="right"><h4>сумма к оплате:</h4></td>
                        <td><h4>${cart.totalCost} руб.</h4></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <br>
    <div class="form-group row">
        <div class="col-sm-4">
            <a href="/cart" class="btn btn-default pull-right" style="margin-right: -25px;">
                вернуться к корзине
            </a>
        </div>
        <div class="col-sm-5">
            <form action="/checkout/confirmation" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">
                    оплатить
                </button>
            </form>
        </div>
    </div>
</@body.body>