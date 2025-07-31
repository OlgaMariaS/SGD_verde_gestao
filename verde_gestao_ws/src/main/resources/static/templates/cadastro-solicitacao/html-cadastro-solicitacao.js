function htmlCadastroSolicitacao() {
    return `
        <div class="container mt-4">
            <h1>Cadastro de Solicitações</h1>
            <form id="form-solicitacao">
                <div class="form-group">
                    <label for="tiposolicitacaoid">Tipo de Solicitação</label>
                    <select id="tiposolicitacaoid" class="form-control" required></select>
                </div>
                
                <div class="form-group">
                    <div class="me-2">
                        <label for="tipodocumentoid">Tipo de Documento</label>
                        <select id="tipodocumentoid" class="form-control"></select>
                    </div>
                </div>
                    
                <!-- Mostrar apenas se a solicitação já foi salva -->
                <div id="area-anexar-documentos" class="form-group d-none flex-column">
                    <div class="me-2">
                        <label for="arquivo">Arquivo</label>
                        <div class="custom-file">
                            <input type="file" id="arquivo" class="custom-file-input" accept="application/pdf">
                            <label class="custom-file-label" for="arquivo">Escolher arquivo</label>
                        </div>
                    </div>
                
                    <div class="me-2 mt-2">
                        <button type="button" class="btn btn-primary btn-sm" onclick="anexarDocumento()">Adicionar Documento</button>
                    </div>
                </div>
                
                <!-- Mostrar se a solicitação ainda não tiver sido salva -->
                <div id="mensagem-salvar-primeiro" class="alert alert-warning mt-2" style="display: block;">
                    Salve a solicitação antes de anexar documentos.
                </div>

                <div class="form-group">
                    <label for="responsavelid">Responsável</label>
                    <select id="responsavelid" class="form-control" required></select>
                </div>

                <div class="form-group">
                    <label for="descricao">Descrição</label>
                    <textarea class="form-control" id="descricao" rows="3" required></textarea>
                </div>
                
                <button type="submit" name="acao" value="salvar" class="btn btn-primary">Salvar</button>
                <button type="submit" name="acao" value="resolver" class="btn btn-success">Marcar Como Resolvida</button>
                <button type="submit" name="acao" value="cancelar" class="btn btn-danger">Marcar Como Cancelada</button>
            </form>
        </div>
        
        <div class="container mt-4">            
            <div id="lista-documentos">
                <!-- Vai ter documentos aqui dentro... -->
            </div>
        </div>            
    `;
}

function htmlCardDocumento(documento) {
    return `
        <div class="card mb-3">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h5 class="card-title mb-1">
                    Anexo - <strong>${documento.tipodocumento.descricao}</strong>
                    </h5>
                </div>
                <div>
                    <button class="btn btn-primary btn-sm btn-baixar"
                        data-documento-id="${documento.documentoid}"
                        data-documento-tipo="${documento.tipodocumento.descricao}">
                        Baixar
                    </button>
                    <button class="btn btn-danger btn-sm" onclick="deletarDocumento(${documento.documentoid})">
                        Excluir
                    </button>
                </div>
            </div>
        </div>
    `;
}
