<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.JavaBeans" %>
<%@page import="java.util.ArrayList" %>
<%
    ArrayList<JavaBeans> l = (ArrayList<JavaBeans>) request.getAttribute("contatos");
    
%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon">
        <link rel="stylesheet" href="estilos/style.css">
        <title>Agenda de Contatos</title>
    </head>
    <body>
        <h1>Lista de Contatos</h1>    
        <a href="novo.html" class="botao1">Adicionar contato</a>
        <a href="report" class="botao2">Relatório</a>
        <table>
            <thead>
            <% if(l.size() != 0){%>
                <th>Id</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>E-mail</th>
                <th colspan='2'>Opções</th>       
            <%}%>
        </thead>
        <tbody>   
            <%for (JavaBeans c : l) { %>
            <tr>
                <td><%=c.getId()%></td>
                <td><%=c.getNome()%></td>
                <td><%=c.getTelefone()%></td>
                <td><%=c.getEmail()%></td>
                <td><a href="editar?id=<%= c.getId()%>" class="botao1">Editar</a></td>
                <td><a href="javascript: 
                       confirmar(<%= c.getId()%>) " class="botao2">Remover</a></td>
            </tr>
            <%}%>
        </tbody>
    </table>
    <p style="margin-left: 110px ">
        <%  if(l.size() == 0){
            out.println("Sem contatos");
        }
        %>
    </p>
    <script src="scripts/confirmador.js"></script>
</body>
</html>
