<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<style type="text/css">
	.my-container{
		padding-top: 35px;
	}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="${s:mvcUrl('HC#index').build() }">Casa do Código</a>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="${s:mvcUrl('PC#listar').build() }">Lista de Produtos</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="${s:mvcUrl('PC#form').build() }">Cadastro de Produtos</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			  <li>
			    <a href="#">
			        <security:authentication property="principal" var="usuario"/>
			        Usuário: ${usuario.username}
			    </a>
			  </li>
			</ul>
		</div>

	</nav>
	<div class="container my-container">
		<h1>Lista de Produtos</h1>

		<div>${sucesso}</div>
		<div>${falha}</div>

		<table class="table table-bordered table-hover table-striped">
			<tr>
				<th>Título</th>
				<th>Descrição</th>
				<th>Páginas</th>
			</tr>

			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><a
						href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build() }">${produto.titulo}</a>
					</td>
					<td>${produto.descricao}</td>
					<td>${produto.paginas}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>