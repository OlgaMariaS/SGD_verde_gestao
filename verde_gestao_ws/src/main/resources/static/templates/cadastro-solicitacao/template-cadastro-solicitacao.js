let idSolicitacao = null

function configurarCadastroSolicitacao(id = null) {
    const usuarioLogado = recuperarLocalmente('usuarioLogado');

    if (id) {
        idSolicitacao = id;

        Promise.all([
            requisitarAPI("/tipoSolicitacoes"),
            requisitarAPI("/tipoDocumentos"),
            requisitarAPI(`/usuarios/quemPossoEnviar/${usuarioLogado.usuarioid}/${usuarioLogado.tipousuario}`),
            requisitarAPI(`/solicitacoes/${id}`)
        ])
            .then(([tipoSol, tipoDoc, res, solicitacao]) => {
                document.getElementById("tiposolicitacaoid").innerHTML =
                    tipoSol.map(t => `<option value="${t.tiposolicitacaoid}">${t.descricao}</option>`).join("");

                document.getElementById("tipodocumentoid").innerHTML =
                    tipoDoc.map(d => `<option value="${d.tipodocumentoid}">${d.descricao}</option>`).join("");

                document.getElementById("responsavelid").innerHTML =
                    res.map(r => `<option value="${r.usuarioid}">(${r.tipousuario.descricao}) ${r.nome}</option>`).join("");

                document.getElementById("tiposolicitacaoid").value = solicitacao.tipoSolicitacao?.tiposolicitacaoid;
                document.getElementById("responsavelid").value = solicitacao.responsavel?.usuarioid;
                document.getElementById("descricao").value = solicitacao.descricao;

                construirListaDocumentos(solicitacao);

                document.getElementById("area-anexar-documentos").classList.remove("d-none");
                document.getElementById("area-anexar-documentos").classList.add("d-flex");
                document.getElementById("mensagem-salvar-primeiro").style.display = "none";

                if (usuarioLogado.usuarioid !== solicitacao.responsavel.usuarioid) {
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
                tipodocumentoid: parseInt(document.getElementById("tipodocumentoid").value)
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

    document.getElementById("arquivo").addEventListener("change", function () {
        const nomeArquivo = this.files[0] ? this.files[0].name : "Escolher arquivo";
        this.nextElementSibling.innerText = nomeArquivo;
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
            select.innerHTML = docs.map(d => `<option value="${d.tipodocumentoid}">${d.descricao}</option>`).join("");
        });

    requisitarAPI(`/usuarios/quemPossoEnviar/${usuarioLogado.usuarioid}/${usuarioLogado.tipousuario}`)
        .then(responsas => {
            const select = document.getElementById("responsavelid");
            select.innerHTML = responsas.map(r => `<option value="${r.usuarioid}">(${r.tipousuario.descricao}) ${r.nome}</option>`).join("");
        });
}

function desativarCamposSolicitacao() {
    const form = document.getElementById("form-solicitacao");
    if (!form) return;

    const elementos = form.querySelectorAll("select, textarea, input, button");

    elementos.forEach(el => {
        el.disabled = true;
    });

    document.getElementById("area-anexar-documentos").classList.add("d-none");
    document.getElementById("area-anexar-documentos").classList.remove("d-flex");
    document.getElementById("mensagem-salvar-primeiro").style.display = "none";
}

function construirListaDocumentos(solicitacao) {
    const listaDocumentos = document.getElementById("lista-documentos");
    solicitacao.documentos.forEach(function (documento) {
        const html = htmlCardDocumento(documento);
        listaDocumentos.innerHTML += html;
    });

    vincularEventosBaixar(solicitacao.documentos);
}

function vincularEventosBaixar(documentos) {
    documentos.forEach((documento) => {
        const botao = document.querySelector(`[data-documento-id="${documento.documentoid}"]`);
        if (botao) {
            botao.addEventListener("click", () => {
                const nome = `${documento.documentoid} - ${documento.tipodocumento.descricao}.pdf`;
                baixarPDF(documento.documentoid, nome);
            });
        }
    });
}

function baixarPDF(documentoId, nomeArquivo = "documento.pdf") {
    console.log(documentoId);
    fetch(`documentos/download/${documentoId}`)
        .then(response => {
            if (!response.ok) throw new Error("Erro ao baixar");
            return response.blob();
        })
        .then(blob => {
            const url = URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.href = url;
            a.download = nomeArquivo;
            a.click();
            URL.revokeObjectURL(url);
        })
        .catch(err => alert("Erro: " + err.message));
}

function anexarDocumento() {
    const tipoDocumentoId = document.getElementById("tipodocumentoid").value;
    const arquivoInput = document.getElementById("arquivo");
    const arquivo = arquivoInput.files[0];

    // Verificação de segurança... Pro cara não botar o mesmo arquivo duas vezes.
    if (!tipoDocumentoId || !arquivo || arquivo.size === 0) {
        alert("Selecione um tipo de documento e um arquivo válido.");
        return;
    }

    console.log("Arquivo selecionado:", arquivo.name, "Tamanho:", arquivo.size);

    const formData = new FormData();
    formData.append("arquivo", arquivo);
    formData.append("tipodocumentoid", tipoDocumentoId);
    formData.append("solicitacaoid", idSolicitacao);

    fetch("/documentos/upload", {
        method: "POST",
        body: formData
    })
        .then(response => {
            if (!response.ok) throw new Error("Erro no envio do documento.");
            alert("Documento anexado com sucesso!");

            // Limpa o input após o uso.
            arquivoInput.value = "";
            reiniciarTela();
        })
        .catch(error => {
            console.error("Erro ao enviar documento:", error);
            alert("Falha ao enviar documento.");
        });
}

function deletarDocumento(documentoId) {
    requisitarAPI(`/documentos/${documentoId}`, "DELETE").then(() => {
        reiniciarTela();
    });
}

function reiniciarTela() {
    atualizarConteudoHtml(() => htmlCadastroSolicitacao(idSolicitacao), () => configurarCadastroSolicitacao(idSolicitacao));
}