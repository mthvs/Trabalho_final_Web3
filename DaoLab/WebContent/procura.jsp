<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Departamentos</title>
<link rel="icon" href="imagens/incon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Procurar Departamento</h1>
	<form name="frmDerp" action="procura">
		<table>
			<tr>
				<td><input type="text" name="id" placeholder="ID" class="caixa2"> <input type="button" value="Procurar" class="Botao1" onclick="Configurar()"></td>
			</tr>
		</table>
	</form>
	<a href="main"  class="Botao1">Voltar</a>
	<script src="scripts/configurar.js"></script>
</body>
</html>