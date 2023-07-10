package org.publicwifi.v1.servlet.bookmark;

import org.publicwifi.v1.dao.BookmarkDAO;
import org.publicwifi.v1.dto.BookmarkDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/bookmark-add")
public class AddToBookmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wifi_id = request.getParameter("wifi_id");
        String bookmark_id = request.getParameter("bookmark_group");
        BookmarkDAO dao = new BookmarkDAO();
        BookmarkDTO dto = new BookmarkDTO(Integer.parseInt(bookmark_id), wifi_id);

        try {
            dao.insertWifiToBookmark(dto);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/bookmark-add-list-submit");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}