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

@WebServlet("/make-bookmark")
public class MakeBookmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookmarkName = request.getParameter("bookmark-name");
        int bookmarkNum = Integer.parseInt(request.getParameter("bookmark-num"));
        BookmarkDAO dao = new BookmarkDAO();
        BookmarkDTO dto = new BookmarkDTO(bookmarkName, bookmarkNum);

        try {
            dao.makeBookmark(dto);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/bookmark-add-submit");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}