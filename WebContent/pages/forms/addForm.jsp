<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add to DB</title>
</head>
<body>

<s:form action="add">
	<s:textfield label="Nome do Examinado" name="nome" />
	<s:textfield label="Nota do Exame 1" name="exame1" />
	<s:textfield label="Nota do Exame 2" name="exame2" />
	<s:submit key="submit" name="submit" />
</s:form>

</body>
</html>