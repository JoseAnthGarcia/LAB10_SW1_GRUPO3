package Daos;

import Beans.Location;
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
public class LocationDao extends DaoBase {

    public ArrayList<Location> listar() {

        ArrayList<Location> lista = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM locations")) {

            while (rs.next()) {
                Location location = new Location();
                location.setLocationId(rs.getInt(1));
                location.setStreetAddress(rs.getString(2));
                location.setPostalCode(rs.getString(3));
                location.setCity(rs.getString(4));
                location.setStateProvince(rs.getString(5));
                location.setCountryId(rs.getString(6));
                lista.add(location);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void crear(int locationID, String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "INSERT INTO locations (`location_id`, `street_address`, `postal_code`, `city`, `state_province`, `country_id`) "
                        + "VALUES (?,?,?,?,?,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, locationID);
                    pstmt.setString(2, streetAddress);
                    pstmt.setString(3, postalCode);
                    pstmt.setString(4, city);
                    pstmt.setString(5, stateProvince);
                    pstmt.setString(6, countryId);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Location obtener(int locationId) {

        Location location = null;
        try {
            String sql = "SELECT * FROM locations WHERE location_id = ?";
            try (Connection conn = this.getConection();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.setInt(1, locationId);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        location = new Location();
                        location.setLocationId(rs.getInt(1));
                        location.setStreetAddress(rs.getString(2));
                        location.setPostalCode(rs.getString(3));
                        location.setCity(rs.getString(4));
                        location.setStateProvince(rs.getString(5));
                        location.setCountryId(rs.getString(6));
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return location;
    }

    public void actualizar(int locationID, String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "UPDATE locations SET street_address = ?, postal_code = ?, city = ?, state_province = ?, country_id = ? "
                        + "WHERE location_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, streetAddress);
                    pstmt.setString(2, postalCode);
                    pstmt.setString(3, city);
                    pstmt.setString(4, stateProvince);
                    pstmt.setString(5, countryId);
                    pstmt.setInt(6, locationID);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrar(int locationID) {
        try {
            try (Connection conn = this.getConection();) {
                String sql = "DELETE FROM locations WHERE location_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, locationID);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

