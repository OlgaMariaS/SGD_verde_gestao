function configurarTabelaSecao() {
    configurarTabelaGenerica({
        endpoint: "/secoes",
        idTabela: "tabela-secao",
        titulo: "seção",
        htmlCadastro: htmlCadastroSecao,
        configCadastro: configurarCadastroSecao,
        getId: s => s.secaoid,
        campos: [
            { nome: "descricao" }
        ]
    });
}