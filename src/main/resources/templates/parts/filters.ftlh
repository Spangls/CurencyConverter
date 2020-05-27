<#macro common>
    <div id="filter-common">
        <div  class="filter">
            <p>
                <label for="common-price">Цена: </label>
                <input type="text" readonly name="common-price" id="common-price" class="range-input">
            </p>
            <div id="common-price-range"></div>
        </div>
    </div>
</#macro>
<#macro cpu>
    <div id="filter-cpu">
            <form name="cpu">
                <div  class="filter col">
                    <p>
                        <label for="cpu-cores">Ядер: </label>
                        <input type="text" readonly name="cpu-cores" id="cpu-cores" class="range-input">
                    </p>
                    <div id="cpu-cores-range"></div>
                </div>
                <div class="filter col">
                    <p>
                        <label for="cpu-flows">Потоков: </label>
                        <input type="text" readonly name="cpu-flows" id="cpu-flows" class="range-input">
                    </p>
                    <div id="cpu-flows-range"></div>
                </div>
                <div  class="filter col">
                    <p>
                        <label for="cpu-frequency">Частота, ГГц: </label>
                        <input type="text" readonly name="cpu-frequency" id="cpu-frequency" class="range-input">
                    </p>
                    <div id="cpu-frequency-range"></div>
                </div>
                <div  class="filter col">
                    <label for="cpu-socket">Сокет:</label>
                    <select id="cpu-socket" name="socket"  class="range-input" onchange="filterCpu()">
                        <option value="-1">Не выбрано</option>
                        <#list sockets as socket>
                            <option value="${socket}">${socket.title}</option>
                        </#list>
                    </select>
                </div>
            </form>
        </div>
</#macro>
<#macro coolingSystem>
    <div id="filter-cs">
        <form name="cooling-system">
            <div  class="filter col">
                <label for="cs-type">Тип:</label>
                <select id="cs-type" name="csType"  class="range-input" onchange="filterCs()">
                    <option value="-1">Не выбрано</option>
                    <#list coolingSystemTypes as type>
                        <option value="${type}">${type.title}</option>
                    </#list>
                </select>
            </div>
            <div  class="filter col">
                <p>
                    <label for="cs-fan-diam">Диаметр вентиляторов, мм: </label>
                    <input type="text" readonly name="cs-fan-diam" id="cs-fan-diam" class="range-input">
                </p>
                <div id="cs-fan-diam-range"></div>
            </div>
            <div  class="filter col">
                <p>
                    <label for="cs-fan-count">Количество вентиляторов: </label>
                    <input type="text" readonly name="cs-fan-count" id="cs-fan-count" class="range-input">
                </p>
                <div id="cs-fan-count-range"></div>
            </div>
            <div  class="filter  col">
                <label for="cs-socket">Сокет:</label>
                <select id="cs-socket" name="socket"  class="range-input" onchange="filterCs()">
                    <option value="-1">Не выбрано</option>
                    <#list sockets as socket>
                        <option value="${socket}">${socket.title}</option>
                    </#list>
                </select>
            </div>
        </form>
    </div>
</#macro>
<#macro case>
    <div id="filter-case">
        <form name="case">
            <div  class="filter  col">
                <label for="cse-ff">Типоразмер:<br/></label>
                <select id="cse-ff" name="cse-ff"  class="range-input" onchange="filterCse()">
                    <option value="-1">Не выбрано</option>
                    <#list caseFormFactors as cff>
                        <option value="${cff}">${cff.title}</option>
                    </#list>
                </select>
            </div>
            <div  class="filter  col">
                <label for="cse-mff">Форм-фактор:<br/></label>
                <select id="cse-mff" name="cse-mff"  class="cse-ff" onchange="filterCse()">
                    <option value="-1">Не выбрано</option>
                    <#list motherboardFormFactors as mff>
                        <option value="${mff}">${mff.title}</option>
                    </#list>
                </select>
            </div>
            <div  class="filter  col">
                <label for="cse-window">Боковая панель:<br/></label>
                <select id="cse-window" name="cse-Window"  class="range-input" onchange="filterCse()">
                    <option value="-1">Не выбрано</option>
                    <#list caseWindows as window>
                        <option value="${window}">${window.title}</option>
                    </#list>
                </select>
            </div>
        </form>
    </div>
</#macro>
<#macro gpu>
    <div id="filter-gpu">
        <form name="gpu">
            <div  class="filter  col">
                <p>
                    <label for="gpu-vram">Объем памяти, ГБ: </label>
                    <input type="text" readonly name="gpu-vram" id="gpu-vram" class="range-input">
                </p>
                <div id="gpu-vram-range"></div>
            </div>
        </form>
    </div>
