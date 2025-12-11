function configurarTabelaTipoSolicitacao() {
    configurarTabelaGenerica({
        endpoint: "/tipoSolicitacoes",
        idTabela: "tabela-tipo-solicitacao",
        titulo: "tipo de solicitação",
        htmlCadastro: htmlCadastroTipoSolicitacao,
        configCadastro: configurarCadastroTipoSolicitacao,
        getId: ts => ts.tiposolicitacaoid,
        campos: [
            { nome: "descricao" }
        ]
    });
}