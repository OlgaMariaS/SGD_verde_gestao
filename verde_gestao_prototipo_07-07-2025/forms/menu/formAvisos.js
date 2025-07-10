function criarFormAviso() {
    return `
            <h1>Cadastrar Novo Aviso</h1>
            
            <div class="container mt-4">
            
                <form id="formAviso">
                    <input type="text" id="titulo" class="form-control mb-2" placeholder="Título" required />
                    <textarea id="descricao" class="form-control mb-2" placeholder="Descrição" required></textarea>
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </form>
                
                <div id="dockDiv" class="mt-4"> </div>
            
            </div>
        `;
}

function adicionarNovoAviso(e, form) {
    e.preventDefault();

    const titulo = document.getElementById("titulo").value;
    const descricao = document.getElementById("descricao").value;
    const dockDiv = document.getElementById("dockDiv");

    const novoDiv = document.createElement("div");
    novoDiv.className = "dock-window";

    const tituloDiv = document.createElement("h5");
    tituloDiv.textContent = titulo;

    const conteudoDiv = document.createElement("p");
    conteudoDiv.textContent = descricao;

    novoDiv.appendChild(tituloDiv);
    novoDiv.appendChild(conteudoDiv);
    dockDiv.appendChild(novoDiv);

    form.reset();
}

function inicializarFormAdicionarAvisos() {
    const form = document.getElementById("formAviso");

    form.addEventListener("submit", function (e) {
        adicionarNovoAviso(e, form);
    });
}

function carregarFormAdicionarAvisos(div) {
    div.innerHTML = criarFormAviso();
    inicializarFormAdicionarAvisos();
}