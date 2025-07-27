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
            { nome: "tipoUsuario", render: u => u.tipoUsuario?.descricao || "-" },
            { nome: "secao", render: u => u.secao?.descricao || "-" }
        ]
    });
}