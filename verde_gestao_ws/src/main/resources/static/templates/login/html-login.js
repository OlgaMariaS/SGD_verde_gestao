function htmlLogin() {
    return `
        <div class="container d-flex justify-content-center">
            <div class="login-container">
                <h2 class="login-title text-center">Login</h2>
                
                <form id="loginForm">
                    <div class="form-group">
                        <label for="username">Usuário</label>
                        <input type="text" class="form-control" id="username" placeholder="Digite seu usuário" required/>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Senha</label>
                        <input type="password" class="form-control" id="password" placeholder="Digite sua senha" required/>
                    </div>
                    
                    <button type="submit" class="btn btn-primary btn-block">Entrar</button>
                </form>
            </div>
        </div>
    `;
}
