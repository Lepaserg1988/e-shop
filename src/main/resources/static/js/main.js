let productTable = document.getElementById("productTable");
let modal = document.getElementById('cityModal');
let productCounter = document.getElementById("productCounter");

function init(){
    getProductList();
    if (getCookie("city")) {
        showUserCity();
    }
}
init();

//получение списка продуктов с сервера
function getProductList() {
    productTable.innerHTML = "";
    let url = getSearchProductUrl();
    fetch('/products' + url)
        .then(response => response.json())
        .then(result => buildResultTable(result));
}

//построение таблицы с продуктами
function buildResultTable(productList){
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

//построение кнопки "добавить в корзину"
function buildAddToBasketBtn(id){
    let addToBasketBtn = document.createElement("button");
    addToBasketBtn.textContent = "Добавить в корзину";
    addToBasketBtn.addEventListener("click", function (){
        fetch('http://localhost:8080/addToBasket?productId=' + id, {
            method: 'PUT',
        })
            .then(response => response.json())
            .then(result => {
                console.log(result);
                let counter = productCounter.textContent || 0;
                productCounter.textContent = +counter + 1;
            });
    })
    return addToBasketBtn;
}

let productSortSelect = document.getElementById('productSort');
productSortSelect.addEventListener("change", function (){
    getProductList();
})

setOnClick("goToBasket", () => {
    location.href = "/basket"
})


