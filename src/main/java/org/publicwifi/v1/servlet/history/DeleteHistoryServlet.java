package org.publicwifi.v1.servlet.history;

import org.publicwifi.v1.dao.HistoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-history")
public class DeleteHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HistoryDAO dao = new HistoryDAO();
        int history_id = Integer.parseInt(request.getParameter("history_id"));
        try {
            dao.deleteHistory(history_id);
            System.out.println("history_id = " + history_id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("delete-history 작동완료");
        response.sendRedirect("/history");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}