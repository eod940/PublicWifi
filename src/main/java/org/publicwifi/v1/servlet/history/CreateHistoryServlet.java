package org.publicwifi.v1.servlet.history;

import org.publicwifi.v1.dao.HistoryDAO;
import org.publicwifi.v1.dao.PublicWifiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create-history")
public class CreateHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HistoryDAO dao = new HistoryDAO();
        try {
            dao.createHistory();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("create-wifi 작동완료");
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}