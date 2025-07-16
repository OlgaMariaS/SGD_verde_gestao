function apiRequest(url, method = "GET", body = null) {
    const options = {
        method,
        headers: {
            "Content-Type": "application/json"
        }
    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    return fetch(url, options)
        .then(res => {
            if (!res.ok) throw new Error(`Erro: ${res.status}`);
            return res.json();
        });
}

/*
    DOCUMENTAÇÃO:

    Essa documentação é código que vocês podem copiar e colar em qualquer contexto que
    seja Javascript, ele vai fazer a requisição diretamente pra API e retornar um objeto (geralmente
    um Usuario ou uma Response). Perdão o uso de emojis, Jesus te ama.

    Exemplo de usos pra tudo relacionado com usuários:

    🔎 Busca todos:
    apiRequest("http://localhost:8080/usuarios")
              .then(text => {
                const data = JSON.parse(text);
                console.log("Todos os usuários:", data);
              })
              .catch(err => console.error(err));


    🔎 Busca só um:
    apiRequest(`http://localhost:8080/usuarios/${usuarioId}`)
              .then(text => {
                const usuario = JSON.parse(text);
                console.log("Usuário por ID:", usuario);
              })
              .catch(err => console.error(err));


    🔎 Busca por nome:
    apiRequest(`http://localhost:8080/usuarios/buscarPorNome?nome=${encodeURIComponent(nome)}`)
              .then(text => {
                const usuario = JSON.parse(text);
                console.log("Usuário por nome:", usuario);
              })
              .catch(err => console.error(err));


    ⁉️ Vê se existe:
    apiRequest(`http://localhost:8080/usuarios/existe?nome=${encodeURIComponent(nomeVerificar)}`)
              .then(text => {
                console.log("Existe?", text === "true");
              })
              .catch(err => console.error(err));


    🔑 Faz login:
    apiRequest(`http://localhost:8080/usuarios/verificarLogin?nome=${encodeURIComponent(nomeLogin)}&senha=${encodeURIComponent(senhaLogin)}`)
              .then(text => {
                console.log("Login válido?", text === "true");
              })
              .catch(err => console.error(err));


    🐣 Criar usuário:
    -- Presta atenção que tem que criar um objeto novo de usuário.

    const novoUsuario = {
      administrador: false,
      nome: "Novo Usuário",
      senha: "123456",
      tipoUsuarioId: 2,
      secaoId: 1
    };

    apiRequest("http://localhost:8080/usuarios", "POST", novoUsuario)
              .then(msg => {
                console.log("Resposta ao criar:", msg);
              })
              .catch(err => console.error(err));


    ♻️ Atualizar usuário:

    -- Presta atenção que tem que reutilizar o objeto de usuário.

    const usuarioAtualizado = {
      administrador: true,
      nome: "Usuário Atualizado",
      senha: "novaSenha",
      tipoUsuarioId: 3,
      secaoId: 2
    };

    const idAtualizar = 1;

    apiRequest(`http://localhost:8080/usuarios/${idAtualizar}`, "PUT", usuarioAtualizado)
              .then(msg => {
                console.log("Resposta ao atualizar:", msg);
              })
              .catch(err => console.error(err));


    💀 Deletar usuário:
    apiRequest(`http://localhost:8080/usuarios/${idDeletar}`, "DELETE")
              .then(msg => {
                console.log("Resposta ao deletar:", msg);
              })
              .catch(err => console.error(err));


    👑 Vê se tem administrador no BD:
    apiRequest("http://localhost:8080/existeAdmin")
              .then(text => {
                const boolean = JSON.parse(text);
                console.log("Existe admin:", boolean);
              })
              .catch(err => console.error(err));
 */
