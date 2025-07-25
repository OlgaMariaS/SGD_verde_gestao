function configurarCadastroUsuario(id = null) {
    carregarDropDownsUsuario();

    if (id) {
        requisitarAPI(`/usuarios/${id}`)
            .then(usuario => {
                document.getElementById("nome").value = usuario.nome;
                document.getElementById("senha").value = usuario.senha;
                document.getElementById("administrador").value = usuario.administrador ? "1" : "0";
                document.getElementById("tipousuarioid").value = usuario.tipoUsuario?.tipousuarioid;
                document.getElementById("secaoid").value = usuario.secao?.secaoid;
            });
    }

    document.getElementById("form-usuario").addEventListener("submit", (e) => {
        e.preventDefault();

        const usuario = {
            nome: document.getElementById("nome").value,
            senha: document.getElementById("senha").value,
            administrador: document.getElementById("administrador").value === "1",
            tipoUsuario: {
                tipousuarioid: parseInt(document.getElementById("tipousuarioid").value)
            },
            secao: {
                secaoid: parseInt(document.getElementById("secaoid").value)
            }
        };

        const metodo = id ? "PUT" : "POST";
        const url = id ? `/usuarios/${id}` : "/usuarios";

        requisitarAPI(url, metodo, usuario)
            .then(() => {
                alert("Usuário salvo!");
                atualizarConteudoHtml(htmlUsuarios, configurarUsuarios);
            });
    });
}

function carregarDropDownsUsuario() {
    requisitarAPI("/tipoUsuarios")
        .then(tipos => {
            const select = document.getElementById("tipousuarioid");
            select.innerHTML = tipos.map(tipo =>
                `<option value="${tipo.tipousuarioid}">${tipo.descricao}</option>`
            ).join("");
        });

    requisitarAPI("/secoes")
        .then(secoes => {
            const select = document.getElementById("secaoid");
            select.innerHTML = secoes.map(secao =>
                `<option value="${secao.secaoid}">${secao.descricao}</option>`
            ).join("");
        });
}
