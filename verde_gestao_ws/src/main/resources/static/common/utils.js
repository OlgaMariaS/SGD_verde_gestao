const tempoFade = 300;
const delayFade = 150;
const primeiraTelaModulo = {funcaoHtml : null, funcaoCallback : null}

function registrarPrimeiraTelaModulo(html, callback) {
    primeiraTelaModulo.funcaoHtml = html;
    primeiraTelaModulo.funcaoCallback = callback;
}

function voltarParaPrimeiraTelaModulo() {
    const html = primeiraTelaModulo.funcaoHtml;
    const callback = primeiraTelaModulo.funcaoCallback;

    if (!html || !callback) {
        return;
    }

    atualizarConteudoHtml(html, callback);
}

function atualizarConteudoHtmlModulo(html, callback) {
    registrarPrimeiraTelaModulo(html, callback);
    atualizarConteudoHtml(html, callback);
}

function atualizarConteudoHtml(html, callback) {
    const content = document.getElementById("content");

    content.classList.remove("fade-in");
    content.classList.add("fade-out");

    setTimeout(() => {
        content.innerHTML = html();

        void content.offsetWidth;

        content.classList.remove("fade-out");
        content.classList.add("fade-in");

        setTimeout(() => {
            content.classList.remove("fade-in");
        }, tempoFade);

        if (typeof callback === 'function') callback();
    }, tempoFade);
}


function recuperarLocalmente(tag) {
    return JSON.parse(localStorage.getItem(tag))
}

function salvarLocalmente(tag, dado) {
    localStorage.setItem(tag, JSON.stringify(dado));
}

function requisitarAPI(url, metodo = "GET", body = null) {
    const opcoes = {
        method: metodo,
        headers: {
            "Content-Type": "application/json"
        }
    };

    if (body) {
        opcoes.body = JSON.stringify(body);
    }

    return fetch(url, opcoes)
        .then(resposta => {
            if (!resposta.ok) {
                return resposta.text().then(text => {
                    throw new Error(`Erro: ${resposta.status} - ${text}`);
                });
            }

            // Não parseia JSON vazio...
            const contentType = resposta.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                return resposta.json();
            } else {
                return null; // ou lança undefined...
            }
        })
        .catch(erro => {
            console.error('Erro na requisição:', erro);
            alert(erro.message); // TODO: remover ao finalizar o sistema.
            throw erro;
        });
}

function verificarUsuarioLogado() {
    const usuarioLogado = recuperarLocalmente('usuarioLogado');

    if (usuarioLogado == null) {
        window.location.href = "index.html";
        return;
    }

    fadeInMenu(usuarioLogado);
}

function mostraBotoesAdministrador(usuarioLogado) {
    if (!usuarioLogado.administrador) {
        ['btn-avisos', 'btn-categorias', 'btn-usuarios', 'btn-secoes', 'dropdown-divider-btn', 'btn-consultar-todas', 'header-adm'].forEach(id => {
            const botao = document.getElementById(id);
            if (botao) botao.style.display = 'none';
        });
    }
}

function fadeInMenu(usuarioLogado) {
    const menu = document.getElementById("sidebar");

    setTimeout(() => {
        mostraBotoesAdministrador(usuarioLogado);

        menu.classList.remove("invisible");
        menu.classList.add("fade-in");

        setTimeout(() => {
            menu.classList.remove("fade-in");
        }, tempoFade);
    }, tempoFade);

    atualizarConteudoHtml(htmlInicio, configurarInicio);
}

function deslogarUsuario() {
    salvarLocalmente('usuarioLogado', null)
    verificarUsuarioLogado()
}

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

                    let tds = `<td><input type="checkbox" class="selecionado" data-id="${id}" /></td>`;

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

        if (confirm(`Deseja realmente excluir os ${titulo}s selecionados?`)) {
            Promise.all(selecionados.map(chk => {
                const id = chk.dataset.id;
                return requisitarAPI(`${endpoint}/${id}`, "DELETE");
            })).then(() => carregarTabela());
        }
    });

    carregarTabela();
}