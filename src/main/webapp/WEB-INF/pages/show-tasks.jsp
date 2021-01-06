
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@  page import="com.softserve.itacademy.repository.TaskRepository"%>
<%@ page import="java.util.List"%>
<%@ page import="com.softserve.itacademy.model.Task" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%! int i = 0; %>

<table>

    <tr>
        <td>No.</td>
        <td>Name</td>
        <td>Priority</td>
        <td colspan="3">Operations</td>
    </tr>

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
            <a href="/read-task?id=<%=task.getId()%>">Read

            </a><td>

        <td>
            <a href="/edit-task?id=<%=task.getId()%>">Edit

            </a><td>

        <td>
            <a href="/delete-task?id=<%=task.getId()%>">Delete

            </a><td>


    </tr>
    <% }%>

</table>






</body>
</html>
