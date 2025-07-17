function atualizarConteudoHtml(html, callback) {
    document.getElementById("content").innerHTML = html;
    callback()
}

function requisitarAPI(url, metodo = "GET", body = null) {
    const opcoes = {
        method: metodo,
        headers: {
            "Content-Type": "application/json"
        }
    };

    if (body) {
        opcoes.body = JSON.stringify(body);
    }

    return fetch(url, opcoes)
        .then(resposta => {
            if (!resposta.ok) throw new Error(`Erro: ${resposta.status}`);
            return resposta.json();
        });
}