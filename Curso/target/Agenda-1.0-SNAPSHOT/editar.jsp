<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilos/style.css"/>
        <link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon">
        <title>Agenda de Contatos</title>
    </head>
    <body>
        <h1>Editar contato</h1>
        <form action="editado" autocomplete="off">
            <div>
                <label for="iid">Id:</label>
                <input type="text" name="id" id="iid" class="caixa" style="width: auto;border: 1px solid red;" readonly 
                       value="<%out.print(request.getAttribute("id"));%>">
            </div>
            <div>
                <label for="inome">Nome: </label>
                <input required type="text" name="nome" id="inome" class="caixa"
                       value="<%out.print(request.getAttribute("nome"));%>">
            </div>
            <div>
                <label for="ifone">Telefone: </label>
                <input required type="text" name="fone" id="ifone"  class="caixa" style="width: auto"
                       value="<%out.print(request.getAttribute("telefone"));%>">
            </div>
            <div>
                <label for="iemail">E-mail: </label>
                <input type="text" name="email" id="iemail" class="caixa"
                       value="<%out.print(request.getAttribute("email"));%>">
            </div>
            <input type="submit" value="Salvar" class="botao1">
        </form>
    </body>
</html>
