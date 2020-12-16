package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DTI
 */
public abstract class DaoBase {

    public Connection getConection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String username = "root";
        String password = "root";
        String database = "hr";
        String params = "serverTimezone=America/Lima&useSSL=false&allowPublicKeyRetrieval=true";
        String url = "jdbc:mysql://localhost:3306/" + database + "?" + params;

        return DriverManager.getConnection(url, username, password);

    }
}
