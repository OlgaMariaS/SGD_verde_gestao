function htmlTabelaUsuario() {
    return `
        <div class="container mt-4">
            <h1>Usuários</h1>
            <button class="btn btn-success mb-3" id="btn-novo">Novo Usuário</button>
            
            <table class="table table-hover" id="tabela-usuarios">
                <thead class="thead-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Administrador</th>
                        <th>Tipo Usuário</th>
                        <th>Seção</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    `;
}