<% String currentPage = request.getParameter("currentPage");%>

<%@page import="Beans.Employee"%>
<jsp:useBean id="employeeSession" type="Employee" scope="session" />
<jsp:useBean id="rol" type="String" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-md navbar-light bg-light">
    <a class="navbar-brand" href="#">Gestión HR</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item <%=currentPage.equals("cou") ? "active" : ""%>" >                
                <a class="nav-link" href="<%=request.getContextPath()%>/CountryServlet">Country</a>
            </li>
            <li class="nav-item <%=currentPage.equals("dep") ? "active" : ""%>" >                
                <a class="nav-link" href="<%=request.getContextPath()%>/DepartmentServlet">Department</a>
            </li>
            <li class="nav-item <%=currentPage.equals("loc") ? "active" : ""%>" >                
                <a class="nav-link" href="<%=request.getContextPath()%>/LocationServlet">Location</a>
            </li>
            <li class="nav-item <%=currentPage.equals("emp") ? "active" : ""%>" >                
                <a class="nav-link" href="<%=request.getContextPath()%>/EmployeeServlet">Employees</a>
            </li>
            <li class="nav-item <%=currentPage.equals("job") ? "active" : ""%>">
                <a class="nav-link" href="<%=request.getContextPath()%>/JobServlet">Jobs</a>
            </li>
            <li class="nav-item <%=currentPage.equals("est") ? "active" : ""%>">
                <a class="nav-link" href="<%=request.getContextPath()%>/EmployeeServlet?action=est">Estadísticas</a>
            </li>
        </ul>
        <div class="font-italic ml-md-2">
            Bienvenido <%=employeeSession.getFirstName()%> <%=employeeSession.getLastName()%> - <%=rol%>
            (<a href="<%=request.getContextPath()%>/LoginServlet?action=logout">cerrar sesión</a>)
        </div>
    </div>
</nav>
