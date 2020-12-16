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
                    session.setAttribute("rol",employee);
                    response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
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
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("includes/login.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "logout":

                session.invalidate();
                response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
                break;


        }

    }
}
