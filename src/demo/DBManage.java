package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManage {
    private static String url;
    private static String user;
    private static String password;
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// 加载Oracle驱动程序
        System.out.println("开始尝试连接数据库！");
        url = "jdbc:oracle:thin:@192.168.1.104:1521:orcl";
        user = "scott";// 用户名,系统默认的账户名
        password = "wangyi";// 你安装时选设置的密码
	}
	/**
	 * 获取数据库链接
	 * @return
	 */
	public static Connection getConn(){
		Connection conn = null;// 创建一个数据库连接
	    try
	    {
	        conn = DriverManager.getConnection(url, user, password);// 获取连接
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		return conn;
	}
	
	/**
	 * 更新数据
	 * @param sql
	 * @param conn
	 * @param params
	 */
	public static void updateData(String sql,Connection conn,String[] params){
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setString(i+1, params[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询数据
	 * @param sql
	 * @param conn
	 * @param params
	 */
	public static void queryDatas(String sql,Connection conn,String[] params){
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from test_mac");
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2);
				System.out.print(id+" ");
				System.out.println(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭链接
	 * @param conn
	 */
	public static void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
