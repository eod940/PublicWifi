package org.publicwifi.v1.servlet.history;

import org.publicwifi.v1.dao.HistoryDAO;
import org.publicwifi.v1.dto.HistoryDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/history")
public class GetHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/WEB-INF/views/history.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        HistoryDAO dao = new HistoryDAO();
        ArrayList<HistoryDTO> list = new ArrayList<>();

        // history 불러오기
        try {
            list = dao.selectHistory();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("history 가져오기");  // 로그

        // 완료 후 forward
        request.setAttribute("historyList", list);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}