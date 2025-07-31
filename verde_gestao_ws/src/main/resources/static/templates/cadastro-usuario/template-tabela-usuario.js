function configurarTabelaUsuario() {
    configurarTabelaGenerica({
        endpoint: "/usuarios",
        idTabela: "tabela-usuarios",
        titulo: "usuário",
        htmlCadastro: htmlCadastroUsuario,
        configCadastro: configurarCadastroUsuario,
        getId: u => u.usuarioid,
        campos: [
            { nome: "nome" },
            { nome: "administrador", render: u => u.administrador ? "Sim" : "Não" },
            { nome: "tipousuario", render: u => u.tipousuario?.descricao || "-" },
            { nome: "secao", render: u => u.secao?.descricao || "-" }
        ]
    });
}