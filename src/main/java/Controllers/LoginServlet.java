package Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import Beans.Employee;
import Daos.DepartmentDao;
import Daos.EmployeeDao;
import Daos.JobDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");


        switch (action) {
            case "login":
                String inputEmail = request.getParameter("inputEmail");
                String inputPassword = request.getParameter("inputPassword");
                EmployeeDao employeeDao= new EmployeeDao();
                Employee employee = employeeDao.validarUsuarioPasswordHash(inputEmail, inputPassword);
                if(employee!=null){
                    HttpSession session =request.getSession();
                    session.setAttribute("employeeSession", employee);

                    String rol = "";
                    int maxSal = employee.getJob().getMaxSalary();
                    int minSal = employee.getJob().getMinSalary();

                    if(maxSal>15000 || employeeDao.validarJefeDepart(employee.getEmployeeId())){
                        rol = "Top 1";
                    }

                    if(maxSal<=15000 && minSal>8500 && !employeeDao.validarJefeDepart(employee.getEmployeeId())){
                        rol = "Top 2";
                    }

                    if(maxSal<=8500 && minSal>5000 && !employeeDao.validarJefeDepart(employee.getEmployeeId())){
                        rol = "Top 3";
                    }

                    if(maxSal<=5000 && !employeeDao.validarJefeDepart(employee.getEmployeeId())){
                        rol = "Top 4";
                    }

                    session.setAttribute("rol", rol);

                    switch (rol){
                        case "Top 1":
                            response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                            break;
                        case "Top 2":
                            response.sendRedirect(request.getContextPath() + "/JobServlet");
                            break;
                        case "Top 3":
                            response.sendRedirect(request.getContextPath() + "/DepartmentServlet");
                            break;
                        case "Top 4":
                            response.sendRedirect(request.getContextPath() + "/CountryServlet");
                            break;
                    }
                }else{
                    response.sendRedirect(request.getContextPath()+"/LoginServlet?error");
                }
                break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion")!=null?
                request.getParameter("accion"): "login";
        HttpSession session = request.getSession();
        switch (accion){
            case "login":
                Employee employee = (Employee) session.getAttribute("employee");
                if(employee!= null && employee.getEmployeeId()>0){
                    response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
                }
                request.setAttribute("employeeSession", employee);
                request.setAttribute("rol", session.getAttribute("rol"));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "logout":

                session.invalidate();
                response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
                break;


        }

    }
}
