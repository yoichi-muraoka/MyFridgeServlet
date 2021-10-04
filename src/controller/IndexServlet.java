package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Item;
import service.ItemService;
import service.ItemServiceImpl;
import service.ServiceFactory;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	private ItemService service;


	@Override
	public void init() throws ServletException {
		service = ServiceFactory.createItemService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GETパラメータの取得
		String sorted = request.getParameter("sorted");

		// GETパラメータに応じたアイテムリストの取得
		service = new ItemServiceImpl();
		List<Item> list;
		if(sorted == null) {
			list = service.getItemList();
			// 削除操作用
			request.getSession().removeAttribute("sorted");
		}
		else {
			list = service.getExpiredList();
			// 削除操作用
			request.getSession().setAttribute("sorted", "expired");
		}

		// リクエストスコープへの格納とフォワード
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け防止
		request.setCharacterEncoding("UTF-8");

		// 入力値の取得
		String name = request.getParameter("name");
		String strExpDate = request.getParameter("expDate");
		System.out.println(name);
		System.out.println(strExpDate);

		// バリデーション
		boolean isValidated = true;

		if(name.isBlank()) {
			isValidated = false;
			request.setAttribute("errorName", "アイテム名を入力してください");
		}

		// 賞味期限をDate型に変換
		Date expDate = null;
		if(strExpDate.isBlank()) {
			// 日付が未入力の場合、7日後を賞味期限とする
			var c = Calendar.getInstance();
			c.add(Calendar.DATE, 7);
			expDate = c.getTime();
		}
		else {
			// <input type="date">から取得されるデータ形式に基づいて変換
			var sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				expDate = sdf.parse(strExpDate);
			} catch (ParseException e) {
				isValidated = false;
				request.setAttribute("errorExpDate", "日付の形式が正しくありません");
				e.printStackTrace();
			}
		}

		// アイテムの保存
		if(isValidated) {
			var sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
			request.setAttribute("noticeAddItem", name + "　賞味期限：" + sdf.format(expDate) + " を登録しました");
			service.addItem(new Item(expDate, name));
		}

		// 画面表示
		doGet(request, response);
	}

}
