<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">MyPC</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <#if name??>
                <li class="nav-item">
                    <a class="nav-link" href="/items">Товары</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/service">Услуги</a>
                </li>
                <#if isEmployee || isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/manufacturer">Производители</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/employee/order">Заказы</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/user">Пользователи</a>
                    </li>
                </#if>
            </#if>
        </ul>
        <#if name??>
            <nav class="navbar navbar-light bg-light">
                <a class="nav-link" href="/cart">Корзина</a>
            </nav>
            <div class="navbar-text"><a href="/user/edit">${name}</a></div>
            <nav class="navbar navbar-light bg-light">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-link">Выход</button>
                </form>
            </nav>
        <#else>
            <nav class="navbar navbar-light bg-light">
                <a class="nav-link" href="/login">Вход</a>
            </nav>
        </#if>
    </div>
</nav>