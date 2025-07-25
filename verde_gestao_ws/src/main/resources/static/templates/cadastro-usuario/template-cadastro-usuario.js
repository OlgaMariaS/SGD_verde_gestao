function configurarCadastroUsuario(id = null) {
    if (id) {
        requisitarAPI(`/usuarios/${id}`)
            .then(usuario => {
                document.getElementById("nome").value = usuario.nome;
                document.getElementById("senha").value = usuario.senha;
                document.getElementById("administrador").value = usuario.administrador ? "1" : "0";
                document.getElementById("tipousuarioid").value = usuario.tipoUsuarioId;
                document.getElementById("secaoid").value = usuario.secaoId;
            });
    }

    document.getElementById("form-usuario").addEventListener("submit", (e) => {
        e.preventDefault();

        const usuario = {
            nome: document.getElementById("nome").value,
            senha: document.getElementById("senha").value,
            administrador: document.getElementById("administrador").value === "1",
            tipoUsuarioId: parseInt(document.getElementById("tipousuarioid").value),
            secaoId: parseInt(document.getElementById("secaoid").value)
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