<#import "parts/body.ftlh" as body>

<@body.body>
<h2>Регистрация товара</h2>
<p id="message"><#if message??>${message}</#if></p>
<form>
    <table style="border: 2px black solid">
        <tbody>
        <tr>
            <td><label for="name">Наименование</label></td>
            <td><input type="text" id="name" name="name" value="<#if item??>${item.name}</#if>"/></td>
        </tr>
        <tr>
            <td><label for="manufacturer">Производитель</label></td>
            <td>
                <select id="manufacturer" name="manufacturer">
                    <option value="-1">Не выбрано</option>
                    <#if manufacturerList??>
                        <#list manufacturerList as manufacturer>
                            <option name="manufacturer" value="${manufacturer.id}" <#if item?? && item.manufacturer.id == manufacturer.id>selected</#if>>${manufacturer.title}</option>
                        </#list>
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="price">Стоимость</label></td>
            <td><input type="number" id="price" name="price" value="<#if item??>${item.price?string("##0.00")}<#else>0</#if>" step="0.01"/></td>
        </tr>
        <tr>
            <td><label for="itemType">Тип товара</label></td>
            <td>
                <select id="itemType" name="itemType" onchange="switchType()">
                    <option name="itemType" value="-1">Не выбрано</option>
                    <#if itemTypeList??>
                        <#list itemTypeList as type>
                            <option name="type" value="${type}" <#if item?? && item.type == type>selected</#if>>${type.title}</option>
                        </#list>
                    </#if>
                </select>
            </td>
        </tr>
        <#if item??>
            <tr>
                <td><label for="item-id">Id</label></td>
                <td><input type="text" readonly id="item-id" value="${item.id}"></td>
            </tr>
        </#if>
        </tbody>
    </table>
    <table id="editable">
        <tbody id="edit-cpu" >
        <tr>
            <td><label for="cpu-cores">Ядер</label></td>
            <td><input type="number" id="cpu-cores" name="cpu-cores" value="<#if cpu??>${cpu.cores}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="cpu-flows">Потоков</label></td>
            <td><input type="number" id="cpu-flows" name="cpu-flows" value="<#if cpu??>${cpu.flows}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="cpu-frequency">Частота, ГГц</label></td>
            <td><input type="number" id="cpu-frequency" name="cpu-frequency" step="0.01" value="<#if cpu??>${cpu.frequency?string("##0.00")}<#else>0.00</#if>"></td>
        </tr>
        <tr>
            <td><label for="cpu-socket">Сокет</label></td>
            <td>
                <select id="cpu-socket" name="cpu-socket">
                    <#list socketList as socket>
                        <option value="${socket}" <#if cpu??  && cpu.socket == socket>selected</#if>>${socket.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <#if cpu??>
            <tr>
                <td><label for="cpu-id">Id</label></td>
                <td><input type="text" readonly id="cpu-id" value="${cpu.id}"></td>
            </tr>
        </#if>
        </tbody>
        <tbody id="edit-cs" >
        <tr>
            <td><label for="cs-type">Тип</label></td>
            <td>
                <select id="cs-type" name="cs-type">
                    <#list coolingSystemTypeList as type>
                        <option value="${type}" <#if cs?? && cs.type == type>selected</#if>>${type.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="cs-fan-count">Вентиляторов</label></td>
            <td><input type="number" id="cs-fan-count" name="cs-fan-count" value="<#if cs??>${cs.fanCount}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="cs-fan-diam">Диаметр, мм</label></td>
            <td><input type="number" id="cs-fan-diam" name="cs-fan-diam" value="<#if cs??>${cs.fanDiameter}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="cs-socket">Сокет</label></td>
            <td>
                <select id="cs-socket" name="cs-socket">
                    <#list socketList as socket>
                        <option value="${socket}" <#if cs?? && cs.socket == socket>selected</#if>>${socket.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <#if cs??>
            <tr>
                <td><label for="cs-id">Id</label></td>
                <td><input type="text" readonly id="cs-id" value="${cs.id}"></td>
            </tr>
        </#if>
        </tbody>
        <tbody id="edit-case" >
        <tr>
            <td><label for="case-form-factor">Типоразмер</label></td>
            <td>
                <select id="case-form-factor" name="case-form-factor">
                    <#list caseFormFactorList as ff>
                        <option value="${ff}" <#if case?? && case.caseFormFactor == ff>selected</#if>>${ff.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="case-mb-form-factor">Форм-фактор</label></td>
            <td>
                <select id="case-mb-form-factor" name="case-mb-form-factor">
                    <#list motherboardFormFactorList as ff>
                        <option value="${ff}" <#if case?? && case.motherboardFormFactor == ff>selected</#if>>${ff.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="case-window">Боковая панель</label></td>
            <td>
                <select id="case-window" name="case-window">
                    <#list caseWindowList as window>
                        <option value="${window}" <#if case?? && case.window == window>selected</#if>>${window.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <#if case??>
            <tr>
                <td><label for="case-id">Id</label></td>
                <td><input type="text" readonly id="case-id" value="${case.id}"></td>
            </tr>
        </#if>
        </tbody>
        <tbody id="edit-gpu" >
        <tr>
            <td><label for="gpu-vram">Объем памяти, ГБ</label></td>
            <td><input type="number" id="gpu-vram" name="gpu-vram" step="0.1" value="<#if gpu??>${gpu.vram?string("##0.0")}<#else>0.0</#if>"></td>
        </tr>
        <#if gpu??>
            <tr>
                <td><label for="gpu-id">Id</label></td>
                <td><input type="text" readonly id="gpu-id" value="${gpu.id}"></td>
            </tr>
        </#if>
        </tbody>
        <tbody id="edit-hd" >
        <tr>
            <td><label for="hd-type">Тип</label></td>
            <td>
                <select id="hd-type" name="hd-type">
                    <#list hardDriveTypeList as type>
                        <option value="${type}" <#if hd?? && hd.type == type>selected</#if>>${type.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="hd-form-factor">Типоразмер</label></td>
            <td>
                <select id="hd-form-factor" name="hd-form-factor">
                    <#list hardDriveFormFactorList as ff>
                        <option value="${ff}" <#if hd?? && hd.formFactor == ff>selected</#if>>${ff.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="hd-capacity">Объем памяти, ГБ</label></td>
            <td><input type="number" id="hd-capacity" name="hd-capacity" value="<#if hd??>${hd.capacity?string("##0.0")}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="hd-speed">Скорость работы, МБ/с</label></td>
            <td><input type="number" id="hd-speed" name="hd-speed" value="<#if hd??>${hd.rwSpeed}<#else>0</#if>"></td>
        </tr>
        <#if hd??>
            <tr>
                <td><label for="hd-id">Id</label></td>
                <td><input type="text" readonly id="hd-id" value="${hd.id}"></td>
            </tr>
        </#if>
        </tbody>
        <tbody id="edit-mb" >
        <tr>
            <td><label for="mb-socket">Сокет</label></td>
            <td>
                <select id="mb-socket" name="mb-socket">
                    <#list socketList as socket>
                        <option value="${socket}" <#if mb?? && mb.socket == socket>selected</#if>>${socket.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="mb-form-factor">Форм-фактор</label></td>
            <td>
                <select id="mb-form-factor" name="mb-form-factor">
                    <#list motherboardFormFactorList as ff>
                        <option value="${ff}" <#if mb?? && mb.formFactor == ff>selected</#if>>${ff.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="mb-chipset">Чипсет</label></td>
            <td>
                <select id="mb-chipset" name="mb-chipset">
                    <#list chipsetList as chipset>
                        <option value="${chipset}" <#if mb?? && mb.chipset == chipset>selected</#if>>${chipset.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="mb-ram-tech">Тип ОЗУ</label></td>
            <td>
                <select id="mb-ram-tech" name="mb-ram-tech">
                    <#list ramTechList as ramTech>
                        <option value="${ramTech}" <#if mb?? && mb.ramTech == ramTech>selected</#if>>${ramTech.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="mb-ram-count">Кол-во слотов ОЗУ</label></td>
            <td><input type="number" id="mb-ram-count" name="mb-ram-count" value="<#if mb??>${mb.num_of_ram}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="mb-port">Порты</label></td>
            <td><input type="text" id="mb-port" name="mb-port" value="<#if mb?? && mb.ports??>${mb.ports}<#else></#if>"></td>
        </tr>
        <#if mb??>
            <tr>
                <td><label for="mb-id">Id</label></td>
                <td><input type="text" readonly id="mb-id" value="${mb.id}"></td>
            </tr>
        </#if>
        </tbody>
        <tbody id="edit-ps" >
        <tr>
            <td><label for="ps-power">Мощность, Вт</label></td>
            <td><input type="number" id="ps-power" name="ps-power" value="<#if ps??>${ps.power}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="ps-sata">Кол-во Sata</label></td>
            <td><input type="number" id="ps-sata" name="ps-sata" value="<#if ps??>${ps.sataCount}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="ps-pcie6">Кол-во PCIe-6</label></td>
            <td><input type="number" id="ps-pcie6" name="ps-pcie6" value="<#if ps??>${ps.pcie6Count}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="ps-pcie8">Кол-во PCIe-8</label></td>
            <td><input type="number" id="ps-pcie8" name="ps-pcie8" value="<#if ps??>${ps.pcie8Count}<#else>0</#if>"></td>
        </tr>
        <#if ps??>
            <tr>
                <td><label for="ps-id">Id</label></td>
                <td><input type="text" readonly id="ps-id" value="${ps.id}"></td>
            </tr>
        </#if>
        </tbody>
        <tbody id="edit-ram" >
        <tr>
            <td><label for="ram-tech">Тип</label></td>
            <td>
                <select id="ram-tech" name="ram-tech">
                    <#list ramTechList as tech>
                        <option value="${tech}" <#if ram?? && ram.ramTech == tech>selected</#if>>${tech.title}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="ram-capacity">Объем памяти, ГБ</label></td>
            <td><input type="number" id="ram-capacity" name="ram-capacity" value="<#if ram??>${ram.capacity?string}<#else>0</#if>"></td>
        </tr>
        <tr>
            <td><label for="ram-frequency">Частота, МГц</label></td>
            <td><input type="number" id="ram-frequency" name="ram-frequency" value="<#if ram??>${ram.frequency?string}<#else>0</#if>"></td>
        </tr>
        <#if ram??>
            <tr>
                <td><label for="ram-id">Id</label></td>
                <td><input type="text" readonly id="ram-id" value="${ram.id}"></td>
            </tr>
        </#if>
        </tbody>
    </table>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div id="submits">
        <input id="buttonCpu" type="button" value="Сохранить"  onclick="eraseMessage(); save(saveCpu())"/>
        <input id="buttonCs" type="button" value="Сохранить"  onclick="eraseMessage(); save(saveCs())"/>
        <input id="buttonCase" type="button" value="Сохранить"  onclick="eraseMessage(); save(saveCase())"/>
        <input id="buttonGpu" type="button" value="Сохранить"  onclick="eraseMessage(); save(saveGpu())"/>
        <input id="buttonMb" type="button" value="Сохранить"  onclick="eraseMessage(); save(saveMb())"/>
        <input id="buttonPs" type="button" value="Сохранить"  onclick="eraseMessage(); save(savePs())"/>
        <input id="buttonRam" type="button" value="Сохранить"  onclick="eraseMessage(); save(saveRam())"/>
        <input id="buttonHd" type="button" value="Сохранить"  onclick="eraseMessage(); save(saveHd())"/>
    </div>
</form>
</@body.body>
