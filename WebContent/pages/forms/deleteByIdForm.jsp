<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete By Id</title>
</head>
<body>

<s:form action="deleteById">
	<s:textfield key="id" />
	<s:submit key="submit" name="submit" />
</s:form>

</body>
</html>