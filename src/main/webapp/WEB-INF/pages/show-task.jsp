<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>info about task </title>
</head>
<body>
<table>
    <%@include file="header.html"%>

    <h1>Read existing task</h1>
    <% Task task = (Task) request.getAttribute("task_1"); %>

    <tr>
        <td>Id: </td>
        <td>
            <strong><%= task.getId()%></strong>
        </td>
    </tr>
    <tr>
        <td>Title: </td>
        <td>
            <strong><%= task.getTitle()%></strong>
        </td>
    </tr>
    <tr>
        <td>Priority: </td>
        <td>
            <strong><%= task.getPriority()%></strong>
        </td>
    </tr>
</table>


</body>
</html>
