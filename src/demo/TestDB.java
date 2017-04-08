package demo;

import java.sql.Connection;

public class TestDB {
	public static void main(String[] args) {
		String sql = "insert into test_mac(id,name) values (?,?)";
		Connection conn = DBManage.getConn();
		DBManage.updateData(sql, conn, new String[]{"1002","wangyi"});
		DBManage.queryDatas(sql, conn, null);
		DBManage.closeConn(conn);
	}
}
