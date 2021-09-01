let f = document.getElementById("form")

async function submitForm() {
    if (await validateForm()) {
        f.url.value = window.location.href
        f.submit()
        return true
    }
    return false
}

async function validateForm() {
    let username = f.username
    let email = f.email

    if (username.value == "" || !username.value.match("[a-zA-Z0-9_]+")) {
        username.focus()
        alert("Enter your username")
        return false
    }
    let response = await fetch("/api/users")
    let users = await response.json()

    for (let i = 0; i < users.length; i++) {
        if (username.value == users[i].username) {
            username.focus()
            alert("This username is already used")
            return false
        }
        if (email.value == users[i].email) {
            email.focus()
            alert("This email is already used")
            return false
        }
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
    if (email.value == "" || !email.value.match("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
        email.focus()
        alert("Enter your email")
        return false
    }
    return true
}