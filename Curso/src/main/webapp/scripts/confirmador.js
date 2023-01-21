/*
 * Confirmar a exclusão do usuário
 */

function confirmar(id) {
    let resposta = confirm("Deseja excluir o contato ?")
    if (resposta === true) {
        window.location.href = "deletar?id=" + id;
    }

}

