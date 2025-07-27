function configurarTabelaSolicitacaoUsuario() {
    configurarTabelaSolicitacao(true);
}

function configurarTabelaSolicitacao(somenteSolicitacoesUsuario = false) {
    const usuarioLogado = recuperarLocalmente('usuarioLogado');
    const tabela = document.querySelector("#tabela-solicitacoes tbody");
    const btnNovo = document.getElementById("btn-novo");
    const endPoint = somenteSolicitacoesUsuario ? "solicitacoes/minhasSolicitacoes/" + usuarioLogado.usuarioid : "/solicitacoes";

    btnNovo.addEventListener("click", () => {
        atualizarConteudoHtml(htmlCadastroSolicitacao, configurarCadastroSolicitacao);
    });

    requisitarAPI(endPoint)
        .then(lista => {
            tabela.innerHTML = "";

            lista.forEach(s => {
                const tr = document.createElement("tr");

                tr.innerHTML = `
                    <td>${s.solicitacaoid || "-"}</td>
                    <td>
                        <button class="btn btn-sm btn-primary editar" data-id="${s.solicitacaoid}">Ver</button>
                    </td>
                    <td>${s.criador?.nome || "-"}</td>
                    <td>${s.responsavel?.nome || "-"}</td>
                    <td>${s.tipoSolicitacao?.descricao || "-"}</td>
                    <td>${s.descricao.trim().substring(0, 40) || "-"}</td>
                    <td>${s.status}</td>
                `;

                tabela.appendChild(tr);
            });

            document.querySelectorAll(".editar").forEach(btn => {
                btn.addEventListener("click", () => {
                    const id = btn.dataset.id;
                    atualizarConteudoHtml(() => htmlCadastroSolicitacao(id), () => configurarCadastroSolicitacao(id));
                });
            });
        });
}