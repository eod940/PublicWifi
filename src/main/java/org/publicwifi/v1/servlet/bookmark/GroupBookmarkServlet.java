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
import java.util.ArrayList;

@WebServlet("/bookmark-group")
public class GroupBookmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/WEB-INF/views/bookmark-group.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        BookmarkDAO dao = new BookmarkDAO();
        ArrayList<BookmarkDTO> list = new ArrayList<>();
        try {
            list = dao.selectBookmark();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("bookmarkList", list);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}