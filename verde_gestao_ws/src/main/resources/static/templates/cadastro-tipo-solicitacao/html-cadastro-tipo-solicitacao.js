function htmlCadastroTipoSolicitacao() {
    return `
        <div class="container mt-4">
            <h1>Cadastro de Tipo de Solicitação</h1>
            <p>Somente um administrador pode ver e cadastrar tipos de solicitação.</p>
        
            <!-- Formulário de seção -->
            <div id="formulario-tipo-solicitacao" class="card mb-4">
                <div class="card-header">
                    Os campos abaixo são obrigatórios
                </div>
        
                <div class="card-body">
                    <form id="form-tipo-solicitacao">
        
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input type="text" class="form-control" id="nome" placeholder="Digite o nome do tipo de solicitação" required />
                        </div>
        
                        <button type="submit" class="btn btn-primary">Salvar</button>
                    </form>
                </div>
            </div>
        </div>
    `
}