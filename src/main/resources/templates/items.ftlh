<#import "parts/body.ftlh" as body>
<#import "parts/filters.ftlh" as filters>

<@body.body>
    <div class="one-row">
        <div onclick="switchFilters(this)" id="switch-cpu"><p>Процессор</p></div>
        <div onclick="switchFilters(this)" id="switch-cs"><p>Стистема охлаждения</p></div>
        <div onclick="switchFilters(this)" id="switch-mb"><p>Материнская плата</p></div>
        <div onclick="switchFilters(this)" id="switch-ram"><p>ОЗУ</p></div>
        <div onclick="switchFilters(this)" id="switch-gpu"><p>Видеокарта</p></div>
        <div onclick="switchFilters(this)" id="switch-ps"><p>Блок питания</p></div>
        <div onclick="switchFilters(this)" id="switch-case"><p>Корпус</p></div>
        <div onclick="switchFilters(this)" id="switch-hd"><p>Хранилище данных</p></div>
    </div>
    <div id="items-body">
        <div id="items-filter">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
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
        <div id="items-result">
            <table>
                <thead>
                <tr id="characteristics-header">
                    <td>Наименование</td>
                    <td>Производитель</td>
                    <td>Цена</td>
                    <td>Количество</td>
                    <td class="filter-cpu toHide" hidden>Ядер</td>
                    <td class="filter-cpu toHide" hidden>Потоков</td>
                    <td class="filter-cpu toHide" hidden>Частота работы</td>
                    <td class="filter-cpu toHide" hidden>Сокет</td>
                </tr>
                </thead>
                <tbody id="item-body">
                <#list items as item>
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.manufacturer.title}</td>
                        <td>${item.price}</td>
                        <td>${item.count}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

</@body.body>