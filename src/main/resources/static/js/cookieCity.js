//отобразить окно с выбором города
let userCityEl = document.getElementById("userCity");
userCityEl.addEventListener("click", function (){
    modal.style.display = "block";
})

//выбор города
let selectCityBtn = document.getElementById("selectCityBtn");
let citySelect = document.getElementById("citySelect");
selectCityBtn.addEventListener("click", function (){
    setCookie("city", citySelect.value);
    showUserCity();
})

//отобразить город из куки
function showUserCity(){
    let userCityId = getCookie("city");
    let userCityName = document.querySelector('#citySelect option[value="' + userCityId +  '"]').textContent;
    document.getElementById('userCity').textContent = userCityName;
    modal.style.display = "none";
}