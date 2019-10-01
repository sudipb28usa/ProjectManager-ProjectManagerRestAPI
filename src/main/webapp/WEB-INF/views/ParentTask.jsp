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


	<table>
		<tr>
			<td>Parent ID</td>
			<td>Parent Task</td>
			<td>Action</td>

		</tr>
		<c:forEach items="${parentTaskList}" var="parentTask" varStatus="status">

			<tr>
				<td>${parentTask.parent_ID}</td>
				<td>${parentTask.parent_Task}</td>
				<td>
				<a href="<c:url value='deleteParentTask/${parentTaskList[status.index].parent_ID}' />">Delete</a>
				<a href="<c:url value='updateParentTask/${parentTaskList[status.index].parent_ID}' />">Update</a>
					
					
					</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>