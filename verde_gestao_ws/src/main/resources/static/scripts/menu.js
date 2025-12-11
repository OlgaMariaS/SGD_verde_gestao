function verificarUsuarioLogado() {
    const usuarioLogado = recuperarLocalmente('usuarioLogado');

    if (usuarioLogado == null) {
        window.location.href = "index.html";
        return;
    }

    fadeInTitle()
    fadeInMenu(usuarioLogado);
}

function mostraBotoesAdministrador(usuarioLogado) {
    if (usuarioLogado.tipousuario === 'Chefe Diretor') {
        ['btn-categorias-documentos', 'btn-categorias-solicitacoes', 'btn-usuarios', 'btn-secoes'].forEach(id => {
            const botao = document.getElementById(id);
            if (botao) botao.style.display = 'none';
        });

        return;
    }

    if (!usuarioLogado.administrador) {
        ['list-adm', 'divider-adm'].forEach(id => {
            const botao = document.getElementById(id);
            if (botao) botao.style.display = 'none';
        });
    }
}

function fadeInMenu(usuarioLogado) {
    const menu = document.getElementById("sidebar");

    setTimeout(() => {
        mostraBotoesAdministrador(usuarioLogado);

        menu.classList.remove("invisible");
        menu.classList.add("fade-in");

        setTimeout(() => {
            menu.classList.remove("fade-in");
        }, TEMPO_FADE);
    }, TEMPO_FADE);

    atualizarConteudoHtml(htmlInicio, configurarInicio);
}

function fadeInTitle() {
    const titulo = document.getElementById("title");

    setTimeout(() => {
        titulo.classList.remove("invisible");
        titulo.classList.add("fade-in");

        setTimeout(() => {
            titulo.classList.remove("fade-in");
        }, TEMPO_FADE);
    }, TEMPO_FADE);
}

function deslogarUsuario() {
    salvarLocalmente('usuarioLogado', null)
    verificarUsuarioLogado()
}