package org.publicwifi.v1.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/param")
public class ParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("param 작동중");
//        while (request.getParameterNames().hasMoreElements()) {
//            System.out.println(request.getParameterNames().nextElement());
//        }
        System.out.println(request.getParameter("lat"));
        System.out.println(request.getParameter("lnt"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}