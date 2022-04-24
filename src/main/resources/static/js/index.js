document.addEventListener("DOMContentLoaded", () => {
    init();
});

init = function(){
    getExchangeRate();
};

getExchangeRate = function(){
    let currencyCode = document.getElementById("recipientCountry");
    currencyCode = currencyCode.options[currencyCode.selectedIndex].value;
    ajaxRequest(currencyCode);
}

ajaxRequest = function(requestCurrencyCode){
    let xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            printExchangeRate(data, requestCurrencyCode);
        } else if(xhr.status === 400) {
            alert("요청 정보를 다시 확인해주세요.");
        } else {
            alert("서버에서 문제가 발생했습니다. 나중에 다시 시도해주세요.");
        }
    }
    xhr.open("GET", "exchange/" + requestCurrencyCode);
    xhr.send();
}

printExchangeRate = function (exchangeRateJson, currencyCode){
    const option = {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    };

    let exchangeRate = "";

    if(exchangeRateJson.usdkrw != null){
        exchangeRate += exchangeRateJson.usdkrw.toLocaleString('ko-KR', option);
    } else if(exchangeRateJson.usdjpy != null){
        exchangeRate += exchangeRateJson.usdjpy.toLocaleString('ko-KR', option);
    } else if(exchangeRateJson.usdphp != null){
        exchangeRate += exchangeRateJson.usdphp.toLocaleString('ko-KR', option);
    }

    exchangeRate += " " + currencyCode + "/USD";

    document.getElementById("exchangeRate").textContent = exchangeRate;
}

calculate = function(){
    const option = {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    };

    let remittanceAmount = document.getElementById("remittanceAmount").value;

    let numberCheck = /^[0-9]+$/;
    if(remittanceAmount === "" || remittanceAmount < 0 || remittanceAmount > 10000 || !numberCheck.test(remittanceAmount)){
        alert("송금액이 바르지 않습니다");
        return;

    }
    let exchangeRate = document.getElementById("exchangeRate").textContent.split(" ", 1)[0].replace(',','');
    let currencyCode = document.getElementById("exchangeRate").textContent.split(" ")[1].split("/")[0];

    let calculatedValue = (exchangeRate * remittanceAmount).toLocaleString('ko-KR', option);

    document.getElementById("calculateResult").textContent = "수취금액은 " + calculatedValue + " " + currencyCode + " 입니다.";
}