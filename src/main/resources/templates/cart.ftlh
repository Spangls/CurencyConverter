<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Корзина</h2>
    <#if cart??>
        <#if cart.itemsCount == 0>
            <p>Ваша корзина пуста.</p>
        </#if>
        <#if cart.itemsCount == 1>
            <p>В вашей корзине находится один товар.</p>
        </#if>
        <#if cart.itemsCount gt 1>
            <p>В вашей корзине находится ${cart.itemsCount} товаров.</p>
        </#if>
    </#if>


    <div class="row justify-content-center">
        <div class="col-md-4">
            <#if cart?? && cart.itemsCount != 0>
                <form method="post" action="/cart/clear">
                    <button type="submit" class="btn btn-secondary">
                        очистить корзину
                    </button>
                </form>
            </#if>
        </div>
        <div class="col-md-4">
            <#if cart?? && cart.itemsCount != 0>
                <a class="btn btn-secondary text-light" href="/checkout/details">оформить заказ</a>
            </#if>
        </div>
    </div>
    <#if cart??>
        <div class="table">
            <table class="table" width="100%">
                <thead>
                <tr>
                    <th>товар</th>
                    <th>цена</th>
                    <th>количество</th>
                    <th align="center">общая цена</th>
                </tr>
                </thead>
                <tbody>
                <#list cart.cartItems as cartItem>
                    <#assign item = itemsById[cartItem.itemId?string]>
                    <tr>
                        <td>${item.manufacturer.title} ${item.name}</td>
                        <td>${item.price} руб.</td>
                        <td width="125">
                            <form  method="post" name="cartItem">
                                <input type="hidden" name="itemId" value="${item.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <div class="input-group input-group-sm">
                                    <input type="number" name="quantity" class="form-control"
                                           min="0" max="${item.count}"
                                           value="${cartItem.quantity}"
                                           maxlength="2" size="1">
                                    <span class="input-group-btn">
                                    <button type="submit" class="btn btn-default quantity-button">
                                        изменить
                                    </button>
                                </span>
                                </div>
                            </form>
                        </td>
                        <td>${cartItem.quantity*item.price} руб.</td>
                    </tr>
                </#list>
                <tr>
                    <td colspan="3" align="right"><h4>Итог:</h4></td>
                    <td><h4>${cart.totalCost} руб.</h4></td>
                </tr>
                </tbody>
            </table>
        </div>
    </#if>
</@body.body>