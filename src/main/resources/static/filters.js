$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e,xhr,options) {
        xhr.setRequestHeader(header, token);
    });
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    $('input.follow-checkbox').change(function () {
        let $this = $(this);
        let form = $this.parents('form:first');
        let url = form.attr('action');
        let jsonData = form.serializeObject();
        jsonData['follow'] = !!jsonData['follow'];
        $.ajax({
            type: "post",
            url: url,
            data: jsonData,
            success: function (data) {
                if (data == false)
                    alert("Что-то пошло не так.")
            },
            error: function (error) {
                console.log(JSON.stringify(error))
            }
        });
    });

    $('#characteristics-header > td[class*="toHide"]').hide();
    $("#items-filter").children().hide();
    $("#filter-common").show();
    setCommonPriceRange();
    setCpuCoresRange();
    setCpuFlowsRange();
    setCpuFrequencyRange();
    setCSFanDiamRange();
    setCSFanCountRange();
    setGpuVramRange();
    setHDCapacityRange();
    setHDSpeedRange();
    setMBRamCountRange();
    setPSPowerRange();
    setPSSataRange();
    setPSPCIE6Range();
    setPSPCIE8Range();
    setRamCapacityRange();
    setRamFrequencyRange();
});

function setCommonPriceRange() {
    $("#common-price-range").slider({
        range: true,
        min: 0,
        max: 500000,
        values: [0, 500000],
        step: 100,
        change: function (event, ui) {
            $("#common-price").val(ui.values[0] + " - " + ui.values[1]);
            let switcher = $("#items-switcher").val();
            switch (switcher) {
                case "cpu":
                    filterCpu();
                    break;
                case "cs":
                    filterCs();
                    break;
                case "case":
                    filterCse();
                    break;
                case "gpu":
                    filterGpu();
                    break;
                case "hd":
                    filterHd();
                    break;
                case "mb":
                    filterMb();
                    break;
                case "ps":
                    filterPs();
                    break;
                case "ram":
                    filterRam();
                    break;
            }
        }

    });
    $("#common-price").val($("#common-price-range").slider("values", 0) +
        " - " + $("#common-price-range").slider("values", 1));
}

function setCpuCoresRange() {
    $("#cpu-cores-range").slider({
        range: true,
        min: 1,
        max: 16,
        values: [1, 16],
        change: function (event, ui) {
            $("#cpu-cores").val(ui.values[0] + " - " + ui.values[1]);
            filterCpu();
        }

    });
    $("#cpu-cores").val($("#cpu-cores-range").slider("values", 0) +
        " - " + $("#cpu-cores-range").slider("values", 1));
}

function setCpuFlowsRange() {
    $("#cpu-flows-range").slider({
        range: true,
        min: 1,
        max: 32,
        values: [1, 32],
        change: function (event, ui) {
            $("#cpu-flows").val(ui.values[0] + " - " + ui.values[1]);
            filterCpu();
        }
    });
    $("#cpu-flows").val($("#cpu-flows-range").slider("values", 0) +
        " - " + $("#cpu-flows-range").slider("values", 1));
}

function setCpuFrequencyRange() {
    $("#cpu-frequency-range").slider({
        range: true,
        min: 0.1,
        max: 6,
        values: [0.1, 6],
        step: 0.1,
        change: function (event, ui) {
            $("#cpu-frequency").val(ui.values[0] + " - " + ui.values[1]);
            filterCpu();
        }

    });
    $("#cpu-frequency").val($("#cpu-frequency-range").slider("values", 0) +
        " - " + $("#cpu-frequency-range").slider("values", 1));
}

function setCSFanDiamRange() {
    $("#cs-fan-diam-range").slider({
        range: true,
        min: 40,
        max: 140,
        values: [40, 140],
        change: function (event, ui) {
            $("#cs-fan-diam").val(ui.values[0] + " - " + ui.values[1]);
            filterCs();
        }

    });
    $("#cs-fan-diam").val($("#cs-fan-diam-range").slider("values", 0) +
        " - " + $("#cs-fan-diam-range").slider("values", 1));
}

function setCSFanCountRange() {
    $("#cs-fan-count-range").slider({
        range: true,
        min: 0,
        max: 3,
        values: [0, 3],
        change: function (event, ui) {
            $("#cs-fan-count").val(ui.values[0] + " - " + ui.values[1]);
            filterCs();
        }

    });
    $("#cs-fan-count").val($("#cs-fan-count-range").slider("values", 0) +
        " - " + $("#cs-fan-count-range").slider("values", 1));
}

