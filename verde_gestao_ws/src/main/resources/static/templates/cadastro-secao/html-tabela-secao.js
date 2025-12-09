function htmlTabelaSecao() {
    return `
        <div class="container mt-4">
            <h1>Seções</h1>
            
            <div class="button-group">
                <button class="btn btn-success" id="btn-novo">Nova Seção</button>
                <button class="btn btn-primary ml-2" id="btn-editar">Editar Selecionado</button>
                <button class="btn btn-danger ml-2" id="btn-excluir">Excluir Selecionado(s)</button>
            </div>

            <table class="table table-hover" id="tabela-secao">
                <thead class="thead-dark">
                    <tr>
                        <th><input type="checkbox" id="check-todos" /></th>
                        <th>Nome</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    `;
}