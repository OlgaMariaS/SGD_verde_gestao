function configurarTabelaTipoDocumento() {
    configurarTabelaGenerica({
        endpoint: "/tipoDocumentos",
        idTabela: "tabela-tipo-documento",
        titulo: "tipo de documento",
        htmlCadastro: htmlCadastroTipoDocumento,
        configCadastro: configurarCadastroTipoDocumento,
        getId: d => d.tipodocumentoid,
        campos: [
            { nome: "descricao" },
            { nome: "prazoEmDias" },
            { nome: "inativo", render: d => d.inativo ? "Não" : "Sim" }
        ]
    });
}