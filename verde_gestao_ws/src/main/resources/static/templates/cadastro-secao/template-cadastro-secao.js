function configurarCadastroSecao(id = null) {
    if (id) {
        requisitarAPI(`/secoes/${id}`)
            .then(secao => {
                document.getElementById("nome").value = secao.descricao;
            });
    }

    document.getElementById("form-secao").addEventListener("submit", (e) => {
        e.preventDefault();

        const secao = {
            descricao: document.getElementById("nome").value
        };

        const metodo = id ? "PUT" : "POST";
        const url = id ? `/secoes/${id}` : "/secoes";

        requisitarAPI(url, metodo, secao)
            .then(() => {
                alert("Seção salva!");
                atualizarConteudoHtml(htmlTabelaSecao, configurarTabelaSecao);
            });
    });
}
