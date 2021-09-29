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
		// GETパラメータの取得
		String sorted = request.getParameter("sorted");

		// GETパラメータに応じたアイテムリストの取得
		ItemService service = new ItemServiceImpl();
		List<Item> list;
		if(sorted == null) {
			list = service.getItemList();
		}
		else {
			list = service.getExpiredList();
		}

		// リクエストスコープへの格納とフォワード
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け防止

		// 入力値の取得

		// バリデーション

		// アイテムの保存

		// 画面表示
		doGet(request, response);
	}

}
