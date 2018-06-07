package test;
import java.sql.SQLException;

import my.dao.pool.DBManager;


public class DataSourceTest {
	public static void main(String[] args) throws SQLException {
		System.out.println(DBManager.getConnection().getCatalog());
		
		DBManager.closeAllConnection();
	}
}
