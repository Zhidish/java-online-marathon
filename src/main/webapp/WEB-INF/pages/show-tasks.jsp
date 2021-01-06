
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@  page import="com.softserve.itacademy.repository.TaskRepository"%>
<%@ page import="java.util.List"%>
<%@ page import="com.softserve.itacademy.model.Task" %>
<html>
<head>
    <title>Title</title>

    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%@include file="header.html"%>
<h1>List of Tasks</h1>

<%! int i = 0; %>
<table>

    <tr>
        <td>No.</td>
        <td>Name</td>
        <td>Priority</td>
        <td colspan="3">Operations</td>
    </tr>
    <% i = 0; %>
    <% for(Task task:(List<Task>) request.getAttribute("tasks")){ %>

    <tr>
        <td>
          <strong><%= ++i%></strong>
        </td>
        <td>
            <%=task.getTitle()%>
        </td>
        <td>
            <%= task.getPriority()%>
        </td>

        <td>
            <a href="${pageContext.request.contextPath}/read-task?id=<%=task.getId()%>">Read</a>
        </td>

        <td>
            <a href="/edit-task?id=<%=task.getId()%>">Edit</a>
        </td>

        <td>
            <a href="/delete-task?id=<%=task.getId()%>">Delete</a>
        </td>

    </tr>
    <% }%>

</table>






</body>
</html>
