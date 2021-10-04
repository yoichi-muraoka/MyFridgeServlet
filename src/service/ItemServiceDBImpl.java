package service;

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

	@Override
	public List<Item> getItemList() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Item> getExpiredList() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void addItem(Item item) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteItem(int index) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
