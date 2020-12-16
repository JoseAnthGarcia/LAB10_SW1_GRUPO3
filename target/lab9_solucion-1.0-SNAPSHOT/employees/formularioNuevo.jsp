<%@page import="Beans.Job"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean scope="request" id="listaTrabajos" type="ArrayList<Job>" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
        <title>Nuevo empleado</title>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="emp" />
            </jsp:include>
            <div class="row mb-4">
                <div class="col"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Nuevo usuario</h1>
                    <hr>
                    <form method="POST" action="EmployeeServlet?action=guardar">
                        <div class="form-group">
                            <label for="first_name">First Name</label>
                            <input type="text" class="form-control form-control-sm" name="first_name">
                        </div>
                        <div class="form-group">
                            <label for="last_name">Last Name</label>
                            <input type="text" class="form-control form-control-sm" name="last_name">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control form-control-sm" name="email">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone number</label>
                            <input type="text" class="form-control form-control-sm" name="phone">
                        </div>
                        <div class="form-group">
                            <label for="hire_date">Hire date</label>
                            <input type="text" class="form-control form-control-sm" name="hire_date">
                        </div>
                        <div class="form-group">
                            <label for="job_id">Job ID</label>
                            <select name="job_id" class="form-control">
                                <% for (Job job : listaTrabajos) {%>
                                <option value="<%=job.getJobId()%>"><%=job.getJobTitle()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="salary">Salary</label>
                            <input type="text" class="form-control form-control-sm" name="salary">
                        </div>
                        <div class="form-group">
                            <label for="commission">Commision PCT</label>
                            <input type="text" class="form-control form-control-sm" name="commission">
                        </div>
                        <div class="form-group">
                            <label for="manager_id">Manager ID</label>
                            <input type="text" class="form-control form-control-sm" name="manager_id">
                        </div>
                        <div class="form-group">
                            <label for="department_id">Department ID</label>
                            <input type="text" class="form-control form-control-sm" name="department_id">
                        </div>
                        <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
                        <input type="submit" value="Guardar" class="btn btn-primary" />
                    </form>
                </div>
                <div class="col"></div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
