function logout(){
    console.log("Invoked postUserLogout() ");
    Cookies.remove("token"); //instead of deleting the cookie from the database, this deletes it from the browser.
    window.location.href = "index.html"
}

