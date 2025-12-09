let primeiraTelaModulo = {funcaoHtml : null, funcaoCallback : null}

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
        }, TEMPO_FADE);

        if (typeof callback === 'function') callback();
    }, TEMPO_FADE);
}