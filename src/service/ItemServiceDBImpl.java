package service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.Item;

public class ItemServiceDBImpl implements ItemService {

	private DataSource ds;

	// コンストラクタ：DataSourceの設定
	public ItemServiceDBImpl() {
		InitialContext ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myfridge_db");
		}
		catch (NamingException e) {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e1) {
					throw new RuntimeException(e1);
				}
			}
			throw new RuntimeException(e);
		}
		this.ds = ds;
	}

	// ResultSet ⇒ Listへの変換
	private List<Item> mapToList(ResultSet rs) throws SQLException {
		List<Item> list = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt("id");
			Date expDate = rs.getDate("exp_date");
			String name = rs.getString("name");
			list.add(new Item(id, expDate, name));
		}
		return list;
	}


	// java.util.Date ⇒ java.sql.Dateへの変換
	private java.sql.Date getSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}


	/**
	 * 全アイテムを取得
	 */
	@Override
	public List<Item> getItemList() {
		List<Item> list = new ArrayList<>();
		try (var con = ds.getConnection()) {
			String sql = "SELECT * FROM items ORDER BY exp_date";
			list = mapToList(con.prepareStatement(sql).executeQuery());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 賞味期限切れアイテムの一覧を取得
	 */
	@Override
	public List<Item> getExpiredList() {
		List<Item> list = new ArrayList<>();
		try(var con = ds.getConnection()) {
			String sql = "SELECT * FROM items WHERE exp_date < CURDATE() ORDER BY exp_date";
			list = mapToList(con.prepareStatement(sql).executeQuery());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * アイテムを追加
	 */
	@Override
	public void addItem(Item item) {
		try(var con = ds.getConnection()) {
			String sql = "INSERT INTO items (exp_date, name) VALUES (?, ?)";
			var stmt = con.prepareStatement(sql);
			// setDate() では、java.sql.Dateを引数にする必要がある
			stmt.setDate(1, getSqlDate(item.getExpDate()));
			stmt.setString(2, item.getName());
			stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * アイテムを削除
	 */
	@Override
	public void deleteItem(int index) {
		try(var con = ds.getConnection()) {
			String sql = "DELETE FROM items WHERE id = ?";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, index);
			stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
