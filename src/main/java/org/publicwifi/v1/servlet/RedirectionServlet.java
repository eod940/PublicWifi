package org.publicwifi.v1.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectionServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dbCode = (int) request.getAttribute("dbCode");
        String redirectPath = "/";

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirectPath);

        if (dbCode == 1) {
            out.println("<script>alert('불러오기 완료')</script>");

            // 완료 후 forward
            requestDispatcher.forward(request, response);
        } else {
            out.println("<script>alert('불러오기 실패!!!')</script>");

            // 완료 후 forward
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}