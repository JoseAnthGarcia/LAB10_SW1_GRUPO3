package Daos;

import Beans.Job;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTI
 */
public class JobDao extends DaoBase {

    public ArrayList<Job> listarTrabajos() {

        ArrayList<Job> lista = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM jobs")) {

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getString(1));
                job.setJobTitle(rs.getString(2));
                job.setMinSalary(rs.getInt(3));
                job.setMaxSalary(rs.getInt(4));
                lista.add(job);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void crearTrabajo(String jobId, String jobTitle, int minSalary, int maxSalary) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "INSERT INTO jobs (job_id,job_title,min_salary,max_salary) "
                        + "VALUES (?,?,?,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, jobId);
                    pstmt.setString(2, jobTitle);
                    pstmt.setInt(3, minSalary);
                    pstmt.setInt(4, maxSalary);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Job obtenerTrabajo(String jobId) {

        Job job = null;
        try {
            String sql = "SELECT * FROM jobs WHERE job_id = ?";
            try (Connection conn = this.getConection();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.setString(1, jobId);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        job = new Job();
                        job.setJobId(rs.getString(1));
                        job.setJobTitle(rs.getString(2));
                        job.setMinSalary(rs.getInt(3));
                        job.setMaxSalary(rs.getInt(4));
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(JobDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return job;
    }

    public void actualizarTrabajo(String jobId, String jobTitle, int minSalary, int maxSalary) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? "
                        + "WHERE job_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, jobTitle);
                    pstmt.setInt(2, minSalary);
                    pstmt.setInt(3, maxSalary);
                    pstmt.setString(4, jobId);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarTrabajo(String jobId) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "DELETE FROM jobs WHERE job_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, jobId);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int obtenerMaxSalary(int employeeId) {

        int maxSalary = 0;

                /*
                Inserte su código aquí
                 */
        return maxSalary;
    }


}

