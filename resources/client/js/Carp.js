"use strict";
function getCarpProductsList() {

    console.log("Invoked getCarpProductsList()");     //console.log your BFF for debugging client side - also use debugger statement
    const url = "/CarpProducts/list";    		// API method on web server will be in Users class, method list
    fetch(url,{
        method: "GET",				//Get method
    }).then(response => {
        return response.json();                 //return response as JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) { //checks if response from the web server has an "Error"
            alert(JSON.stringify(response));    // if it does, convert JSON object to string and alert (pop up window)
        } else {
            formatProductsList(response);          //this function will create an HTML table of the data
        }
    });
}

function formatProductsList(myJSONArray){
    let dataHTML = "";
    for (let item of myJSONArray) {
        dataHTML += "<tr><td>" + item.ProductID + "<td><td>" + item.ProductName + "<td><td>" + item.ProductDepartment + "<td><td>" + item.Brand + "<td><td>" + item.OtherInfo + "<td><td>" + item.Price + "<tr><td>";
    }
    document.getElementById("ProductsTable").innerHTML = dataHTML;
}