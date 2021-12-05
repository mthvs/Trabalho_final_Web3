<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="models.Department" %>
<%@ page import=" java.util.ArrayList" %>
<%@ page import= "dao.DaoFactory" %>
<%@ page import= "dao.DepartmentDao" %>
<%@ page import= "models.Department" %>

<%
	ArrayList<Department> department = (ArrayList<Department>)
request.getAttribute("departamentos");
%>
<!DOCTYPE html>
<html lang="pt-br"> 
<head>
<meta charset="utf-8">
<title>Departamentos</title>
<link rel="icon" href="imagens/incon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Lista de Departamentos</h1>
	<form name="frmDerp" action="listar">
		<table id="tabela">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%for(int i = 0 ; i<department.size();i++){ %>
					<tr>
						<td><%=department.get(i).getId()%></td>
						<td><%=department.get(i).getName()%></td>
						<td><a href="select?id=<%=department.get(i).getId()%>" class="Botao1">Editar</a>
						<a href="javascript: confirmar(<%=department.get(i).getId()%>)" class="Botao2">Excluir</a></td>
						
					</tr>
				<%} %>

			</tbody>
		</table>
		<p></p>
		<a href="main" class="Botao1">Voltar</a>
	</form>
	<script src="scripts/confirmador.js"></script>
</body>
</html>