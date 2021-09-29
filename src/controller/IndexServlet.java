package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Item;
import service.ItemService;
import service.ItemServiceImpl;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// アイテムリストの取得
		ItemService service = new ItemServiceImpl();
		List<Item> list = service.getItemList();

		for(Item item : list) {
			System.out.print(item.getExpDate());
			System.out.println(item.getName());
		}

		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
	}

}
