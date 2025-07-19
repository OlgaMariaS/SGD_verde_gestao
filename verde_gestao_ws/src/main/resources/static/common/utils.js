function atualizarConteudoHtml(html, callback) {
    document.getElementById("content").innerHTML = html();
    callback()
}

function recuperarLocalmente(tag) {
    return JSON.parse(localStorage.getItem(tag))
}

function salvarLocalmente(tag, dado) {
    localStorage.setItem(tag, JSON.stringify(dado));
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
            if (!resposta.ok) {
                return resposta.text().then(text => {
                    throw new Error(`Erro: ${resposta.status} - ${text}`);
                });
            }
            return resposta.json();
        })
        .catch(erro => {
            console.error('Erro na requisição:', erro);
            alert(erro.message); // Todo: remover ao finalizar o sistema.
            throw erro;
        });
}
