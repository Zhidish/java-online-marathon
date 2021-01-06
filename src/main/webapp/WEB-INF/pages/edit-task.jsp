<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page import="com.softserve.itacademy.model.Task" %><%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 06.01.2021
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.html"%>
<% Task task = (Task)request.getAttribute("task"); %>


<h1>Edit existing Task</h1>
<form action="/edit-task" method="post">
    <table>
        <tr>
            <td>
                <label for="task">Id:</label>
            </td>

            <td>
                <input type="text"  value="<%= task.getId() %>" disabled>
                <input name="id" type="text" id="id" value="<%= task.getId() %>" hidden>
            </td>
        </tr>

        <tr>
            <td>
                <label for="task">Task:</label>
            </td>

            <td>
                <input name="task" type="text" id="task" value="<%= task.getTitle() %>">
            </td>
        </tr>


        <tr>
            <td>
                <label for="priority">Priority:</label>
            </td>


            <td>
                <select id="priority" name="priority">
                    <option selected value="LOW" <%= task.getPriority() == Priority.LOW? "selected" : ""%>>
                        Low
                    </option>
                    <option value="MEDIUM" <%= task.getPriority() == Priority.MEDIUM? "selected" : ""%>>
                        Medium
                    </option>
                    <option value="HIGH" <%= task.getPriority() == Priority.HIGH? "selected" : ""%>>
                        High
                    </option>

                </select>
            </td>
        </tr>


        <tr>
            <td>
                <input type="submit" value="Update">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
