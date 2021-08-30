function validateForm() {
    let f = document.getElementById("form")
    let username = f.username
    if (username.value == "" || !username.value.match("[a-zA-Z0-9_]+")) {
        username.focus()
        alert("Enter your username")
        return false
    }
    let password = f.password
    if (password.value == "" || !password.value.match("[a-zA-Z0-9]{8,}")) {
        password.focus()
        alert("Enter your password")
        return false
    }
    let confirmPsw = f.confirmPsw
    if (confirmPsw.value == "" || !password.value.match("[a-zA-Z0-9]{8,}")) {
        confirmPsw.focus()
        alert("Confirm your password")
        return false
    }
    if (password.value != confirmPsw.value) {
        confirmPsw.focus()
        alert("Confirm password doesn't match!")
        return false
    }
    let email = f.email
    if (email.value == "" || !email.value.match("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
        email.focus()
        alert("Enter your email")
        return false
    }
    return true
}