<%@ page import="oriscontrolwork.model.Schedule" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to your homepage</title>
</head>
<body>
<h1>Расписание</h1>
<table>
    <tbody>
    <% for(Schedule s: (List<Schedule>) request.getAttribute("schedule")) {%>
        <tr>
            <td><%= s.getAuditory_num()%> </td>
            <td><%= s.getTime()%></td>
            <td><%= s.getWeekday()%></td>
            <td><%= s.getTeacher_last_name()%></td>
            <td><%= s.getGroup()%></td>
        </tr>
    <%}%>
    </tbody>
</table>
<div class="footer">

    <div id="">
        <%for (Integer number: (List<Integer>)request.getAttribute("countPages")) {%>
            <span>
                <a href="/ControlWork_war_exploded/schedule?page=<%= number%>"><%=number%>&nbsp;</a>

            </span>
        <%}%>
    </div>
</div>
</body>
</html>

