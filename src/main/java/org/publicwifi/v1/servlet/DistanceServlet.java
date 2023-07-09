package org.publicwifi.v1.servlet;

import org.publicwifi.v1.dao.PublicWifiDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dist")
public class DistanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "WEB-INF/views/main.jsp";
        PublicWifiDAO dao = new PublicWifiDAO();

        double lat = Double.parseDouble(request.getParameter("lat"));
        double lnt = Double.parseDouble(request.getParameter("lnt"));
        int curPage = 0;

        System.out.println("lnt = " + lnt);
        System.out.println("lat = " + lat);

        try {
            dao.selectPublicWifi(curPage, lat, lnt);
            distanceCheck(lat, lnt);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
//        request.setAttribute("distance", distance);

        requestDispatcher.forward(request, response);
    }

    public void distanceCheck(double lat, double lnt) {

    }
}