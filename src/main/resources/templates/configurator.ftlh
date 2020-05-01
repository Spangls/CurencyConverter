<#import "parts/body.ftlh" as body>

<@body.body>
    <form>
        <select id="case" name="case" onchange="refresh()">
            <option value="-1">Не выбрано</option>
            <#list caseList as case>
                <option name="case" value="${case.id}">${case.item.name}</option>
            </#list>
        </select>
        <select id="cpu" name="cpu" onchange="refresh()">
            <option value="-1">Не выбрано</option>
            <#list cpuList as cpu>
                <option name="cpu" value="${cpu.id}">${cpu.item.name}</option>
            </#list>
        </select>
        <select id="gpu" name="gpu" onchange="refresh()">
            <option value="-1">Не выбрано</option>
            <#list gpuList as gpu>
                <option name="gpu" value="${gpu.id}">${gpu.item.name}</option>
            </#list>
        </select>
        <select id="hd" name="hd" onchange="refresh()">
            <option value="-1">Не выбрано</option>
            <#list hdList as hd>
                <option name="hd" value="${hd.id}">${hd.item.name}</option>
            </#list>
        </select>
        <select id="mb" name="mb" onchange="refresh()">
            <option name="mb" value="-1">Не выбрано</option>
            <#list mbList as mb>
                <option name="mb" value="${mb.id}">${mb.item.name}</option>
            </#list>
        </select>
        <select id="ps" name="ps" onchange="refresh()">
            <option name="ps" value="-1">Не выбрано</option>
            <#list psList as ps>
                <option name="ps" value="${ps.id}">${ps.item.name}</option>
            </#list>
        </select>
        <select id="ram" name="ram" onchange="refresh()">
            <option value="-1">Не выбрано</option>
            <#list ramList as ram>
                <option name="ram" value="${ram.id}">${ram.item.name}</option>
            </#list>
        </select>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" name="change" value="0"/>
        <div id="price">
            <h3>Стоимость: 0.0</h3>
            <p>Корпус: 0.0</p>
            <p>Процессор: 0.0</p>
            <p>Видеокарта: 0.0</p>
            <p>Накопитель информации: 0.0</p>
            <p>Материнская плата: 0.0</p>
            <p>Блок питания: 0.0</p>
            <p>Оперативная память: 0.0</p>
        </div>
    </form>
</@body.body>

<script type="text/javascript">
    $("select").focusout(
    function reloadStopper() {
        let change = $("input[name=change]");
        change.val(0);
        console.log('reload: '+change.val());
    });

    function reloadSelect(list, select, id) {
        $.each(list, function (id, entry) {
            select.append($('<option>', {value: entry.id, text: entry.item.name}))
        })
        select.val(id);
    }

        function refresh() {
            let change = $("input[name=change]");
            if (change.val() == 1) {
                return false;
            }
            change.val(1);
            let _csrf = $('input[name=_csrf]').val(),
                caseId = $('#case').val(),
                cpuId = $('#cpu').val(),
                gpuId = $('#gpu').val(),
                hdId = $('#hd').val(),
                mbId = $('#mb').val(),
                psId = $('#ps').val(),
                ramId = $('#ram').val();

            $.ajax({
                type: 'POST',
                url: "/configurator",
                data: {
                    caseId: caseId,
                    cpuId: cpuId,
                    gpuId: gpuId,
                    hdId: hdId,
                    mbId: mbId,
                    psId: psId,
                    ramId: ramId,
                    _csrf: _csrf,
                },
                success: function (json) {
                    $("select").each(function () {
                        $(this).empty();
                        $(this).append("<option value=\"-1\">Не выбрано</option>");
                    })
                    // console.log(JSON.stringify(json, null, 2))
                    reloadSelect(json.caseList, $('#case'), caseId);
                    reloadSelect(json.cpuList, $('#cpu'), cpuId);
                    reloadSelect(json.gpuList, $('#gpu'), gpuId);
                    reloadSelect(json.hdList, $('#hd'), hdId);
                    reloadSelect(json.mbList, $('#mb'), mbId);
                    reloadSelect(json.psList, $('#ps'), psId);
                    reloadSelect(json.ramList, $('#ram'), ramId);
                    $("#price").empty().append(json.price);
                },
                error: function (error) {
                    alert(JSON.stringify(error));
                }
            });
        }
</script>