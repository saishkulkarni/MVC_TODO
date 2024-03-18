<%@page isELIgnored="false" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style>
div {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.extra {
	margin-top: 20px;
	margin-left: 305px;
}
</style>
</head>
<body>

	<h1 align="center">This is Home Page</h1>
	<div>
	<h3 style="color: green">${success}</h3>
		<table border="1">
			<tr>
				<th>Task Name</th>
				<th>Task Description</th>
				<th>Created Time</th>
				<th>Status</th>
				<th>Delete</th>
				<th>Edit</th>
			</tr>
			<jstl:forEach var="task" items="${tasks}">
			<tr>
				<th>${task.name}</th>
				<th>${task.description}</th>
				<th>${task.createdTime}</th>
				<th>
				<jstl:if test="${task.status}">
				Completed
				</jstl:if>
				<jstl:if test="${!task.status}">
				<a href="complete?id=${task.id}"><button>Complete</button></a>
				</jstl:if>
				</th>
				<th><a href="delete?id=${task.id}"><button>Delete</button></a></th>
				<th><a href="edit-task?id=${task.id}"><button>Edit</button></a></th>
			</tr>
			</jstl:forEach>
		</table>
	</div>
	<a href="addtask.jsp"><button class="extra">Add Task</button></a>
	<a href="logout"><button class="extra">Logout</button></a>
</body>
</html>