async function loadNotes() {
    let response = await fetch("/notes/all")
    let notes = await response.json()
    let root = document.querySelector("#root")
    for (let i = 0; i < notes.length; i++) {
        let node = document.createElement("div")
        let title = document.createElement("a")
        title.innerText = notes[i].title
        title.href = notes[i].links[0].href
        let description = document.createElement("div")
        description.innerText = notes[i].description
        node.appendChild(title)
        node.appendChild(description)
        root.appendChild(node)
    }
}

loadNotes()