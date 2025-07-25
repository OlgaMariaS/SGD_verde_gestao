function htmlCadastroSecao() {
    return `
        <div class="container mt-4">
            <h1>Cadastro de Seção</h1>
            <p>Somente um administrador pode ver e cadastrar seções.</p>
        
            <!-- Formulário de seção -->
            <div id="formulario-secao" class="card mb-4">
                <div class="card-header">
                    Os campos abaixo são obrigatórios
                </div>
        
                <div class="card-body">
                    <form id="form-secao">
        
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input type="text" class="form-control" id="nome" placeholder="Digite o nome da seção" required />
                        </div>
        
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                    </form>
                </div>
            </div>
        </div>
    `
}