</#macro>
<#macro hardDrive>
    <div id="filter-hd">
        <form name="hd">
            <div  class="filter  col">
                <label for="hd-ff">Типоразмер:</label>
                <select id="hd-ff" name="hd-ff"  class="range-input" onchange="filterHd()">
                    <option value="-1">Не выбрано</option>
                    <#list hardDriveFormFactors as hdff>
                        <option value="${hdff}">${hdff.title}</option>
                    </#list>
                </select>
            </div>
            <div  class="filter  col">
                <p>
                    <label for="hd-capacity">Объем, ГБ: </label>
                    <input type="text" readonly name="hd-capacity" id="hd-capacity" class="range-input">
                </p>
                <div id="hd-capacity-range"></div>
            </div>
            <div  class="filter  col">
                <p>
                    <label for="hd-speed">Скорость чтения/записи, Мб/с: </label>
                    <input type="text" readonly name="hd-speed" id="hd-speed" class="range-input">
                </p>
                <div id="hd-speed-range"></div>
            </div>
            <div  class="filter  col">
                <label for="hd-type">Тип:</label>
                <select id="hd-type" name="hd-type"  class="range-input" onchange="filterHd()">
                    <option value="-1">Не выбрано</option>
                    <#list hardDriveTypes as type>
                        <option value="${type}">${type.title}</option>
                    </#list>
                </select>
            </div>
        </form>
    </div>
</#macro>
<#macro motherboard>
    <div id="filter-mb">
        <form name="mb">
            <div  class="filter  col">
                <label for="mb-socket">Сокет:</label>
                <select id="mb-socket" name="socket"  class="range-input" onchange="filterMb()">
                    <option value="-1">Не выбрано</option>
                    <#list sockets as socket>
                        <option value="${socket}">${socket.title}</option>
                    </#list>
                </select>
            </div>
            <div  class="filter  col">
                <label for="mb-ff">Форм-фактор:<br/></label>
                <select id="mb-ff" name="mb-ff"  class="mb-ff" onchange="filterMb()">
                    <option value="-1">Не выбрано</option>
                    <#list motherboardFormFactors as mff>
                        <option value="${mff}">${mff.title}</option>
                    </#list>
                </select>
            </div>
            <div  class="filter  col">
                <label for="mb-chipset">Чипсет:<br/></label>
                <select id="mb-chipset" name="mb-chipset"  class="mb-chipset" onchange="filterMb()">
                    <option value="-1">Не выбрано</option>
                    <#list chipsets as chip>
                        <option value="${chip}">${chip.title}</option>
                    </#list>
                </select>
            </div>
            <div  class="filter  col">
                <p>
                    <label for="mb-ram-count">Количество слотов для ОЗУ: </label>
                    <input type="text" readonly name="mb-ram-count" id="mb-ram-count" class="range-input">
                </p>
                <div id="mb-ram-count-range"></div>
            </div>
            <div  class="filter  col">
                <label for="mb-ram-tech">Тип ОЗУ:</label>
                <select id="mb-ram-tech" name="mb-ram-tech"  class="range-input" onchange="filterMb()">
                    <option value="-1">Не выбрано</option>
                    <#list ramTechs as tech>
                        <option value="${tech}">${tech.title}</option>
                    </#list>
                </select>
            </div>
        </form>
    </div>
</#macro>
<#macro powerSupply>
    <div id="filter-ps">
        <form name="ps">
            <div  class="filter  col">
                <p>
                    <label for="ps-power">Мощность, Вт: </label>
                    <input type="text" readonly name="ps-power" id="ps-power" class="range-input">
                </p>
                <div id="ps-power-range"></div>
            </div>
            <div  class="filter  col">
                <p>
                    <label for="ps-sata-count">Количество SATA: </label>
                    <input type="text" readonly name="ps-sata-count" id="ps-sata-count" class="range-input">
                </p>
                <div id="ps-sata-count-range"></div>
            </div>
            <div  class="filter  col">
                <p>
                    <label for="ps-pcie6-count">Количество PCIe-6: </label>
                    <input type="text" readonly name="ps-pcie6-count" id="ps-pcie6-count" class="range-input">
                </p>
                <div id="ps-pcie6-count-range"></div>
            </div>
            <div  class="filter  col">
                <p>
                    <label for="ps-pcie8-count">Количество PCIe-8: </label>
                    <input type="text" readonly name="ps-pcie8-count" id="ps-pcie8-count" class="range-input">
                </p>
                <div id="ps-pcie8-count-range"></div>
            </div>
        </form>
    </div>
</#macro>
<#macro ram>
    <div id="filter-ram">
        <form name="ram">
            <div  class="filter  col">
                <p>
                    <label for="ram-capacity">Объем, ГБ: </label>
                    <input type="text" readonly name="ram-capacity" id="ram-capacity" class="range-input">
                </p>
                <div id="ram-capacity-range"></div>
            </div>
            <div  class="filter  col">
                <p>
                    <label for="ram-frequency">Частота, МГц: </label>
                    <input type="text" readonly name="ram-frequency" id="ram-frequency" class="range-input">
                </p>
                <div id="ram-frequency-range"></div>
            </div>
            <div  class="filter  col">
                <label for="ram-tech">Тип:</label>
                <select id="ram-tech" name="ram-tech"  class="range-input" onchange="filterRam()">
                    <option value="-1">Не выбрано</option>
                    <#list ramTechs as tech>
                        <option value="${tech}">${tech.title}</option>
                    </#list>
                </select>
            </div>
        </form>
    </div>
</#macro>