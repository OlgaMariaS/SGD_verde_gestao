var usuarioLogado = []

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
        .then(responseLogin => {
            if (!responseLogin) {
                alert("Usuário ou senha inválidos!");
                return
            }

            usuarioLogado = responseLogin;
            window.location.href = "home.html";
        })
        .catch(erro => {});
}