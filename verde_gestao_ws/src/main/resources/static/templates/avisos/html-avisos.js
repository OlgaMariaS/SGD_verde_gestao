function htmlAvisos() {
    return `
        <div class="container mt-4">
            <h1>Cadastro de Avisos</h1>
            <p>Os avisos cadastrados poderão ser lidos por todos que acessarem o sistema e aparecerão na aba "Início".</p>
            
            <!-- Formulário de aviso -->
            <div id="formulario-aviso" class="card mb-4">
                <div class="card-header">
                    Os cambos abaixo são obrigatórios
                </div>
                
                <div class="card-body">
                    <form id="form-aviso">
                        <div class="form-group">
                            <label for="campo-titulo">Título do aviso</label>
                            <input type="text" class="form-control" id="campo-titulo" placeholder="Digite o título" required />
                        </div>
                        
                        <div class="form-group">
                            <label for="campo-mensagem">Mensagem</label>
                            <textarea class="form-control" id="campo-mensagem" rows="3" placeholder="Digite o aviso" required></textarea>
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Adicionar Aviso</button>
                    </form>
                </div>
            </div>
        </div>
    `;
}

function htmlCardAviso(aviso) {
    return `
        <div class="card-body" id="card-${aviso.id}">
            <h5 class="card-title">${aviso.titulo} <small class="text-muted" style="font-size: 0.9em;"> - postado em: ${aviso.data}</small></h5>
            <p class="card-text">${aviso.texto}</p>
            <p class="card-text"><small class="text-muted">Assinado por: ${aviso.usuario}</small></p>
            <button id="botao-excluir-${aviso.id}" class="btn btn-danger btn-sm" onclick="excluirAviso(${aviso.id})">Excluir</button>
        </div>
    `;
}