fetch('http://localhost:8080/products')
    .then(response => response.json())
    .then(result => buildResultTable(result));

let modal = document.getElementById('cityModal');
if (getCookie("city")) {
    showUserCity();
}

function showUserCity(){
    let userCityId = getCookie("city");
    let userCityName = document.querySelector('#citySelect option[value="' + userCityId +  '"]').textContent;
    document.getElementById('userCity').textContent = userCityName;
    modal.style.display = "none";
}


let productCounter = document.getElementById("productCounter");

function buildResultTable(productList){
    let productTable = document.getElementById("productTable");
    productList.forEach(product => {
        let nameDiv = document.createElement("div");
        nameDiv.textContent = product.name;
        let descriptionDiv = document.createElement("div");
        descriptionDiv.textContent = product.description;
        let priceDiv = document.createElement("div");
        priceDiv.textContent = product.price;
        let photoImg = document.createElement("img");
        photoImg.setAttribute("width", "50");
        photoImg.setAttribute("height", "60");
        photoImg.setAttribute("src", "/images/1.jpg")

        let productDiv = document.createElement("div");
        productDiv.appendChild(nameDiv);
        productDiv.appendChild(descriptionDiv);
        productDiv.appendChild(priceDiv);
        productDiv.appendChild(photoImg);
        productDiv.appendChild(buildAddToBasketBtn(product.id));
        productTable.appendChild(productDiv);
    });
}

function buildAddToBasketBtn(id){
    let addToBasketBtn = document.createElement("button");
    addToBasketBtn.textContent = "Добавить в корзину";
    addToBasketBtn.addEventListener("click", function (){
        fetch('http://localhost:8080/addToBasket?productId=' + id)
            .then(response => response.json())
            .then(result => {
                console.log(result);
                let counter = productCounter.textContent || 0;
                productCounter.textContent = +counter + 1;
            });
    })
    return addToBasketBtn;
}

let goToBasketBtn = document.getElementById("goToBasket");
goToBasketBtn.addEventListener("click", function (){
    location.href = "/basket"
})

let userCityEl = document.getElementById("userCity");
userCityEl.addEventListener("click", function (){
    modal.style.display = "block";
})

let selectCityBtn = document.getElementById("selectCityBtn");
let citySelect = document.getElementById("citySelect");
selectCityBtn.addEventListener("click", function (){
    setCookie("city", citySelect.value);
    showUserCity();
})


function setCookie(name, value, options = {}) {

    options = {
        path: '/',
        // при необходимости добавьте другие значения по умолчанию
        ...options
    };

    if (options.expires instanceof Date) {
        options.expires = options.expires.toUTCString();
    }

    let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);

    for (let optionKey in options) {
        updatedCookie += "; " + optionKey;
        let optionValue = options[optionKey];
        if (optionValue !== true) {
            updatedCookie += "=" + optionValue;
        }
    }

    document.cookie = updatedCookie;
}

function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}