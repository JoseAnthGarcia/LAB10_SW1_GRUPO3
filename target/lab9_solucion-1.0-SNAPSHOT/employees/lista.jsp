<%@page import="Beans.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="listaEmpleados" type="ArrayList<Employee>" scope="request" />
<jsp:useBean id="rol" type="String" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
        <title>Lista empleados</title>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="emp" />
            </jsp:include>
            <div class="row mb-5 mt-4">
                <div class="col-lg-6">
                    <h1 class=''>Lista de empleados</h1>
                </div>
                <% if (rol.equals("Top 1") || rol.equals("Top 2")) {%>
                <div class="col-lg-6 my-auto text-lg-right">
                    <a href="<%= request.getContextPath()%>/EmployeeServlet?action=agregar" class="btn btn-primary">Agregar nuevo empleado</a>
                </div>
                <% } %>
            </div>
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th> 
                        <th>Employee</th> 
                        <th>Email</th> 
                        <th>Job ID</th> 
                        <th>Salary</th> 
                        <th>Commision</th> 
                        <th>Manager ID</th> 
                        <th>Department ID</th> 
                            <% if (rol.equals("Top 1") || rol.equals("Top 3")) { %>
                        <th></th>
                            <% } %>
                            <% if (rol.equals("Top 1") || rol.equals("Top 2")) { %>
                        <th></th>
                            <% } %>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int i = 1;
                        for (Employee e : listaEmpleados) {
                    %>
                    <tr>
                        <td><%= i%></td>
                        <td><%= e.getFirstName() + " " + e.getLastName()%></td>
                        <td><%= e.getEmail()%></td>
                        <td><%= e.getJob().getJobTitle()%></td>
                        <td><%= e.getSalary()%></td>
                        <td><%= e.getCommissionPct() == null ? "Sin comisión" : e.getCommissionPct()%></td>
                        <td><%= e.getManager().getEmployeeId() == 0 ? "Sin jefe" : (e.getManager().getFirstName() + " " + e.getManager().getLastName())%></td>
                        <td><%= e.getDepartment().getDepartmentName()%></td>
                        <% if (rol.equals("Top 1") || rol.equals("Top 3")) {%>  
                        <td><a href="EmployeeServlet?action=editar&id=<%= e.getEmployeeId()%>">Editar</a></td>
                        <% }%>
                        <% if (rol.equals("Top 1") || rol.equals("Top 2")) {%>
                        <td><a href="EmployeeServlet?action=borrar&id=<%= e.getEmployeeId()%>">Borrar</a></td>
                        <% }%> 
                    </tr>
                    <%
                            i++;
                        }
                    %>
                </tbody>


            </table>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        </div>
    </body>
</html>
