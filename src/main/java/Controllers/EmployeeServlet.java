package Controllers;

import Beans.Department;
import Beans.Employee;
import Beans.Job;
import Beans.JobHistory;
import Daos.DepartmentDao;
import Daos.EmployeeDao;
import Daos.JobDao;
import Daos.JobHistoryDao;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

            RequestDispatcher view;
            Employee employee;
            EmployeeDao employeeDao = new EmployeeDao();
            JobDao jobDao = new JobDao();

            switch (action) {
                case "guardar":

                        Employee e = new Employee();
                        e.setFirstName(request.getParameter("first_name"));
                        e.setLastName(request.getParameter("last_name"));
                        e.setEmail(request.getParameter("email"));
                        e.setPhoneNumber(request.getParameter("phone"));
                        e.setHireDate(request.getParameter("hire_date"));
                        Job job = new Job();
                        job.setJobId(request.getParameter("job_id"));
                        e.setJob(job);
                        e.setSalary(new BigDecimal(request.getParameter("salary")));
                        e.setCommissionPct(request.getParameter("commission").equals("") ? null : new BigDecimal(request.getParameter("commission")));
                        Employee manager = new Employee();
                        manager.setEmployeeId(Integer.parseInt(request.getParameter("manager_id")));
                        e.setManager(manager);

                        Department department = new Department();
                        department.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));
                        e.setDepartment(department);

                        employeeDao.guardarEmpleado(e);

                        response.sendRedirect("EmployeeServlet");

                    break;
                case "actualizar":
                        Employee empl = new Employee();
                        empl.setEmployeeId(Integer.parseInt(request.getParameter("employee_id"))); //no olvidar que para actualizar se debe enviar el ID
                        empl.setFirstName(request.getParameter("first_name"));
                        empl.setLastName(request.getParameter("last_name"));
                        empl.setEmail(request.getParameter("email"));
                        empl.setPhoneNumber(request.getParameter("phone"));
                        empl.setHireDate(request.getParameter("hire_date"));
                        Job j = new Job();
                        j.setJobId(request.getParameter("job_id"));
                        empl.setJob(j);
                        empl.setSalary(new BigDecimal(request.getParameter("salary")));
                        empl.setCommissionPct(request.getParameter("commission").equals("") ? null : new BigDecimal(request.getParameter("commission")));
                        Employee m = new Employee();
                        m.setEmployeeId(Integer.parseInt(request.getParameter("manager_id")));
                        empl.setManager(m);

                        Department d = new Department();
                        d.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));
                        empl.setDepartment(d);

                        Employee employeeAntiguo = employeeDao.obtenerEmpleado(empl.getEmployeeId());

                        employeeDao.actualizarEmpleado(empl);

                        if (!employeeAntiguo.getJob().getJobId().equals(empl.getJob().getJobId())) {
                            JobHistoryDao jobHistoryDao = new JobHistoryDao();
                            JobHistory jobHistory = jobHistoryDao.obtenerUltimoJobHistory(empl.getEmployeeId());

                            jobHistoryDao.CrearJobHistory(empl.getEmployeeId(), jobHistory == null ? employeeAntiguo.getHireDate() : jobHistory.getEndDate(), employeeAntiguo.getJob().getJobId(), employeeAntiguo.getDepartment().getDepartmentId());

                        }

                        response.sendRedirect("EmployeeServlet?action=lista");


                    break;
            }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

            RequestDispatcher view;
            Employee employee;
            EmployeeDao employeeDao = new EmployeeDao();
            JobDao jobDao = new JobDao();

            switch (action) {
                case "lista":
                    request.setAttribute("listaEmpleados", employeeDao.listarEmpleados());
                    view = request.getRequestDispatcher("employees/lista.jsp");
                    view.forward(request, response);
                    break;
                case "agregar":
                        request.setAttribute("listaTrabajos", jobDao.listarTrabajos());

                        view = request.getRequestDispatcher("employees/formularioNuevo.jsp");
                        view.forward(request, response);
                    break;

                case "editar":
                        if (request.getParameter("id") != null) {
                            String employeeIdString = request.getParameter("id");
                            int employeeId = 0;
                            try {
                                employeeId = Integer.parseInt(employeeIdString);
                            } catch (NumberFormatException ex) {
                                response.sendRedirect("EmployeeServlet");
                            }

                            Employee emp = employeeDao.obtenerEmpleado(employeeId);

                            if (emp != null) {
                                request.setAttribute("empleado", emp);
                                /*
                                Inserte su código aquí
                                 */

                                view = request.getRequestDispatcher("employees/formularioEditar.jsp");
                                view.forward(request, response);
                            } else {
                                response.sendRedirect("EmployeeServlet");
                            }

                        } else {
                            response.sendRedirect("EmployeeServlet");
                        }

                    break;
                case "borrar":
                        if (request.getParameter("id") != null) {
                            String employeeIdString = request.getParameter("id");
                            int employeeId = 0;
                            try {
                                employeeId = Integer.parseInt(employeeIdString);
                            } catch (NumberFormatException ex) {
                                response.sendRedirect("EmployeeServlet");
                            }

                            Employee emp = employeeDao.obtenerEmpleado(employeeId);

                            if (emp != null) {
                                employeeDao.borrarEmpleado(employeeId);
                            }
                        }

                        response.sendRedirect("EmployeeServlet");

                    break;
                case "est":
                     /*
                Inserte su código aquí
                 */
                    break;
        }
    }
}


