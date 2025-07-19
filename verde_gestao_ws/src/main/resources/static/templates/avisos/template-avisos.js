let listaAvisos = [];

function configurarAvisos() {
    document.getElementById("form-aviso").addEventListener("submit", function(evento) {
        evento.preventDefault();

        const tituloAviso = document.getElementById("campo-titulo").value;
        const mensagem = document.getElementById("campo-mensagem").value;
        const dataHora = new Date().toLocaleString("pt-BR");
        const usuarioLogado = recuperarLocalmente('usuarioLogado');

        const aviso = {
            avisoId: 0,
            autorUsuarioId: usuarioLogado.usuarioId,
            titulo: tituloAviso,
            texto: mensagem,
            dataInicio: dataHora,
            dataFim: dataHora,
        }

        inserirAviso(aviso)

        document.getElementById("form-aviso").reset();
    });
}

function instanciarCardAvisos() {
    const containerAvisos = document.getElementById("lista-avisos");
    containerAvisos.innerHTML = "";

    listaAvisos.forEach(aviso => {
        const cardAviso = document.createElement("div");
        cardAviso.className = "card mb-3";
        cardAviso.innerHTML = htmlCardAviso(aviso);

        containerAvisos.appendChild(cardAviso);
    });
}

function buscarAvisos() {
    requisitarAPI(`/avisos/buscarTodosCardsAvisos`)
        .then(listaCardAviso => {
            listaAvisos = [];

            listaCardAviso.forEach(cardAviso => {
                const novoCardAviso = {
                    id: cardAviso.id,
                    titulo: cardAviso.titulo,
                    texto: cardAviso.texto,
                    usuario: cardAviso.usuario,
                    data: cardAviso.data
                };

                listaAvisos.unshift(novoCardAviso);
            });

            instanciarCardAvisos();
        }).catch(erro => {});
}

function inserirAviso(aviso) {
    console.log(aviso)

    requisitarAPI(`/avisos`, "POST", aviso)
        .then(resposta => {buscarAvisos()})
        .catch(erro => {});
}

function excluirAviso(id) {
    requisitarAPI(`/avisos/${encodeURIComponent(id)}`, "DELETE")
        .then(resposta => {buscarAvisos()})
        .catch(erro => {});
}