function setGpuVramRange() {
    $("#gpu-vram-range").slider({
        range: true,
        min: 0.5,
        max: 32,
        values: [0.5, 32],
        step: 0.5,
        change: function (event, ui) {
            $("#gpu-vram").val(ui.values[0] + " - " + ui.values[1]);
            filterGpu();
        }

    });
    $("#gpu-vram").val($("#gpu-vram-range").slider("values", 0) +
        " - " + $("#gpu-vram-range").slider("values", 1));
}

function setHDCapacityRange() {
    $("#hd-capacity-range").slider({
        range: true,
        min: 0,
        max: 16000,
        values: [0, 16000],
        step: 1000,
        change: function (event, ui) {
            $("#hd-capacity").val(ui.values[0] + " - " + ui.values[1]);
            filterHd();
        }

    });
    $("#hd-capacity").val($("#hd-capacity-range").slider("values", 0) +
        " - " + $("#hd-capacity-range").slider("values", 1));
}

function setHDSpeedRange() {
    $("#hd-speed-range").slider({
        range: true,
        min: 80,
        max: 5000,
        values: [80, 5000],
        step: 20,
        change: function (event, ui) {
            $("#hd-speed").val(ui.values[0] + " - " + ui.values[1]);
            filterHd();
        }

    });
    $("#hd-speed").val($("#hd-speed-range").slider("values", 0) +
        " - " + $("#hd-speed-range").slider("values", 1));
}

function setMBRamCountRange() {
    $("#mb-ram-count-range").slider({
        range: true,
        min: 1,
        max: 16,
        values: [1, 16],
        change: function (event, ui) {
            $("#mb-ram-count").val(ui.values[0] + " - " + ui.values[1]);
            filterMb();
        }

    });
    $("#mb-ram-count").val($("#mb-ram-count-range").slider("values", 0) +
        " - " + $("#mb-ram-count-range").slider("values", 1));
}

function setPSPowerRange() {
    $("#ps-power-range").slider({
        range: true,
        min: 150,
        max: 2000,
        values: [150, 2000],
        step: 50,
        change: function (event, ui) {
            $("#ps-power").val(ui.values[0] + " - " + ui.values[1]);
            filterPs();
        }

    });
    $("#ps-power").val($("#ps-power-range").slider("values", 0) +
        " - " + $("#ps-power-range").slider("values", 1));
}

function setPSSataRange() {
    $("#ps-sata-count-range").slider({
        range: true,
        min: 1,
        max: 8,
        values: [1, 8],
        change: function (event, ui) {
            $("#ps-sata-count").val(ui.values[0] + " - " + ui.values[1]);
            filterPs();
        }

    });
    $("#ps-sata-count").val($("#ps-sata-count-range").slider("values", 0) +
        " - " + $("#ps-sata-count-range").slider("values", 1));
}

function setPSPCIE6Range() {
    $("#ps-pcie6-count-range").slider({
        range: true,
        min: 1,
        max: 8,
        values: [1, 8],
        change: function (event, ui) {
            $("#ps-pcie6-count").val(ui.values[0] + " - " + ui.values[1]);
            filterPs();
        }

    });
    $("#ps-pcie6-count").val($("#ps-pcie6-count-range").slider("values", 0) +
        " - " + $("#ps-pcie6-count-range").slider("values", 1));
}

function setPSPCIE8Range() {
    $("#ps-pcie8-count-range").slider({
        range: true,
        min: 1,
        max: 8,
        values: [1, 8],
        change: function (event, ui) {
            $("#ps-pcie8-count").val(ui.values[0] + " - " + ui.values[1]);
            filterPs();
        }

    });
    $("#ps-pcie8-count").val($("#ps-pcie8-count-range").slider("values", 0) +
        " - " + $("#ps-pcie8-count-range").slider("values", 1));
}

function setRamCapacityRange() {
    $("#ram-capacity-range").slider({
        range: true,
        min: 1,
        max: 128,
        values: [1, 128],
        change: function (event, ui) {
            $("#ram-capacity").val(ui.values[0] + " - " + ui.values[1]);
            filterRam();
        }
    });
    $("#ram-capacity").val($("#ram-capacity-range").slider("values", 0) +
        " - " + $("#ram-capacity-range").slider("values", 1));
}

function setRamFrequencyRange() {
    $("#ram-frequency-range").slider({
        range: true,
        min: 800,
        max: 4800,
        values: [800, 4800],
        step: 100,
        change: function (event, ui) {
            $("#ram-frequency").val(ui.values[0] + " - " + ui.values[1]);
            filterRam();
        }
    });
    $("#ram-frequency").val($("#ram-frequency-range").slider("values", 0) +
        " - " + $("#ram-frequency-range").slider("values", 1));
}


