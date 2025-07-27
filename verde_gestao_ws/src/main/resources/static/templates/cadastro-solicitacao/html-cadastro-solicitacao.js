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
                    <label for="tipodocumentoid">Tipo de Documento</label>
                    <select id="tipodocumentoid" class="form-control" required></select>
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
    `;
}