package Daos;

import Beans.Department;
import Beans.Employee;
import Beans.Job;
import Dtos.EmpleadosPorRegionDto;
import Dtos.SalarioPorDepartamentoDto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTI
 */
public class EmployeeDao extends DaoBase {

    public ArrayList<Employee> listarEmpleados() {
        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees e \n"
                     + "left join jobs j on (j.job_id = e.job_id) \n"
                     + "left join departments d on (d.department_id = e.department_id)\n"
                     + "left  join employees m on (e.manager_id = m.employee_id)");) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt(1));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setEmail(rs.getString(4));
                employee.setPhoneNumber(rs.getString(5));
                employee.setHireDate(rs.getString(6));
                Job job = new Job();
                job.setJobId(rs.getString(7));
                job.setJobTitle(rs.getString("job_title"));

                employee.setJob(job);
                employee.setSalary(rs.getBigDecimal(8));
                employee.setCommissionPct(rs.getBigDecimal(9));

                Employee manager = new Employee();
                manager.setEmployeeId(rs.getInt("e.manager_id"));
                manager.setFirstName(rs.getString("m.first_name"));
                manager.setLastName(rs.getString("m.last_name"));

                employee.setManager(manager);

                Department department = new Department();
                department.setDepartmentId(rs.getInt(11));
                department.setDepartmentName(rs.getString("d.department_name"));
                employee.setDepartment(department);

                listaEmpleados.add(employee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaEmpleados;
    }

    public Employee obtenerEmpleado(int employeeId) {

        Employee employee = null;

        String sql = "SELECT * FROM employees e WHERE employee_id = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, employeeId);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setEmail(rs.getString(4));
                    employee.setPhoneNumber(rs.getString(5));
                    employee.setHireDate(rs.getString(6));

                    Job job = new Job();
                    job.setJobId(rs.getString(7));
                    employee.setJob(job);

                    employee.setSalary(rs.getBigDecimal(8));
                    employee.setCommissionPct(rs.getBigDecimal(9));

                    Employee manager = new Employee();
                    manager.setEmployeeId(rs.getInt(10));
                    employee.setManager(manager);

                    Department department = new Department();
                    department.setDepartmentId(rs.getInt(11));
                    employee.setDepartment(department);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employee;
    }

    public void guardarEmpleado(Employee employee) {

        String sql = "INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhoneNumber());
            pstmt.setString(5, employee.getHireDate());
            pstmt.setString(6, employee.getJob().getJobId());
            pstmt.setBigDecimal(7, employee.getSalary());
            if (employee.getCommissionPct() == null) {
                pstmt.setNull(8, Types.DECIMAL);
            } else {
                pstmt.setBigDecimal(8, employee.getCommissionPct());
            }
            pstmt.setInt(9, employee.getManager().getEmployeeId());
            pstmt.setInt(10, employee.getDepartment().getDepartmentId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarEmpleado(Employee employee) {

        String sql = "UPDATE employees "
                + "SET first_name = ?, "
                + "last_name = ?, "
                + "email = ?, "
                + "phone_number = ?, "
                + "hire_date = ?, "
                + "job_id = ?, "
                + "salary = ?, "
                + "commission_pct = ?, "
                + "manager_id = ?, "
                + "department_id = ? "
                + "WHERE employee_id = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhoneNumber());
            pstmt.setString(5, employee.getHireDate());
            pstmt.setString(6, employee.getJob().getJobId());
            pstmt.setBigDecimal(7, employee.getSalary());
            if (employee.getCommissionPct() == null) {
                pstmt.setNull(8, Types.DECIMAL);
            } else {
                pstmt.setBigDecimal(8, employee.getCommissionPct());
            }
            pstmt.setInt(9, employee.getManager().getEmployeeId());
            pstmt.setInt(10, employee.getDepartment().getDepartmentId());
            pstmt.setInt(11, employee.getEmployeeId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarEmpleado(int employeeId) {

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employees WHERE employee_id = ?");) {

            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public Employee validarUsuarioPasswordHash(String username, String password) {

        String sql = "select* from employees_credentials where email=? and password_hashed=sha2(?,256);";
        Employee employee = null;
        try(Connection conn = getConection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setString(1,username);
            pstmt.setString(2,password);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    int employeeId = rs.getInt(1);
                    employee = this.obtenerEmpleado(employeeId);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }



    public ArrayList<EmpleadosPorRegionDto> listaEmpleadosPorRegion(){
        ArrayList<EmpleadosPorRegionDto> lista = new ArrayList<>();
        String sql = "select r.region_name, count(c.country_id) as '# empleados'\n" +
                "from employees e\n" +
                "inner join departments d on (e.department_id = d.department_id)\n" +
                "inner join locations l on (l.location_id = d.location_id)\n" +
                "inner join countries c on (c.country_id = l.country_id)\n" +
                "inner join regions r on (c.region_id = r.region_id)\n" +
                "group by r.region_id;";
        try(Connection connection = getConection();
            Statement statement = connection.createStatement();
            ResultSet rs= statement.executeQuery(sql);) {
            while (rs.next()){
                EmpleadosPorRegionDto e = new EmpleadosPorRegionDto();
                e.setNombreRegion(rs.getString(1));
                e.setCantidadEmpleados(rs.getInt(2));
                lista.add(e);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }
    public ArrayList<SalarioPorDepartamentoDto> listaSalarioPorDepartamento(){
        ArrayList<SalarioPorDepartamentoDto> lista = new ArrayList<>();
        String sql = "select department_name,max(e.salary) as 'maxSalary', min(e.salary) as'minSalary',\n" +
                "((max(e.salary)+min(e.salary))/2) as 'promedio'\n" +
                "from employees e\n" +
                "inner join departments d on e.department_id = d.department_id\n" +
                "group by e.department_id;";
        try(Connection connection = getConection();
            Statement statement = connection.createStatement();
            ResultSet rs= statement.executeQuery(sql);) {
            while (rs.next()){




            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }
}
