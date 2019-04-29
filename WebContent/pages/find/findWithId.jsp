<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find With ID</title>
</head>
<body>
	<p>
		Procurando por Matricula com valor <s:property value="id" />...
	</p>
	<br>
	<br>
	<p>
		Encontrado linha: <s:property value="r" />
	</p>
</body>
</html>