function filterCpu() {
    $.ajax({
        type: 'POST',
        url: "/items/cpu",
        data: {
            _csrf: $("input[name=_csrf]").val(),
            prices: $("#common-price").val(),
            cores: $("#cpu-cores").val(),
            flows: $("#cpu-flows").val(),
            frequency: $("#cpu-frequency").val(),
            socket: $("#cpu-socket").val()
        },
        success: function (data) {
            $("#item-body").empty();
            $.each(data, function (id, entry) {
                $("#item-body").append(
                    "<tr>" +
                    commonTableParams(entry.item) +
                    "<td>" + entry.cores + "</td>" +
                    "<td>" + entry.flows + "</td>" +
                    "<td>" + entry.frequency + "</td>" +
                    "<td>" + entry.socketTitle + "</td>" +
                    "</tr>")
            });
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function filterCs() {
    $.ajax({
        type: 'POST',
        url: "/items/cs",
        data: {
            _csrf: $("input[name=_csrf]").val(),
            prices: $("#common-price").val(),
            type: $("#cs-type").val(),
            diam: $("#cs-fan-diam").val(),
            count: $("#cs-fan-count").val(),
            socket: $("#cs-socket").val()
        },
        success: function (data) {
            $("#item-body").empty();
            $.each(data, function (id, entry) {
                $("#item-body").append(
                    "<tr>" +
                    commonTableParams(entry.item) +
                    "<td>" +
                    entry.socketTitle + "</td><td>" +
                    entry.typeTitle + "</td><td>" +
                    entry.fanDiameter + "</td><td>" +
                    entry.fanCount + "</td></tr>")
            });
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function filterCse() {
    $.ajax({
        type: 'POST',
        url: "/items/case",
        data: {
            _csrf: $("input[name=_csrf]").val(),
            prices: $("#common-price").val(),
            caseFF: $("#cse-ff").val(),
            mbFF: $("#cse-mff").val(),
            window: $("#cse-window").val()
        },
        success: function (data) {
            $("#item-body").empty();
            $.each(data, function (id, entry) {
                $("#item-body").append(
                    "<tr>" +
                    commonTableParams(entry.item) +
                    "<td>" +
                    entry.cffTitle + "</td><td>" +
                    entry.mffTitle + "</td><td>" +
                    entry.windowTitle + "</td></tr>")
            });
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function filterGpu() {
    $.ajax({
        type: 'POST',
        url: "/items/gpu",
        data: {
            _csrf: $("input[name=_csrf]").val(),
            prices: $("#common-price").val(),
            vram: $("#gpu-vram").val(),
        },
        success: function (data) {
            $("#item-body").empty();
            $.each(data, function (id, entry) {
                $("#item-body").append(
                    "<tr>" +
                    commonTableParams(entry.item) +
                    "<td>" +
                    entry.item.manufacturer.title + "</td><td>" +
                    entry.item.price + "</td><td>" +
                    entry.item.count + "</td><td>" +
                    "</td><td>" +
                    entry.vram + "</td></tr>")
            });
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function filterHd() {
    $.ajax({
        type: 'POST',
        url: "/items/hd",
        data: {
            _csrf: $("input[name=_csrf]").val(),
            prices: $("#common-price").val(),
            hdFF: $("#hd-ff").val(),
            capacity: $("#hd-capacity").val(),
            speed: $("#hd-speed").val(),
            type: $("#hd-type").val()
        },
        success: function (data) {
            $("#item-body").empty();
            $.each(data, function (id, entry) {
                $("#item-body").append(
                    "<tr>" +
                    commonTableParams(entry.item) +
                    "<td>" +
                    entry.typeTitle + "</td><td>" +
                    entry.formFactorTitle + "</td><td>" +
                    entry.capacity + "</td><td>" +
                    entry.rwSpeed + "</td></tr>")
            });
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function filterMb() {
    $.ajax({
        type: 'POST',
        url: "/items/mb",
        data: {
            _csrf: $("input[name=_csrf]").val(),
            prices: $("#common-price").val(),
            socket: $("#mb-socket").val(),
            mbFF: $("#mb-ff").val(),
            chipset: $("#mb-chipset").val(),
            ramCount: $("#mb-ram-count").val(),
            ramTech: $("#mb-ram-tech").val()
        },
        success: function (data) {
            $("#item-body").empty();
            $.each(data, function (id, entry) {
                $("#item-body").append(
                    "<tr>" +
                    commonTableParams(entry.item) +
                    "<td>" + entry.formFactorTitle +
                    "</td><td>" + entry.socketTitle +
                    "</td><td>" + entry.ramTechTitle +
                    "</td><td>" + entry.chipsetTitle +
                    "</td><td>" + entry.num_of_ram + "</td></tr>")
            });
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function filterPs() {
    $.ajax({
        type: 'POST',
        url: "/items/ps",
        data: {
            _csrf: $("input[name=_csrf]").val(),
            prices: $("#common-price").val(),
            power: $("#ps-power").val(),
            sata: $("#ps-sata-count").val(),
            pcie6: $("#ps-pcie6-count").val(),
            pcie8: $("#ps-pcie8-count").val()
        },
        success: function (data) {
            $("#item-body").empty();
            $.each(data, function (id, entry) {
                $("#item-body").append(
                    "<tr>" +
                    commonTableParams(entry.item) +
                    "<td>" +
                    entry.power + "</td><td>" +
                    entry.sataCount + "</td><td>" +
                    entry.pcie6Count + "</td><td>" +
                    entry.pcie8Count + "</td></tr>")
            });
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function filterRam() {
    $.ajax({
        type: 'POST',
        url: "/items/ram",
        data: {
            _csrf: $("input[name=_csrf]").val(),
            prices: $("#common-price").val(),
            capacity: $("#ram-capacity").val(),
            frequency: $("#ram-frequency").val(),
            tech: $("#ram-tech").val()
        },
        success: function (data) {
            $("#item-body").empty();
            $.each(data, function (id, entry) {
                $("#item-body").append(
                    "<tr>" +
                    commonTableParams(entry.item) +
                    "<td>" +
                    entry.techTitle + "</td><td>" +
                    entry.capacity + "</td><td>" +
                    entry.frequency + "</td></tr>")
            });
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function commonTableParams(item) {
    let cart = "<td>" + '<div class="row justify-content-center">' +
        '<form method="post" id="quantity-form" action="/cart">' +
        '<input type="hidden" name="_csrf" value="' + $("#csrf").val() + '"\'/>' +
        '<input type="hidden" name="itemId" value="' + item.id + '">' +
        '<input type="number" name="quantity" value="1" min="1" max="' + item.count + '" maxlength="2" class="col form-control">' +
        '<button type="submit" class="col btn btn-secondary btn-sm quantity-button">+</button>' +
        "</form></div></td>"
    let userPart = "<td><a href='/item/" + item.id + "'>" + item.name + "</a></td>" +
        "<td>" + item.manufacturer.title + "</td>" +
        "<td>" + item.price + "</td>" +
        "<td>" + item.count + "</td>";
    let employeePart = "<td><a href='/items/edit/" + item.id + "' class='btn btn-secondary btn-sm'>Изменить</a></td>" +
        "<td><form method='post' action='/items/count/add'>" +
        "<input type='hidden' name='_csrf' value='" + $("#csrf").val() + "'/>" +
        "<input type='hidden' name='itemId' value='" + item.id + "'>" +
        "<input type='number' name='count' placeholder='Кол-во' class='w-50'>" +
        "<input type='submit' value='Добавить' class='btn btn-secondary btn-sm'>" +
        "</form>" +
        "</td>"
    if ($("#isEmployee").val() == "true")
        return cart + userPart + employeePart;
    else
        return cart + userPart;

}

function switchFilters(elem) {

    $("#items-filter").children().hide();
    $("#filter-common").show();
    $('#characteristics-header > td[class*="toHide"]').hide();
    $("#item-body").empty();
    switch (elem.id) {
        case 'switch-cpu':
            $("#items-switcher").val("cpu");
            $("#filter-cpu").show();
            $('#characteristics-header > td[class*="filter-cpu"]').show();
            filterCpu();
            break;
        case 'switch-cs':
            $("#items-switcher").val("cs");
            $("#filter-cs").show();
            $('#characteristics-header > td[class*="filter-cs"]').show();
            filterCs();
            break;
        case 'switch-mb':
            $("#items-switcher").val("mb");
            $("#filter-mb").show();
            $('#characteristics-header > td[class*="filter-mb"]').show();
            filterMb();
            break;
        case 'switch-ram':
            $("#items-switcher").val("ram");
            $("#filter-ram").show();
            $('#characteristics-header > td[class*="filter-ram"]').show();
            filterRam();
            break;
        case 'switch-gpu':
            $("#items-switcher").val("gpu");
            $("#filter-gpu").show();
            $('#characteristics-header > td[class*="filter-gpu"]').show();
            filterGpu();
            break;
        case 'switch-ps':
            $("#items-switcher").val("ps");
            $("#filter-ps").show();
            $('#characteristics-header > td[class*="filter-ps"]').show();
            filterPs();
            break;
        case 'switch-case':
            $("#items-switcher").val("case");
            $("#filter-case").show();
            $('#characteristics-header > td[class*="filter-case"]').show();
            filterCse();
            break;
        case 'switch-hd':
            $("#items-switcher").val("hd");
            $("#filter-hd").show();
            $('#characteristics-header > td[class*="filter-hd"]').show();
            filterHd();
            break;
    }
}