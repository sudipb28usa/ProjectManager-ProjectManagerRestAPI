<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="./">
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>${Header}</h1>
	<br>
	<br> ${message}
	<br>
	<br>


	<form:form method="POST" action="saveParentTask" modelAttribute="parentTask">
		<table>
		
		<tr>
				<td><form:label path="parent_ID">Parent ID</form:label></td>
				<td><form:input path="parent_ID" readonly="true" value="${parentTask.parent_ID}" /></td>
			</tr>
			
			<tr>
				<td><form:label path="parent_Task">Parent Task Name</form:label></td>
				<td><form:input path="parent_Task" value="${parentTask.parent_Task}" /></td>
			</tr>
			 
			<tr>
				<td>
				 
 
				<input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>