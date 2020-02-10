<#import "parts/body.ftlh" as body>

<@body.body>
    <div>
        <h2>История</h2>
        <div>
            <form name="historyForm" method="get" action="/history">
            <input type="date" name="date" value="${date}">
            <select name="sourceCurrency">
                <#list currencyList as currency>
                    <option name="currency" value="${currency.charCode}" <#if sourceCurrency?? && sourceCurrency==currency.charCode>selected</#if>>${currency.charCode} (${currency.name})</option>
                </#list>
            </select>
            <select name="targetCurrency">
                <#list currencyList as currency>
                    <option name="currency" value="${currency.charCode}" <#if targetCurrency?? && targetCurrency==currency.charCode>selected</#if>>${currency.charCode} (${currency.name})</option>
                </#list>
            </select>
            <input type="submit" value="Поиск"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Исходная валюта</th>
                    <th>Целевая валюта</th>
                    <th>Исходная сумма</th>
                    <th>Получаемая сумма</th>
                    <th>Дата</th>
                </tr>
            </thead>
            <tbody>
            <#list historyList as history>
                <tr>
                    <td>${history.sourceCurrency.name}</td>
                    <td>${history.targetCurrency.name}</td>
                    <td>${history.sourceCurrencyAmount}</td>
                    <td>${history.targetCurrencyAmount}</td>
                    <td>${history.date}</td>
                </tr>
                <#else>
                <h2>Истории по заданным параметрам не найдено.</h2>
            </#list>
            </tbody>
        </table>
    </div>
</@body.body>