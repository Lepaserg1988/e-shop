function setOnClick(id, callback) {
    let el = document.getElementById(id);
    el.addEventListener("click", callback)
}

function getSearchProductUrl(){
    let urlParamArr = [];
    let productSortSelect = document.getElementById('productSort');
    let sortOrder = productSortSelect.value;
    urlParamArr.push("sortOrder=" + sortOrder);
    let textSearchInput = document.getElementById("textSearchInput");
    let searchText = textSearchInput.value;
    urlParamArr.push("searchText=" + searchText);
    return "?" + urlParamArr.join("&");
}