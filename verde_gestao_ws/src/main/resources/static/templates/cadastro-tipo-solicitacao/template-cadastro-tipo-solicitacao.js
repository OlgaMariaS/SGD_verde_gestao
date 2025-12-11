function configurarCadastroTipoSolicitacao(id = null) {
    if (id) {
        requisitarAPI(`/tipoSolicitacoes/${id}`)
            .then(tipoSolicitacao => {
                document.getElementById("nome").value = tipoSolicitacao.descricao;
            });
    }

    document.getElementById("form-tipo-solicitacao").addEventListener("submit", (e) => {
        e.preventDefault();

        const tipoSolicitacao = {
            descricao: document.getElementById("nome").value
        };

        const metodo = id ? "PUT" : "POST";
        const url = id ? `/tipoSolicitacoes/${id}` : "/tipoSolicitacoes";

        requisitarAPI(url, metodo, tipoSolicitacao)
            .then(() => {
                alert("Tipo de solicitação salvo!");
                atualizarConteudoHtml(htmlTabelaTipoSolicitacao, configurarTabelaTipoSolicitacao);
            });
    });
}
