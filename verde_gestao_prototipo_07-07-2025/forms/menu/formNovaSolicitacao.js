function criarFormNovaSolicitacao() {
    return `
        <h1>Cadastrar Nova Solicitação</h1>

        <body>
            <div class="container mt-4">
                <form>
                
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="categoriaDocumento">Categoria de documento:</label>
                            <select id="categoriaDocumento" class="form-control">
                                <option selected>Selecione</option>
                                <option>Opção 1</option>
                                <option>Opção 2</option>
                            </select>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <label for="responsavel">Responsável:</label>
                            <select id="responsavel" class="form-control">
                                <option selected>Selecione</option>
                                <option>Responsável 1</option>
                                <option>Responsável 2</option>
                            </select>
                        </div>
                    </div>
        
                    <div class="form-group">
                        <textarea class="form-control" rows="5" placeholder="Digite aqui o detalhamento da solicitação..."></textarea>
                    </div>
        
                    <button type="submit" class="btn btn-primary btn-block">Solicitar</button>
                    
                </form>
            </div>
        </body>
    `
}

function inicializarFormNovaSolicitacao() {

}

function carregarFormNovaSolicitacao(div) {
    div.innerHTML = criarFormNovaSolicitacao();
    inicializarFormNovaSolicitacao();
}