function configurarCadastroSolicitacao(id = null) {
    const usuarioLogado = recuperarLocalmente('usuarioLogado');

    if (id) {
        Promise.all([
            requisitarAPI("/tipoSolicitacoes"),
            requisitarAPI("/tipoDocumentos"),
            requisitarAPI(`/usuarios/quemPossoEnviar/${usuarioLogado.usuarioid}/${usuarioLogado.tipoUsuario}`),
            requisitarAPI(`/solicitacoes/${id}`)
        ])
            .then(([tipos, docs, responsas, s]) => {
                // Preenche os dropdowns...
                document.getElementById("tiposolicitacaoid").innerHTML =
                    tipos.map(t => `<option value="${t.tiposolicitacaoid}">${t.descricao}</option>`).join("");

                document.getElementById("tipodocumentoid").innerHTML =
                    docs.map(d => `<option value="${d.tipoDocumentoid}">${d.descricao}</option>`).join("");

                document.getElementById("responsavelid").innerHTML =
                    responsas.map(r => `<option value="${r.usuarioid}">(${r.tipoUsuario.descricao}) ${r.nome}</option>`).join("");

                // Agora que os options foram adicionados, setamos os valores corretos...
                document.getElementById("tiposolicitacaoid").value = s.tipoSolicitacao?.tiposolicitacaoid;
                document.getElementById("tipodocumentoid").value = s.tipoDocumento?.tipoDocumentoid;
                document.getElementById("responsavelid").value = s.responsavel?.usuarioid;
                document.getElementById("descricao").value = s.descricao;

                if (usuarioLogado.usuarioid !== s.responsavel.usuarioid) {
                    desativarCamposSolicitacao();
                }
            });
    } else {
        carregarDropDownsSolicitacao();
    }

    document.getElementById("form-solicitacao").addEventListener("submit", e => {
        e.preventDefault();

        const botaoClicado = e.submitter;
        const acao = botaoClicado?.value;

        const hoje = new Date();
        const dataISO = hoje.toISOString().slice(0, 10);

        const nova = {
            criador: { usuarioid: usuarioLogado.usuarioid },
            responsavel: { usuarioid: parseInt(document.getElementById("responsavelid").value) },
            status: 'ABERTA',
            descricao: document.getElementById("descricao").value,
            dataCriacao: dataISO,
            tipoSolicitacao: {
                tiposolicitacaoid: parseInt(document.getElementById("tiposolicitacaoid").value)
            },
            tipoDocumento: {
                tipoDocumentoid: parseInt(document.getElementById("tipodocumentoid").value)
            }
        };

        switch (acao) {
            case "salvar":
                nova.status = 'ABERTA';
                break;
            case "resolver":
                nova.status = 'CONCLUÍDA';
                break;
            case "cancelar":
                nova.status = 'CANCELADA';
                break;
            default:
        }

        const metodo = id ? "PUT" : "POST";
        const url = id ? `/solicitacoes/${id}` : "/solicitacoes";

        requisitarAPI(url, metodo, nova)
            .then(() => {
                alert("As informações na solicitações foram salvas!");
                voltarParaPrimeiraTelaModulo();
            });
    });
}

function carregarDropDownsSolicitacao() {
    const usuarioLogado = recuperarLocalmente('usuarioLogado');

    requisitarAPI("/tipoSolicitacoes")
        .then(tipos => {
            const select = document.getElementById("tiposolicitacaoid");
            select.innerHTML = tipos.map(t => `<option value="${t.tiposolicitacaoid}">${t.descricao}</option>`).join("");
        });

    requisitarAPI("/tipoDocumentos")
        .then(docs => {
            const select = document.getElementById("tipodocumentoid");
            select.innerHTML = docs.map(d => `<option value="${d.tipoDocumentoid}">${d.descricao}</option>`).join("");
        });

    requisitarAPI(`/usuarios/quemPossoEnviar/${usuarioLogado.usuarioid}/${usuarioLogado.tipoUsuario}`)
        .then(responsas => {
            const select = document.getElementById("responsavelid");
            select.innerHTML = responsas.map(r => `<option value="${r.usuarioid}">(${r.tipoUsuario.descricao}) ${r.nome}</option>`).join("");
        });
}

function desativarCamposSolicitacao() {
    const form = document.getElementById("form-solicitacao");
    if (!form) return;

    const elementos = form.querySelectorAll("select, textarea, input, button");

    elementos.forEach(el => {
        el.disabled = true;
    });
}