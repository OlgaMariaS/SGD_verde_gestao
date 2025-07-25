function configurarTabelaUsuarios() {
    const tabelaUsuarios = document.querySelector("#tabela-usuarios tbody");
    const btnNovo = document.getElementById("btn-novo");

    btnNovo.addEventListener("click", () => {
        atualizarConteudoHtml(htmlCadastroUsuario, configurarCadastroUsuario);
    });

    requisitarAPI("/usuarios")
        .then(usuarios => {
            tabelaUsuarios.innerHTML = "";

            usuarios.forEach(usuario => {
                const tr = document.createElement("tr");

                tr.innerHTML = `
                    <td>${usuario.nome}</td>
                    <td>${usuario.administrador ? "Sim" : "Não"}</td>
                    <td>${usuario.tipoUsuario?.descricao || "-"}</td>
                    <td>${usuario.secao?.descricao || "-"}</td>
                    <td>
                        <button class="btn btn-sm btn-primary editar" data-id="${usuario.usuarioid}">Editar</button>
                        <button class="btn btn-sm btn-danger excluir" data-id="${usuario.usuarioid}">Excluir</button>
                    </td>
                `;

                tabelaUsuarios.appendChild(tr);
            });

            document.querySelectorAll(".editar").forEach(btn => {
                btn.addEventListener("click", () => {
                    const id = btn.dataset.id;
                    atualizarConteudoHtml(() => htmlCadastroUsuario(id), () => configurarCadastroUsuario(id));
                });
            });

            document.querySelectorAll(".excluir").forEach(btn => {
                btn.addEventListener("click", () => {
                    const id = btn.dataset.id;
                    if (confirm("Deseja excluir?")) {
                        requisitarAPI(`/usuarios/${id}`, "DELETE")
                            .then(() => configurarTabelaUsuarios());
                    }
                });
            });
        });
}