function htmlLogin() {
    return `
        <div className="container d-flex justify-content-center">
            <div className="login-container">
                <h2 className="login-title text-center">Login</h2>
                
                <form id="loginForm">
                    <div className="form-group">
                        <label htmlFor="username">Usuário</label>
                        <input type="text" className="form-control" id="username" placeholder="Digite seu usuário" required/>
                    </div>
                    
                    <div className="form-group">
                        <label htmlFor="password">Senha</label>
                        <input type="password" className="form-control" id="password" placeholder="Digite sua senha" required/>
                    </div>
                    
                    <button type="submit" className="btn btn-primary btn-block">Entrar</button>
                </form>
            </div>
        </div>
    `
}