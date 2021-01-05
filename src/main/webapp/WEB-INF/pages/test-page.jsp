<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Page</title>
</head>
<body>
<table>

    <h2>Test Page</h2>

    <% Task task = (Task) request.getAttribute("task"); %>

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