let productTable = document.getElementById("productTable");
let modal = document.getElementById('cityModal');

function getProductList() {
    productTable.innerHTML = "";
    fetch('http://localhost:8080/getProductInBasket')
        .then(response => response.json())
        .then(result => {
            buildResultTable(result);
        });
}

function init(){
    getProductList();
    if (getCookie("city")) {
        showUserCity();
    }
}
init();

function buildResultTable(productList){
    productList.forEach(product => {
        let nameDiv = document.createElement("div");
        nameDiv.textContent = product.name;
        let countDiv = document.createElement("div");
        countDiv.textContent = product.count;
        countDiv.style.margin  = "10px";
        let priceDiv = document.createElement("div");
        priceDiv.textContent = product.price;
        let photoImg = document.createElement("img");
        photoImg.setAttribute("width", "120");
        photoImg.setAttribute("height", "100");
        photoImg.setAttribute("src", '/images/' + product.photoUrl)


        let addToBasketBtn = buildAddToBasketBtn(product.id);
        let reduceFromBasketBtn = buildReduceFromBasketBtn(product.id);
        let deleteFromBasketBtn = buildDeleteFromBasketBtn(product.id);

        let countChangeDiv = document.createElement("div");
        countChangeDiv.className = "count-change"
        countChangeDiv.appendChild(reduceFromBasketBtn);
        countChangeDiv.appendChild(countDiv);
        countChangeDiv.appendChild(addToBasketBtn);
        let productDiv = document.createElement("div");
        productDiv.className = "product-row"
        productDiv.appendChild(photoImg);
        productDiv.appendChild(nameDiv);
        productDiv.appendChild(priceDiv);
        productDiv.appendChild(countChangeDiv);
        productDiv.appendChild(deleteFromBasketBtn);
        productTable.appendChild(productDiv);
    });

    let totalPriceDiv = document.createElement("div");
    totalPriceDiv.textContent = computeTotalPrice(productList);
    productTable.appendChild(totalPriceDiv);
}

function computeTotalPrice(productList){
    let totalPrice = 0;
    productList.forEach(prod => {
        totalPrice += prod.price * prod.count;
    })
    return totalPrice;
}

function buildAddToBasketBtn(id) {
    let addToBasketBtn = document.createElement("button");
    addToBasketBtn.textContent = "+";
    addToBasketBtn.addEventListener("click", function () {
        fetch('http://localhost:8080/addToBasket?productId=' + id, {
            method: 'PUT',
        }).then(getProductList);
    })
    return addToBasketBtn;
}
function buildReduceFromBasketBtn(id){
    let addToBasketBtn = document.createElement("button");
    addToBasketBtn.textContent = "-";
    addToBasketBtn.addEventListener("click", function (){
        fetch('http://localhost:8080/reduceFromBasket?productId=' + id, {
            method: 'PUT',
        })
            .then(getProductList);
    })
    return addToBasketBtn;
}
function buildDeleteFromBasketBtn(id){
    let addToBasketBtn = document.createElement("button");
    addToBasketBtn.textContent = "Удалить";
    addToBasketBtn.addEventListener("click", function (){
        fetch('http://localhost:8080/deleteFromBasket?productId=' + id, {
            method: 'DELETE',
        })
            .then(getProductList);
    })
    return addToBasketBtn;
}

setOnClick('makeOrder', ()=>{
    fetch('http://localhost:8080/saveOrder', {
        method: 'POST',
        body: JSON.stringify({cityId: getCookie("city")})
    })
})