package org.publicwifi.v1.servlet;

import org.publicwifi.v1.dao.PublicWifiDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/load-wifi")
public class GetWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/WEB-INF/views/load-wifi.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);

        // DB에 저장하는 코드
        try {
            PublicWifiDAO publicWifiDAO = new PublicWifiDAO();
            publicWifiDAO.insertPublicWifi();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("서블릿 정상 작동");

        int dbCode = (int) (Math.random() * 2);  // 종료 코드 (1: 정상, 0: 에러)
        System.out.println(dbCode);

        // 완료 후 redirect
        if (dbCode == 1) {
            request.setAttribute("nums", dbCode);
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("error", dbCode);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}