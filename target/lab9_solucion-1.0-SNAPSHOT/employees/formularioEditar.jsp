<%@page import="java.util.ArrayList"%>
<%@page import="Beans.JobHistory"%>
<%@page import="Beans.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="empleado" type="Employee" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
        <title>Editar empleado</title>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="emp" />
            </jsp:include>
            <div class="row mb-4">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Editar usuario</h1>
                    <hr>
                    <form method="POST" action="EmployeeServlet?action=actualizar">
                        <input type="hidden" name="employee_id" value="<%= empleado.getEmployeeId()%>" />
                        <div class="form-group">
                            <label for="first_name">First Name</label>
                            <input type="text" class="form-control form-control-sm" name="first_name" value="<%= empleado.getFirstName() == null ? "" : empleado.getFirstName()%>">
                        </div>
                        <div class="form-group">
                            <label for="last_name">Last Name</label>
                            <input type="text" class="form-control form-control-sm" name="last_name" value="<%= empleado.getLastName() == null ? "" : empleado.getLastName()%>">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control form-control-sm" name="email" value="<%= empleado.getEmail() == null ? "" : empleado.getEmail()%>">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone number</label>
                            <input type="text" class="form-control form-control-sm" name="phone" value="<%= empleado.getPhoneNumber() == null ? "" : empleado.getPhoneNumber()%>">
                        </div>
                        <div class="form-group">
                            <label for="phone">Hire date</label>
                            <input type="text" class="form-control form-control-sm" name="hire_date" value="<%= empleado.getHireDate() == null ? "" : empleado.getHireDate()%>">
                        </div>
                        <div class="form-group">
                            <label for="phone">Job ID</label>
                            <input type="text" class="form-control" name="job_id" value="<%= empleado.getJob().getJobId() == null ? "" : empleado.getJob().getJobId()%>">
                        </div>
                        <div class="form-group">
                            <label for="salary">Salary</label>
                            <input type="text" class="form-control form-control-sm" name="salary" value="<%= empleado.getSalary().equals("0.0") ? "" : empleado.getSalary()%>">
                        </div>
                        <div class="form-group">
                            <label for="commission">Commision PCT</label>
                            <input type="text" class="form-control form-control-sm" name="commission" value="<%= empleado.getCommissionPct() == null ? "" : empleado.getCommissionPct()%>">
                        </div>
                        <div class="form-group">
                            <label for="manager_id">Manager ID</label>
                            <input type="text" class="form-control form-control-sm" name="manager_id" value="<%= empleado.getManager().getEmployeeId() == 0 ? "" : empleado.getManager().getEmployeeId()%>">
                        </div>
                        <div class="form-group">
                            <label for="department_id">Department ID</label>
                            <input type="text" class="form-control form-control-sm" name="department_id" value="<%= empleado.getDepartment().getDepartmentId() == 0 ? "" : empleado.getDepartment().getDepartmentId()%>">
                        </div>                        
                        <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
                        <input type="submit" value="Guardar" class="btn btn-primary" />
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
            <% if (!listaJobHistory.isEmpty()) { %>
            <div class="row mb-5 mt-4">
                <div class="col-lg-10">
                    <h1 class=''>Historial de trabajo del empleado</h1>
                </div>
            </div>
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th> 
                        <th>Start Date</th> 
                        <th>End Date</th> 
                        <th>Job Title</th> 
                        <th>Department Name</th> 
                    </tr>
                </thead>
                <tbody>
                <%--                        --%>
                <%--                        Inserte su código aquí--%>
                <%--                        --%>
                </tbody>
            </table>
            <% } else { %>
            <div class="p-3 mb-2 bg-info text-white">No presenta cambios laborales</div>
            <% }%>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
