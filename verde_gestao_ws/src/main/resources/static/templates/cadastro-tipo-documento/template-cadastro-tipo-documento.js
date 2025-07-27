function configurarCadastroTipoDocumento(id = null) {
    if (id) {
        requisitarAPI(`/tipoDocumentos/${id}`)
            .then(doc => {
                document.getElementById("nome").value = doc.descricao;
                document.getElementById("prazo").value = doc.prazo_em_dias;
                document.getElementById("ativado").value = doc.inativo ? "0" : "1";
            });
    }

    document.getElementById("form-tipo-documento").addEventListener("submit", (e) => {
        e.preventDefault();

        const novoTipoDocumento = {
            descricao: document.getElementById("nome").value,
            prazoEmDias: parseInt(document.getElementById("prazo").value, 10),
            inativo: document.getElementById("ativado").value === "0" // inativo = true quando "Não"
        };

        const metodo = id ? "PUT" : "POST";
        const url = id ? `/tipoDocumentos/${id}` : "/tipoDocumentos";

        requisitarAPI(url, metodo, novoTipoDocumento)
            .then(() => {
                alert("Tipo de documento salvo!");
                atualizarConteudoHtml(htmlTabelaTipoDocumento, configurarTabelaTipoDocumento);
            });
    });
}
