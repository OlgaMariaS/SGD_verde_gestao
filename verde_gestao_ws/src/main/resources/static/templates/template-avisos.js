// Todo: fazer essa classe funcionar com o banco de dados.

const listaAvisos = [];

function htmlAvisos() {
    return `
        <div class="container mt-4">
            <!-- Formulário de aviso -->
            <div id="formulario-aviso" class="card mb-4">
                <div class="card-header">
                    Adicionar Aviso
                </div>
                <div class="card-body">
                    <form id="form-aviso">
                        <div class="form-group">
                            <label for="campo-titulo">Título do aviso</label>
                            <input type="text" class="form-control" id="campo-titulo" placeholder="Digite o título" required />
                        </div>
                        <div class="form-group">
                            <label for="campo-mensagem">Mensagem</label>
                            <textarea class="form-control" id="campo-mensagem" rows="3" placeholder="Digite o aviso..." required></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Adicionar Aviso</button>
                    </form>
                </div>
            </div>
        
            <!-- Lista de avisos -->
            <div id="lista-avisos">
                <!-- Vai ter aviso aqui dentro... -->
            </div>
        </div>
    `;
}

function htmlCardAviso() {
    return `
        <div class="card-body">
            <h5 class="card-title">${aviso.titulo} <small class="text-muted" style="font-size: 0.9em;">${aviso.data}</small></h5>
            <p class="card-text">${aviso.texto}</p>
            <p class="card-text"><small class="text-muted">Postado por: ${aviso.usuario}</small></p>
            <button class="btn btn-danger btn-sm" onclick="excluirAviso(${aviso.id})">Excluir</button>
        </div>
    `
}

function configurarAvisos() {
    document.getElementById("form-aviso").addEventListener("submit", function(evento) {
        evento.preventDefault();

        const tituloAviso = document.getElementById("campo-titulo").value;
        const mensagem = document.getElementById("campo-mensagem").value;
        const dataHora = new Date().toLocaleString("pt-BR");
        const usuario = "Admin"; // Futuramente iremos puxar o nome do usuário por aqui...

        const aviso = {
            id: Date.now(),
            titulo: tituloAviso,
            texto: mensagem,
            usuario: usuario,
            data: dataHora
        };

        listaAvisos.push(aviso);
        atualizarAvisos();

        document.getElementById("form-aviso").reset();
    });
}

function excluirAviso(id) {
    const indice = listaAvisos.findIndex(aviso => aviso.id === id);
    if (indice !== -1) {
        listaAvisos.splice(indice, 1);
        atualizarAvisos();
    }
}

function atualizarAvisos() {
    const containerAvisos = document.getElementById("lista-avisos");
    containerAvisos.innerHTML = "";

    listaAvisos.forEach(aviso => {
        const cardAviso = document.createElement("div");
        cardAviso.className = "card mb-3";
        cardAviso.innerHTML = htmlCardAviso();

        containerAvisos.appendChild(cardAviso);
    });
}