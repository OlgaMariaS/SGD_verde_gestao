function configurarTabelaGenerica({
                                      endpoint,
                                      idTabela,
                                      titulo,
                                      htmlCadastro,
                                      configCadastro,
                                      getId,
                                      campos
                                  }) {
    const tabela = document.querySelector(`#${idTabela} tbody`);
    const btnNovo = document.getElementById("btn-novo");
    const btnEditar = document.getElementById("btn-editar");
    const btnExcluir = document.getElementById("btn-excluir");
    const checkTodos = document.getElementById("check-todos");

    btnNovo.addEventListener("click", () => {
        atualizarConteudoHtml(htmlCadastro, configCadastro);
    });

    function carregarTabela() {
        requisitarAPI(endpoint)
            .then(lista => {
                tabela.innerHTML = "";

                lista.forEach(item => {
                    const id = getId(item);
                    const tr = document.createElement("tr");

                    let tds = id === 0
                        ? `<td></td>`
                        : `<td><input type="checkbox" class="selecionado" data-id="${id}" /></td>`;

                    campos.forEach(c => {
                        let valor = c.render ? c.render(item) : item[c.nome];
                        tds += `<td>${valor}</td>`;
                    });

                    tr.innerHTML = tds;
                    tabela.appendChild(tr);
                });

                checkTodos.checked = false;
                checkTodos.addEventListener("change", () => {
                    const checks = document.querySelectorAll(".selecionado");
                    checks.forEach(chk => {
                        chk.checked = checkTodos.checked;
                        chk.closest("tr").classList.toggle("table-active", chk.checked);
                    });
                });

                document.querySelectorAll(".selecionado").forEach(chk => {
                    chk.addEventListener("change", () => {
                        chk.closest("tr").classList.toggle("table-active", chk.checked);
                    });
                });
            });
    }

    btnEditar.addEventListener("click", () => {
        const selecionados = document.querySelectorAll(".selecionado:checked");
        if (selecionados.length === 0) {
            alert(`Selecione um ${titulo} para editar.`);
        } else if (selecionados.length > 1) {
            alert(`Selecione apenas um ${titulo} para editar.`);
        } else {
            const id = selecionados[0].dataset.id;
            atualizarConteudoHtml(() => htmlCadastro(id), () => configCadastro(id));
        }
    });

    btnExcluir.addEventListener("click", () => {
        const selecionados = Array.from(document.querySelectorAll(".selecionado:checked"));
        if (selecionados.length === 0) {
            alert(`Selecione pelo menos um ${titulo} para excluir.`);
            return;
        }

        if (confirm(`Deseja realmente excluir os ${titulo}s selecionados? Todos os seus dados relacionados e registrados também serão deletados.`)) {
            Promise.all(selecionados.map(chk => {
                const id = chk.dataset.id;
                return requisitarAPI(`${endpoint}/${id}`, "DELETE");
            })).then(() => carregarTabela());
        }
    });

    carregarTabela();
}