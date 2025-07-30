function configurarLogin() {
    document.getElementById("loginForm").addEventListener("submit", function(e) {
        e.preventDefault();
        const nomeLogin = document.getElementById("username").value;
        const senhaLogin = document.getElementById("password").value;
        requisitarLogin(nomeLogin, senhaLogin);
    });
}

function requisitarLogin(nomeLogin, senhaLogin) {
    requisitarAPI(`/usuarios/verificarLogin?nome=${encodeURIComponent(nomeLogin)}&senha=${encodeURIComponent(senhaLogin)}`)
        .then(usuarioLogado => {
            salvarLocalmente('usuarioLogado', usuarioLogado)
            window.location.href = "home.html";
        })
        .catch(erro => {
            console.error(erro);
            if (erro.message.includes("404")) {
                alert("Usuário ou senha inválidos!");
                return;
            }
        });
}