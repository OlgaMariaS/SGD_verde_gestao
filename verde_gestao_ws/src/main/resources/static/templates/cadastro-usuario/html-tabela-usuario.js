function htmlTabelaUsuario() {
    return `
        <div class="container mt-4">
            <h1>Usuários</h1>
            <button class="btn btn-success mb-3" id="btn-novo">Novo Usuário</button>
            <button class="btn btn-primary mb-3 ml-2" id="btn-editar">Editar Selecionado</button>
            <button class="btn btn-danger mb-3 ml-2" id="btn-excluir">Excluir Selecionados</button>

            <table class="table table-hover" id="tabela-usuarios">
                <thead class="thead-dark">
                    <tr>
                        <th><input type="checkbox" id="check-todos" /></th>
                        <th>Nome</th>
                        <th>Administrador</th>
                        <th>Tipo Usuário</th>
                        <th>Seção</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    `;
}