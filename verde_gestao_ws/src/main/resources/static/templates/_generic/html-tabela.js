function htmlTabela() {
    return `
        <div class="container mt-4">
            <h2>Tipos de Documento Cadastrados</h2>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead class="thead-light">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Prazo (dias)</th>
                            <th>Ativado</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody id="tabela-tipos-documento">
                        <!-- Linhas serão inseridas por JS -->
                    </tbody>
                </table>
            </div>
        </div>
    `
}