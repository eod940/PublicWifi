package org.publicwifi.v1.servlet.bookmark;

import org.publicwifi.v1.dao.BookmarkDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-bookmark")
public class DeleteBookmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "WEB-INF/views/bookmark-del-submit.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        BookmarkDAO dao = new BookmarkDAO();
        int id = Integer.parseInt(request.getParameter("bookmark_id"));
        try {
            dao.deleteBookmark(id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}