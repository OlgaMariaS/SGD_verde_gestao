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

function criarCardAvisos() {
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
        .then(listaResponseCardAviso => {
            listaAvisos = [];

            listaResponseCardAviso.forEach(responseCardAviso => {
                const novoCardAviso = {
                    id: responseCardAviso.id,
                    titulo: responseCardAviso.titulo,
                    texto: responseCardAviso.texto,
                    usuario: responseCardAviso.usuario,
                    data: responseCardAviso.data
                };

                listaAvisos.unshift(novoCardAviso);
            });

            criarCardAvisos();
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