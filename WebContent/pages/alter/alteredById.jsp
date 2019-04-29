<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Altered values</title>
</head>
<body>
	<p>Alterando valores.</p>
	<br>
	<p>Valores anteriores: <s:property value="originalLine" /> </p>
	<p>Valores novos: <s:property value="newLine" /> </p>
</body>
</html>