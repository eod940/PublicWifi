package org.publicwifi.v1.servlet.wifi;

import org.publicwifi.v1.dao.BookmarkDAO;
import org.publicwifi.v1.dao.PublicWifiDAO;
import org.publicwifi.v1.dto.BookmarkDTO;
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

@WebServlet("/detail")
public class DetailWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/WEB-INF/views/detail.jsp";
        PublicWifiDAO dao = new PublicWifiDAO();
        BookmarkDAO daoB = new BookmarkDAO();
        ArrayList<PublicWifiDTO> list = new ArrayList<>();
        ArrayList<BookmarkDTO> listB = new ArrayList<>();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        String mgrNo = request.getParameter("mgrNo");

        try {
            list = dao.selectDetailPublicWifi(mgrNo);
            listB = daoB.selectBookmark();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // 완료 후 forward
        request.setAttribute("wifiDTOS", list);
        request.setAttribute("bookmarkGroup", listB);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}