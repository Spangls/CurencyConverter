<#import "parts/body.ftlh" as body>
<#import "parts/filters.ftlh" as filters>
<#include "parts/security.ftlh">

<@body.body>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
    <@security.authorize access="hasAuthority('EMPLOYEE')">
        <div class="row mb-1">
            <div class="col">
                <a class="btn btn-primary" href="/items/new">Регистрация товара</a>
            </div>
        </div>
    </@security.authorize>
    <div class="row justify-content-around">
        <div><a class="btn btn-primary" href="/items">Всё</a></div>
        <div onclick="switchFilters(this)" id="switch-cpu"><p class="btn btn-primary">Процессор</p></div>
        <div onclick="switchFilters(this)" id="switch-cs"><p class="btn btn-primary">Стистема охлаждения</p></div>
        <div onclick="switchFilters(this)" id="switch-mb"><p class="btn btn-primary">Материнская плата</p></div>
        <div onclick="switchFilters(this)" id="switch-ram"><p class="btn btn-primary">ОЗУ</p></div>
        <div onclick="switchFilters(this)" id="switch-gpu"><p class="btn btn-primary">Видеокарта</p></div>
        <div onclick="switchFilters(this)" id="switch-ps"><p class="btn btn-primary">Блок питания</p></div>
        <div onclick="switchFilters(this)" id="switch-case"><p class="btn btn-primary">Корпус</p></div>
        <div onclick="switchFilters(this)" id="switch-hd"><p class="btn btn-primary">Хранилище данных</p></div>

    </div>
    <div id="items-filter" class="row justify-content-around">
        <input type="hidden" name="_csrf" value="${_csrf.token}" id="csrf"/>
        <input type="hidden" name="isEmployee" value="<#if isEmployee>true<#else>false</#if>" id="isEmployee"/>
        <input type="hidden" id="items-switcher" value="common"/>
        <@filters.common/>
        <@filters.cpu/>
        <@filters.coolingSystem/>
        <@filters.case/>
        <@filters.gpu/>
        <@filters.hardDrive/>
        <@filters.motherboard/>
        <@filters.powerSupply/>
        <@filters.ram/>
    </div>
    <br>
    <div id="items-body">
        <div id="items-result" class="row table">
            <table>
                <thead>
                <tr id="characteristics-header">
                    <td>Слежение</td>
                    <td></td>
                    <td>Наименование</td>
                    <td>Производитель</td>
                    <td>Цена</td>
                    <td>Количество</td>
                    <@security.authorize access="hasAuthority('EMPLOYEE')">
                        <td>Действия</td>
                        <td>Поставки</td>
                    </@security.authorize>
                    <td class="filter-cpu toHide">Ядер</td>
                    <td class="filter-cpu toHide">Потоков</td>
                    <td class="filter-cpu toHide">Частота работы, ГГц</td>
                    <td class="filter-cpu filter-cs toHide">Сокет</td>
                    <td class="filter-cs filter-ram filter-hd toHide" hidden>Тип</td>
                    <td class="filter-cs toHide">Диаметр Вентиляторов, мм</td>
                    <td class="filter-cs toHide">Кол-во вентиляторов</td>
                    <td class="filter-case filter-hd toHide">Типоразмер</td>
                    <td class="filter-case filter-mb toHide">Форм-фактор</td>
                    <td class="filter-case toHide">Материал бокового окна</td>
                    <td class="filter-mb toHide">Сокет</td>
                    <td class="filter-mb toHide">Тип ОЗУ</td>
                    <td class="filter-mb toHide">Чипсет</td>
                    <td class="filter-mb toHide">Слотов под ОЗУ</td>
                    <td class="filter-ram filter-gpu filter-hd toHide">Объем памяти, ГБ</td>
                    <td class="filter-ram toHide">Частота, МГц</td>
                    <td class="filter-ps toHide">Мощность, Вт</td>
                    <td class="filter-ps toHide">Кол-во SATA</td>
                    <td class="filter-ps toHide">Кол-во PCIe-6</td>
                    <td class="filter-ps toHide">Кол-во PCIe-8</td>
                    <td class="filter-hd toHide">Скорость работы, МБ/с</td>
                </tr>
                </thead>
                <tbody id="item-body">
                <#list items as item>
                    <tr>
                        <td>
                            <div class="row justify-content-center">
                                <form method="post" id="follow-form" action="/items/follow">
                                    <input type="hidden" name="itemId" value="${item.id}">
                                    <input name="follow" class="form-control follow-checkbox" type="checkbox" id="item-follow-checkbox" <#if item.followed>checked</#if>>
                                </form>
                            </div>
                        </td>
                        <td>
                            <div class="row justify-content-center">
                                <form method="post" id="quantity-form" action="/cart">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <input type="hidden" name="itemId" value="${item.id}">
                                        <input type="number" name="quantity" value="1" min="1" max="${item.count}"
                                               maxlength="2" class="col form-control">
                                        <button type="submit" class="col btn btn-secondary btn-sm quantity-button">+</button>
                                </form>
                            </div>
                        </td>
                        <td><a href="/item/${item.id}">${item.name}</a></td>
                        <td>${item.manufacturer.title}</td>
                        <td>${item.price}</td>
                        <td>${item.count}</td>
                        <@security.authorize access="hasAuthority('EMPLOYEE')">
                            <td><a href="/items/edit/${item.id}" class="btn btn-secondary btn-sm">Изменить</a></td>
                            <td>
                                <form method="post" action="/items/count/add">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <input type="hidden" name="itemId" value="${item.id}">
                                    <input type="number" name="count" placeholder="Количество" class="w-50">
                                    <input type="submit" value="Добавить" class="btn btn-secondary btn-sm">
                                </form>
                            </td>
                        </@security.authorize>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@body.body>