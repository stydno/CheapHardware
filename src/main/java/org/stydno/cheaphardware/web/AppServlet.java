package org.stydno.cheaphardware.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.stydno.cheaphardware.service.AppService;

public class AppServlet extends HttpServlet {

    Integer id;
    String parameter;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession se = request.getSession();
        AppService service = (AppService) se.getAttribute("service");

        if (request.getParameter("create") != null) {
            service.createAdvertisement(Integer.parseInt(request.getParameter("price")), request.getParameter("name"),
                    request.getParameter("phone"), request.getParameter("model"));
        }
        if (request.getParameter("delete") != null) {
            service.deleteAds(Integer.parseInt(request.getParameter("adsId")));
        }
        if (request.getParameter("changePrice") != null) {
            service.changePrice(Integer.parseInt(request.getParameter("adsId")), Integer.parseInt(request.getParameter("newPrice")));
        }
        if (request.getParameter("showAll") != null) {
            se.setAttribute("allAds", service.getAds());
        }

        if (request.getParameter("search") != null) {
            parameter = request.getParameter("parameter");
            if (service.getAdsWithItem(parameter).isEmpty() != true) {
                se.setAttribute("searchAds", service.getAdsWithItem(parameter));
            } else {
                se.setAttribute("searchAds", service.getAdsWithSeller(parameter));
            }
        }
    }

}
