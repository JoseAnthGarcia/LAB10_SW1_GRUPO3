package Daos;

import Beans.Country;
import java.math.BigDecimal;
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
public class CountryDao extends DaoBase {

    public ArrayList<Country> listar() {

        ArrayList<Country> lista = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM countries")) {

            while (rs.next()) {
                Country country = new Country();
                country.setCountryId(rs.getString(1));
                country.setCountryName(rs.getString(2));
                country.setRegionId(rs.getBigDecimal(3));
                lista.add(country);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void crear(String countryId, String countryName, BigDecimal regionId) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "INSERT INTO countries (`country_id`, `country_name`, `region_id`) "
                        + "VALUES (?,?,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, countryId);
                    pstmt.setString(2, countryName);
                    pstmt.setBigDecimal(3, regionId);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Country obtener(String countryId) {

        Country country = null;
        try {
            String sql = "SELECT * FROM countries WHERE country_id = ?";
            try (Connection conn = this.getConection();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.setString(1, countryId);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        country = new Country();
                        country.setCountryId(rs.getString(1));
                        country.setCountryName(rs.getString(2));
                        country.setRegionId(rs.getBigDecimal(3));
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return country;
    }

    public void actualizar(String countryId, String countryName, BigDecimal regionId) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "UPDATE countries SET country_name = ?, region_id = ? "
                        + "WHERE country_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, countryName);
                    pstmt.setBigDecimal(2, regionId);
                    pstmt.setString(3, countryId);
                    pstmt.executeUpdate();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrar(String countryId) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "DELETE FROM countries WHERE country_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, countryId);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

