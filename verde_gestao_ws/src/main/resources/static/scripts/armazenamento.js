function recuperarLocalmente(tag) {
    return JSON.parse(localStorage.getItem(tag))
}

function salvarLocalmente(tag, dado) {
    localStorage.setItem(tag, JSON.stringify(dado));
}