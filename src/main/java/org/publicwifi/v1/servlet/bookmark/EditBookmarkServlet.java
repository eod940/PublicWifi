package org.publicwifi.v1.servlet.bookmark;

import org.publicwifi.v1.dao.BookmarkDAO;
import org.publicwifi.v1.dto.BookmarkDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit-bookmark")
public class EditBookmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "WEB-INF/views/bookmark-edit-submit.jsp";
        String name = request.getParameter("bookmark_name");
        int id = Integer.parseInt(request.getParameter("bookmark_id"));
        int num = Integer.parseInt(request.getParameter("bookmark_num"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        BookmarkDAO dao = new BookmarkDAO();
        BookmarkDTO dto = new BookmarkDTO(id, name, num);
        try {
            dao.updateBookmark(dto);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}