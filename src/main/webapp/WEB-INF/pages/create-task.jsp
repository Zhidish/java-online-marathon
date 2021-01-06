<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/create-task" method="post">
    <table>
        <tr>
            <td>
                <label for="task">Task:</label>
            </td>


            <td>
                <input name="task" type="text" id="task">
            </td>
        </tr>


        <tr>
            <td>
                <label for="priority">Priority:</label>
            </td>


            <td>
              <select id="priority" name="priority">
                  <option selected value="LOW">
                      Low
                  </option>
                  <option value="MEDIUM">
                      Medium
                  </option>
                  <option value="HIGH">
                      Hight
                  </option>

              </select>
            </td>
        </tr>


        <tr>
            <td>
                <input type="submit" value="submit">
            </td>

            <td><input type="reset" value="reset"></td>
        </tr>
    </table>
</form>


</body>
</html>
