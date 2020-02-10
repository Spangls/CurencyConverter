<#import "parts/body.ftlh" as body>

<@body.body>
    <h2>Конвертер</h2>
    <div>
        <form method="post" id="converterForm">
            <div>
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <select name="sourceCurrency" onchange="convert(this)">
                                <#list currencyList as currency>
                                    <option name="currency" value="${currency.charCode}">${currency.charCode} (${currency.name})</option>
                                </#list>
                            </select>
                        </td>
                        <td>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </td>
                        <td>
                            <select name="targetCurrency" onchange="convert(this)">
                                <#list currencyList as currency>
                                    <option name="currency" value="${currency.charCode}">${currency.charCode} (${currency.name})</option>
                                </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="number" name="sourceAmount" placeholder="Сумма"
                                   onclick="switchSourceAndTarget(this)" value="0" step="any" onchange="convert(this)"/>
                        </td>
                        <td>
                        </td>
                        <td>
                            <input type="number" name="targetAmount" placeholder="Сумма"
                                   onclick="switchSourceAndTarget(this)" value="0" step="any" onchange="convert(this)"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <script type="text/javascript">
            function convert(element) {
                var sourceCurrency, targetCurrency,
                    sourceAmount, switchCurrency, _csrf;
                if (element.name == "targetAmount" || element.name == "targetCurrency") {
                    sourceCurrency = $('select[name=targetCurrency]').val();
                    targetCurrency = $('select[name=sourceCurrency]').val();
                    sourceAmount = $('input[name=targetAmount]').val();
                    switchCurrency = true;
                } else if (element.name == "sourceAmount" || element.name == "sourceCurrency") {
                    sourceCurrency = $('select[name=sourceCurrency]').val();
                    targetCurrency = $('select[name=targetCurrency]').val();
                    sourceAmount = $('input[name=sourceAmount]').val();
                    switchCurrency = false;
                }
                _csrf = $('input[name=_csrf]').val();

                $.ajax({
                    type: 'POST',
                    url: "/converter",
                    data: {
                        sourceCurrency: sourceCurrency,
                        targetCurrency: targetCurrency,
                        sourceAmount: sourceAmount,
                        _csrf: _csrf,
                    },
                    success: function (data) {
                        if (switchCurrency) {
                            $('input[name=sourceAmount]').val(data);
                        }
                        else{
                            $('input[name=targetAmount]').val(data);
                        }
                    },
                    error: function (error) {
                        alert(JSON.stringify(error));
                    }
                });
            }
        </script>
    </div>
</@body.body>


