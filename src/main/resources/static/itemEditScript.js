$(document).ready(function () {
    switchType();
})

function switchType() {
    let type = $("#itemType").val();
    $("#editable").children().hide();
    $("#submits").children().hide();
    switch (type) {
        case "CPU":
            $("#edit-cpu").show();
            $("#buttonCpu").show();
            break;
        case "CS":
            $("#edit-cs").show();
            $("#buttonCs").show();
            break;
        case "CASE":
            $("#edit-case").show();
            $("#buttonCase").show();
            break;
        case "GPU":
            $("#edit-gpu").show();
            $("#buttonGpu").show();
            break;
        case "HD":
            $("#edit-hd").show();
            $("#buttonHd").show();
            break;
        case "MB":
            $("#edit-mb").show();
            $("#buttonMb").show();
            break;
        case "PS":
            $("#edit-ps").show();
            $("#buttonPs").show();
            break;
        case "RAM":
            $("#edit-ram").show();
            $("#buttonRam").show();
            break;
    }
}

function save(data) {
    let url = $("#itemType").val();
    if (url === "-1") {
        setMessage("Не выбрана категория товара.")
        return;
    }
    let itemData = {
        _csrf: $("input[name=_csrf]").val(),
        itemId: $("#item-id").val(),
        name: $("#name").val(),
        manufacturer: $("#manufacturer").val(),
        itemType: url,
        price: $("#price").val(),
    };
    if (itemData.name.trim()  == 0 || itemData.manufacturer === "-1" || itemData.price == 0){
        setMessage("Не заполнено одно из основных полей товара.")
        return;
    }
    for (let char in data){
        if (!char.includes("Id") && (data[char] === "-1" || !data[char].trim() || data[char] == 0)){
            setMessage("Одно из полей характеристик не заполнено.")
            return;
        }
    }

    Object.assign(itemData, data);
    $.ajax({
        type: 'POST',
        url: "/items/save/"+url.toLowerCase(),
        data: itemData,
        success: function (data){
            window.location.href = data;
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

function saveCpu() {
    return {
        cpuId: $("#cpu-id").val(),
        cores: $("#cpu-cores").val(),
        flows: $("#cpu-flows").val(),
        frequency: $("#cpu-frequency").val(),
        socket: $("#cpu-socket").val(),
    }
}

function saveCs() {
    return {
        csId: $("#cs-id").val(),
        type: $("#cs-type").val(),
        fanCount: $("#cs-fan-count").val(),
        fanDiam: $("#cs-fan-diam").val(),
        socket: $("#cs-socket").val(),
    };
}
function saveCase() {
    return {
        caseId: $("#case-id").val(),
        caseFF: $("#case-form-factor").val(),
        motherboardFF: $("#case-mb-form-factor").val(),
        window: $("#case-window").val(),
    }
}
function saveGpu() {
    return {
        gpuId: $("#gpu-id").val(),
        vram: $("#gpu-vram").val(),
    }
}
function saveHd() {
    return {
        hdId: $("#hd-id").val(),
        type: $("#hd-type").val(),
        formFactor: $("#hd-form-factor").val(),
        capacity: $("#hd-capacity").val(),
        speed: $("#hd-speed").val(),
    }
}
function saveMb() {
    return {
        mbId: $("#mb-id").val(),
        socket: $("#mb-socket").val(),
        formFactor: $("#mb-form-factor").val(),
        chipset: $("#mb-chipset").val(),
        ramTech: $("#mb-ram-tech").val(),
        ramSlotCount: $("#mb-ram-count").val(),
        ports: $("#mb-port").val(),
    }
}
function savePs() {
    return  {
        psId: $("#ps-id").val(),
        power: $("#ps-power").val(),
        sata: $("#ps-sata").val(),
        pcie6: $("#ps-pcie6").val(),
        pcie8: $("#ps-pcie8").val(),
    }
}
function saveRam() {
    return {
        ramId: $("#ram-id").val(),
        tech: $("#ram-tech").val(),
        capacity: $("#ram-capacity").val(),
        frequency: $("#ram-frequency").val(),
    }
}

function eraseMessage() {
    $("#message").text("");
}

function setMessage(message) {
    $("#message").text(message);
}