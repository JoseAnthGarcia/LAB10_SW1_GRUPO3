package Controllers;

import Beans.Country;
import Beans.Employee;
import Daos.CountryDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "CountryServlet", urlPatterns = {"/CountryServlet"})
public class CountryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        CountryDao countryDao = new CountryDao();
        RequestDispatcher view;
        Country country;
        String countryId;

        switch (action) {
            case "crear":
                countryId = request.getParameter("id");
                String countryName = request.getParameter("countryName");
                BigDecimal regionId = new BigDecimal(request.getParameter("regionId"));

                country = countryDao.obtener(countryId);

                if (country == null) {
                    countryDao.crear(countryId, countryName, regionId);
                } else {
                    countryDao.actualizar(countryId, countryName, regionId);
                }
                response.sendRedirect(request.getContextPath() + "/CountryServlet");

                break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        CountryDao countryDao = new CountryDao();
        RequestDispatcher view;
        Country country;
        String countryId;

        switch (action) {
            case "formCrear":

                view = request.getRequestDispatcher("country/newCountry.jsp");
                view.forward(request, response);

                break;
            case "lista":
                ArrayList<Country> lista = countryDao.listar();

                request.setAttribute("lista", lista);

                view = request.getRequestDispatcher("country/listaCountry.jsp");
                view.forward(request, response);
                break;

            case "editar":

                countryId = request.getParameter("id");
                country = countryDao.obtener(countryId);
                if (country == null) {
                    response.sendRedirect(request.getContextPath() + "/CountryServlet");
                } else {
                    request.setAttribute("country", country);
                    view = request.getRequestDispatcher("country/updateCountry.jsp");
                    view.forward(request, response);
                }

                break;
            case "borrar":

                countryId = request.getParameter("id");
                if (countryDao.obtener(countryId) != null) {
                    countryDao.borrar(countryId);
                }
                response.sendRedirect(request.getContextPath() + "/CountryServlet");

                break;
        }
    }
}
