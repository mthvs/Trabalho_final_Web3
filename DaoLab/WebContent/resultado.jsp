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
	<h1>Departamento</h1>
	<form name="frmDerp" >
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly value="<%out.print(request.getAttribute("id")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="caixa1" value="<%out.print(request.getAttribute("nome")); %>"></td>
			</tr>
		</table>
		<a href="procura.jsp"  class="Botao1">Voltar</a>
	</form>
</body>
</html>