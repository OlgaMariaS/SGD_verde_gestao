function htmlTabelaSolicitacao() {
    return `
        <div class="container mt-4">
            <h1>Solicitações</h1>
            
            <div class="button-group">
                <button class="btn btn-success" id="btn-novo">Nova Solicitação</button>
            </div>

            <table class="table table-hover" id="tabela-solicitacoes">
                <thead class="thead-dark">
                    <tr>
                        <th>Código</th>
                        <th>Ações</th>
                        <th>Solicitante</th>
                        <th>Responsável</th>
                        <th>Tipo</th>
                        <th>Descrição</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    `;
}