<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>To Alter by Id</title>
</head>
<body>

	<p>Selecione uma Matricula para alterar seus valores</p>
	
	<s:form action="alterById">
		<s:textfield label="Matricula" name="id" />
		<s:textfield label="Novo Nome do Examinado:" name="nome" />
		<s:textfield label="Nova Nota do Exame 1:" name="exame1" />
		<s:textfield label="Nova Nota do Exame 2:" name="exame2" />
		<s:submit />
	</s:form>
</body>
</html>