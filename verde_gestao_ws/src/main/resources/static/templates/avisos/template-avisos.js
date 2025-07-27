let listaAvisos = [];

function configurarAvisos() {
    document.getElementById("form-aviso").addEventListener("submit", function(evento) {
        evento.preventDefault();

        const tituloAviso = document.getElementById("campo-titulo").value;
        const mensagem = document.getElementById("campo-mensagem").value;
        const usuarioLogado = recuperarLocalmente('usuarioLogado');

        // Converte para ISO "yyyy-MM-dd"
        const hoje = new Date();
        const dataISO = hoje.toISOString().slice(0, 10);

        const aviso = {
            autor: { usuarioid: usuarioLogado.usuarioid },
            titulo: tituloAviso,
            texto: mensagem,
            dataInicio: dataISO,
            dataFim: dataISO
        };

        inserirAviso(aviso);
        document.getElementById("form-aviso").reset();
    });
}

function instanciarCardAvisos() {
    const containerAvisos = document.getElementById("lista-avisos");
    containerAvisos.innerHTML = "";

    listaAvisos.forEach((aviso, index) => {
        const cardAviso = document.createElement("div");
        cardAviso.className = "card mb-3 invisible"; // já vem invisível.
        cardAviso.innerHTML = htmlCardAviso(aviso);
        containerAvisos.appendChild(cardAviso);
        configurarBotaoExcluir(aviso);

        // Aplica o fade-in com atraso baseado no índice.
        setTimeout(() => {
            cardAviso.classList.remove("invisible");
            cardAviso.classList.add("fade-in");

            setTimeout(() => {
                cardAviso.classList.remove("fade-in");
            }, tempoFade);
        }, index * delayFade); // atraso entre cada card.
    });
}


function configurarBotaoExcluir(aviso) {
    const botaoExcluir = document.getElementById(`botao-excluir-${aviso.id}`);
    const usuarioLogado = recuperarLocalmente('usuarioLogado');
    const administrador = usuarioLogado.administrador;

    if (!administrador) {
        botaoExcluir.style.display = "none";
    } else {
        botaoExcluir.style.display = "inline-block";
    }
}

function buscarAvisos() {
    requisitarAPI(`/avisos/cards`)
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