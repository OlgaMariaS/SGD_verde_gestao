document.addEventListener("DOMContentLoaded", function () {
    const janelaAtual = window;
    const divConteudo = document.querySelector(".main-content");
    const links = document.querySelectorAll(".sidebar .nav-link");

    function sair() {
        janelaAtual.location.href = "login.html";
    }

    function aoClicarNoLink(e) {
        e.preventDefault();

        const texto = this.textContent.trim();
        switch (texto) {
            case "Cadastrar Novos Avisos":
                carregarFormAdicionarAvisos(divConteudo);
                break;
            case "Cadastrar Nova Solicitação":
                carregarFormNovaSolicitacao(divConteudo);
                break;
            case "Consultar Solicitações":
                carregarFormSolicitacoes(divConteudo);
                break;
            case "Sair":
                sair();
                break;
        }
    }

    links.forEach((link) => {
        link.addEventListener("click", aoClicarNoLink);
    });
});