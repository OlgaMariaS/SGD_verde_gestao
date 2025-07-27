function htmlCadastroTipoDocumento() {
    return `
        <div class="container mt-4">
            <h1>Cadastro de Tipo de Documento</h1>
            <p>Somente um administrador pode ver e cadastrar tipos de documentos.</p>

            <!-- Formulário de tipo de documento -->
            <div id="formulario-tipo-documento" class="card mb-4">
                <div class="card-header">
                    Os campos abaixo são obrigatórios
                </div>

                <div class="card-body">
                    <form id="form-tipo-documento">
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input type="text" class="form-control" id="nome" placeholder="Digite o nome do tipo de documento" required />
                        </div>

                        <div class="form-group">
                            <label for="prazo">Prazo (em dias)</label>
                            <input type="number" class="form-control" id="prazo" placeholder="Ex: 30" min="1" required />
                        </div>

                        <div class="form-group">
                            <label for="ativado">Ativado</label>
                            <select class="form-control" id="ativado" required>
                                <option value="" disabled selected>Selecione uma opção</option>
                                <option value="1">Sim</option>
                                <option value="0">Não</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary">Salvar</button>
                    </form>
                </div>
            </div>
        </div>
    `;
}