<%@page import="Beans.Job"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean type="ArrayList<Job>" scope="request" id="lista"  />
<jsp:useBean id="rol" type="String" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
        <title>JSP Page</title>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="job" />
            </jsp:include>
            <div class="row mb-5 mt-4">
                <div class="col-lg-6">
                    <h1 class=''>Lista de trabajos en hr</h1>
                </div>
                <% if (rol.equals("Top 1") || rol.equals("Top 2")) {%>
                <div class="col-lg-6 my-auto text-lg-right">
                    <a href="<%= request.getContextPath()%>/JobServlet?action=formCrear" class="btn btn-primary">Crear Trabajo</a>
                </div>
                <% } %>
            </div>
            <table class="table">
                <tr>
                    <th>#</th> 
                    <th>Job ID</th>
                    <th>Job Name</th>
                    <th>Min Salary</th>
                    <th>Max Salary</th>
                        <% if (rol.equals("Top 1") || rol.equals("Top 3")) { %>
                    <th></th>
                        <% } %>
                        <% if (rol.equals("Top 1") || rol.equals("Top 2")) { %>
                    <th></th>
                        <% } %>
                </tr>
                <%
                    int i = 1;
                    for (Job job : lista) {
                %>
                <tr>
                    <td><%=i%></td>
                    <td><%=job.getJobId()%></td>
                    <td><%=job.getJobTitle()%></td>
                    <td><%=job.getMinSalary()%></td>
                    <td><%=job.getMaxSalary()%></td>
                    <% if (rol.equals("Top 1") || rol.equals("Top 3")) {%>  
                    <td>
                        <a href="<%=request.getContextPath()%>/JobServlet?action=editar&id=<%=job.getJobId()%>">
                            Editar
                        </a>
                    </td>
                    <% }%>
                    <% if (rol.equals("Top 1") || rol.equals("Top 2")) {%>

                    <td>
                        <a href="<%=request.getContextPath()%>/JobServlet?action=borrar&id=<%=job.getJobId()%>">
                            Borrar
                        </a>
                    </td>
                    <% }%> 
                </tr>
                <%
                        i++;
                    }
                %>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>