function configurarLogin() {
    document.getElementById("loginForm").addEventListener("submit", function(e) {
        e.preventDefault();
        const nomeLogin = document.getElementById("username").value;
        const senhaLogin = document.getElementById("password").value;
        requisitarLogin(nomeLogin, senhaLogin);
    });
}

function requisitarLogin(nomeLogin, senhaLogin) {
    // Talvez fosse mais prudente criar um sistema de handlers e daí lembrar o Login pela sessão do servidor, mas foda-se.
    requisitarAPI(`/usuarios/verificarLogin?nome=${encodeURIComponent(nomeLogin)}&senha=${encodeURIComponent(senhaLogin)}`)
        .then(usuarioLogado => {
            salvarLocalmente('usuarioLogado', usuarioLogado)
            window.location.href = "home.html";
        })
        .catch(erro => {
            console.error(erro);
            // É, eu sei... é gambiarra.
            if (erro.message.includes("404")) {
                alert("Usuário ou senha inválidos!");
                return;
            }
        });
}