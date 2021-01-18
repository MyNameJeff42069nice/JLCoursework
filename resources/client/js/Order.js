function AddOrder() {
    console.log("Invoked AddOrder()");
    let url = "/Order/add";
    let formData = new FormData();
    formData.append("userID", parseInt(Cookies.get("userID")));
    console.log(parseInt(Cookies.get("userID")));
    fetch(url, {
        method: "POST",
        body: formData,
    }).then(response => {
        console.log('Response:', response)
        return response.json()
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));
            if (response == null) {
                alert(JSON.stringify(response));
            }
        } else {
            getOrderList()
        }
    });
}
function getOrderList() {
    console.log("Invoked getOrderList()");

    const url = "/Order/list/";
    let userID = Cookies.get("userID")
    fetch(url+userID , {
        method: "GET",
    }).then(response => {
        return response.json();
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));
        } else{
            OrderVariables(response)
        }
    })
}
function OrderVariables(response) {
    console.log("Invoked OrderVariables()");
    console.log(response);
    console.log(response.length);
    let OrderID = response[response.length-1].OrderID;
    let Quantity;
    var ProductID;
    for (let i = 0; i < localStorage.length; i++) {
        //key is ID number of product
        ProductID = localStorage.key(i);
        //value at key is the quantity of said item
        Quantity = localStorage.getItem(ProductID);
        OrderProduct(OrderID, Quantity, ProductID);
    }
    alert ("Thank you for placing your order! A Paypal invoice from our company will be sent to your email soon. If this invoice is not payed within 7 days, your order will automatically be cancelled and must be replaced.");
    localStorage.clear();
    window.open("index.html", "_self");
}

function OrderProduct(OrderID, Quantity, ProductID){
    console.log("Invoked OrderProduct()");
    let url = "/Order/addProduct";
    let formData = new FormData();
    formData.append("OrderID", OrderID);
    formData.append("ProductID", ProductID);
    formData.append("Quantity", Quantity);
    fetch(url, {
        method: "POST",
        body: formData,
    }).then(response => {
        console.log('Response:', response)
        return response.json()
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));
            if (response == null) {
                alert(JSON.stringify(response));
            }
        }
    });
}
