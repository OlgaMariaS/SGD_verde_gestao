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

            // Não parseia JSON vazio...
            const contentType = resposta.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                return resposta.json();
            } else {
                return null; // ou lança undefined...
            }
        })
        .catch(erro => {
            console.error('Erro na requisição:', erro);
            throw erro;
        });
}