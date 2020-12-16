<%@page import="Dtos.SalarioPorDepartamentoDto"%>
<%@page import="Dtos.EmpleadosPorRegionDto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<EmpleadosPorRegionDto> listaEmpRegion = (ArrayList<EmpleadosPorRegionDto>) request.getAttribute("listaEmpRegion");
    ArrayList<SalarioPorDepartamentoDto> listaSalario = (ArrayList<SalarioPorDepartamentoDto>) request.getAttribute("listaSalario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
        <title>Estadísticas</title>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="est" />
            </jsp:include>
            <h1 class='mb-5 mt-4'>Estadísticas</h1>
            <hr />
            <h3 class='mb-3'>Cantidad de empleados por región </h3>
            <% for (EmpleadosPorRegionDto emp : listaEmpRegion) {%>
            <div class="row">
                <div class="col">
                    <%--                        --%>
                    <%--                        Inserte su código aquí--%>
                    <%--                        --%>

                </div>
            </div>
            <% } %>
            <hr />
            <h3 class='mb-3'>Salario por departamento</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th> 
                        <th>Departamento</th> 
                        <th>Salario mínimo</th>  
                        <th>Salario promedio</th>
                        <th>Salario máximo</th> 
                    </tr>
                </thead>
                <tbody>
                    <%
                        int i = 1;
                        for (SalarioPorDepartamentoDto dto : listaSalario) {
                    %>
                    <tr>
<%--                        --%>
<%--                        Inserte su código aquí--%>
<%--                        --%>
                    </tr>
                    <% i++;
                        }%>
                </tbody>
            </table>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        </div>
    </body>
</html>
