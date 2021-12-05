<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Departamentos</title>
<link rel="icon" href="imagens/incon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1> Editar Departamento</h1>
	<form name="frmDerp" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly value="<%out.print(request.getAttribute("id")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="caixa1" value="<%out.print(request.getAttribute("nome")); %>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="Validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>