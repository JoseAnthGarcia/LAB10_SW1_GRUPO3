package Controllers;

import Beans.Employee;
import Beans.Location;
import Daos.LocationDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


                String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

                LocationDao locationDao = new LocationDao();
                RequestDispatcher view;
                Location location;
                int locationId;

                switch (action) {
                    case "crear":
                            locationId = Integer.parseInt(request.getParameter("id"));
                            String streetAddress = request.getParameter("streetAddress");
                            String postalCode = request.getParameter("postalCode");
                            String city = request.getParameter("city");
                            String stateProvince = request.getParameter("stateProvince");
                            String countryId = request.getParameter("countryId");

                            location = locationDao.obtener(locationId);

                            if (location == null) {
                                locationDao.crear(locationId, streetAddress, postalCode, city, stateProvince, countryId);
                            } else {
                                locationDao.actualizar(locationId, streetAddress, postalCode, city, stateProvince, countryId);
                            }
                            response.sendRedirect(request.getContextPath() + "/LocationServlet");
                        break;
                }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

                LocationDao locationDao = new LocationDao();
                RequestDispatcher view;
                Location location;
                int locationId;

                switch (action) {
                    case "formCrear":
                            view = request.getRequestDispatcher("location/newLocation.jsp");
                            view.forward(request, response);
                        break;
                    case "lista":
                        ArrayList<Location> lista = locationDao.listar();

                        request.setAttribute("lista", lista);

                        view = request.getRequestDispatcher("location/listaLocation.jsp");
                        view.forward(request, response);
                        break;

                    case "editar":
                            locationId = Integer.parseInt(request.getParameter("id"));
                            location = locationDao.obtener(locationId);
                            if (location == null) {
                                response.sendRedirect(request.getContextPath() + "/LocationServlet");
                            } else {
                                request.setAttribute("location", location);
                                view = request.getRequestDispatcher("location/updateLocation.jsp");
                                view.forward(request, response);
                            }
                        break;
                    case "borrar":
                            locationId = Integer.parseInt(request.getParameter("id"));
                            if (locationDao.obtener(locationId) != null) {
                                locationDao.borrar(locationId);
                            }
                            response.sendRedirect(request.getContextPath() + "/LocationServlet");
                        break;
                }
        }
    }


