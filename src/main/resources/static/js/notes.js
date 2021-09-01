async function loadNotes() {
    let response = await fetch("/notes/all")
    let result = await response.json()
}

loadNotes()