function htmlCadastroUsuario() {
    return `
        <div class="container mt-4">
            <h1>Cadastro de Usuários</h1>
            <form id="form-usuario">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" class="form-control" id="nome" required />
                </div>
                <div class="form-group">
                    <label for="senha">Senha</label>
                    <input type="password" class="form-control" id="senha" required />
                </div>
                <div class="form-group">
                    <label for="administrador">Administrador</label>
                    <select id="administrador" class="form-control">
                        <option value="1">Sim</option>
                        <option value="0">Não</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="tipousuarioid">Tipo de Usuário</label>
                    <select id="tipousuarioid" class="form-control"></select>
                </div>
                <div class="form-group">
                    <label for="secaoid">Seção</label>
                    <select id="secaoid" class="form-control"></select>
                </div>
                <button class="btn btn-primary" type="submit">Salvar</button>
            </form>
        </div>
    `;
}