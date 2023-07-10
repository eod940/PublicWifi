package org.publicwifi.v1.servlet;

import org.publicwifi.v1.dao.HistoryDAO;
import org.publicwifi.v1.dao.PublicWifiDAO;
import org.publicwifi.v1.dto.HistoryDTO;
import org.publicwifi.v1.dto.PublicWifiDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "WEB-INF/views/main.jsp";
        PublicWifiDAO publicWifiDAO = new PublicWifiDAO();
        HistoryDAO historyDAO = new HistoryDAO();

        ArrayList<PublicWifiDTO> wifiDTOS = new ArrayList<>();

        double lat = Double.parseDouble(request.getParameter("lat"));
        double lnt = Double.parseDouble(request.getParameter("lnt"));
        int curPage = 0;

        HistoryDTO historyDTO = new HistoryDTO(lat, lnt);

        try {
            wifiDTOS = publicWifiDAO.selectPublicWifi(curPage, lat, lnt);
            historyDAO.insertHistory(historyDTO);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        request.setAttribute("wifiDTOS", wifiDTOS);

        requestDispatcher.forward(request, response);
    }

    public void distanceCheck(double lat, double lnt) {

    }
}