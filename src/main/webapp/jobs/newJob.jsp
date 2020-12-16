<%-- 
    Document   : newJob
    Created on : 13/08/2019, 12:38:09 PM
    Author     : DTI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
        <title>Crear un nuevo trabajo</title>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="job" />
            </jsp:include>
            <div class="row mb-4">
                <div class="col"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Crear un nuevo trabajo</h1>
                    <form method="POST" action="<%=request.getContextPath()%>/JobServlet?action=crear">
                        <div class="form-group">
                            <label for="jobId">Job ID</label>
                            <input type="text" class="form-control" name="id">
                        </div>
                        <div class="form-group">
                            <label for="jobTitle">Job Title</label>
                            <input type="text" class="form-control" name="jobTitle">
                        </div>
                        <div class="form-group">
                            <label for="minSalary">Min Salary</label>
                            <input type="text" class="form-control" name="minSalary">
                        </div>
                        <div class="form-group">
                            <label for="maxSalary">Max Salary</label>
                            <input type="text" class="form-control" name="maxSalary">
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
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
