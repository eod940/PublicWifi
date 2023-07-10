package org.publicwifi.v1.servlet.wifi;

import org.publicwifi.v1.dao.PublicWifiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-wifi")
public class DeleteWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicWifiDAO dao = new PublicWifiDAO();
        try {
            dao.deletePublicWifi();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("delete-wifi 작동완료");
        response.sendRedirect("/");
    }
}