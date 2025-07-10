function criarFormSolicitacoes() {
    return `
        <body>
            <div class="container mt-4">
            
                <!-- Filtros -->
                <form class="form-inline mb-3" id="filtroForm">
                    <label class="mr-2">Filtrar:</label>
                    
                    <input type="text" class="form-control mr-2" id="filtroNome" placeholder="Nome">
                    <input type="text" class="form-control mr-2" id="filtroId" placeholder="Código">
                    
                    <select class="form-control mr-2" id="filtroCategoria">
                        <option value="">Categoria</option>
                        <option>Reembolso</option>
                        <option>Atividade Ext</option>
                        <option>Material</option>
                        <option>Loja Escoteira</option>
                    </select>
                    
                    <input type="text" class="form-control mr-2" id="filtroResponsavel" placeholder="Responsável">
                    
                    <!-- Adicionar posteriormente um seletor de datas :( -->
                    <span class="ml-auto">De 01/02/2025 até 03/06/2025</span>
                </form>
        
                <!-- Tabela -->
                <table class="table table-bordered text-center" id="dadosTabela">
                    <thead class="thead-dark">
                        <tr>
                            <th>CÓDIGO</th>
                            <th>CATEGORIA</th>
                            <th>SOLICITANTE</th>
                            <th>RESPONSÁVEL</th>
                            <th>STATUS</th>
                            <th>DATA DE ABERTURA</th>
                        </tr>
                    </thead>
                  <tbody>
                  </tbody>
                </table>
                
            </div>
        </body>
        `
}

function refazerTabelaSolicitacoes(data) {
    const tbody = document.querySelector('#dadosTabela tbody');
    tbody.innerHTML = '';
    data.forEach(req => {
        const row =
            `<tr class="clickable-row">
                    <td>${req.codigo}</td>
                    <td>${req.categoria}</td>
                    <td>${req.solicitante}</td>
                    <td>${req.responsavel}</td>
                    <td>${req.status}</td>
                    <td>${req.data}</td>
                </tr>`;
        tbody.insertAdjacentHTML('beforeend', row);
    });
}

function inicializarFormSolicitacoes() {
    // Todo: fazer uma função que puxa os dados do banco de dados e bota nesse formato aí.
    const dadosBD = [
        {codigo: '001', categoria: 'REEMBOLSO', solicitante: 'OLGA', responsavel: 'DIRETOR FINANCEIRO', status: 'em atraso', data: '01/04/2025'},
        {codigo: '002', categoria: 'ATIVIDADE EXT', solicitante: 'YASSER', responsavel: 'DIRETOR DE MÉTODOS', status: 'pendente', data: '27/05/2025'},
        {codigo: '003', categoria: 'MATERIAL', solicitante: 'GABRIEL', responsavel: 'DIRETOR DE MÉTODOS', status: 'em análise', data: '20/05/2025'},
        {codigo: '004', categoria: 'ATIVIDADE EXT', solicitante: 'RAFAEL', responsavel: 'PRESIDENTE', status: 'concluída', data: '05/05/2025'},
        {codigo: '005', categoria: 'LOJA ESCOTEIRA', solicitante: 'GABRIEL', responsavel: 'DIRETOR DE GESTÃO ADULTOS', status: 'cancelada', data: '01/05/2025'},
    ];

    document.getElementById('filtroForm').addEventListener('input', () => {
        const nome = document.getElementById('filtroNome').value.toLowerCase();
        const id = document.getElementById('filtroId').value;
        const categoria = document.getElementById('filtroCategoria').value;
        const responsavel = document.getElementById('filtroResponsavel').value.toLowerCase();

        const dadosFiltrados = dadosBD.filter(row => {
            return  (!nome          || row.solicitante.toLowerCase().includes(nome)) &&
                    (!id            || row.codigo.includes(id)) &&
                    (!categoria     || row.categoria.toLowerCase() === categoria.toLowerCase()) &&
                    (!responsavel   || row.responsavel.toLowerCase().includes(responsavel));
        });

        refazerTabelaSolicitacoes(dadosFiltrados);
    });

    refazerTabelaSolicitacoes(dadosBD);
}

function carregarFormSolicitacoes(div) {
    div.innerHTML = criarFormSolicitacoes();
    inicializarFormSolicitacoes();
}