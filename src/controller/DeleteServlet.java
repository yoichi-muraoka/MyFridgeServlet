package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ItemService;
import service.ItemServiceImpl;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 削除用IDの取得
		String strId = request.getParameter("id");

		// 削除処理
		if(strId != null) {
			int id = Integer.parseInt(strId);
			ItemService service = new ItemServiceImpl();
			service.deleteItem(id);
		}

		response.sendRedirect("index");
	}


}
