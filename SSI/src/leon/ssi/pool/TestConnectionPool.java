package leon.ssi.pool;

import java.sql.Connection;

public class TestConnectionPool {
	public static void main(String[] args) {
		SimpleConnectionPool.setUrl("");
		SimpleConnectionPool.setUser("");
		SimpleConnectionPool.setPassword("");

		Connection con = SimpleConnectionPool.getConnection();
		Connection con1 = SimpleConnectionPool.getConnection();
		Connection con2 = SimpleConnectionPool.getConnection();

		// do something with con ...

		try {
			con.close();
		} catch (Exception e) {
		}

		try {
			con1.close();
		} catch (Exception e) {
		}

		try {
			con2.close();
		} catch (Exception e) {
		}

		con = SimpleConnectionPool.getConnection();
		con1 = SimpleConnectionPool.getConnection();
		try {
			con1.close();
		} catch (Exception e) {
		}

		con2 = SimpleConnectionPool.getConnection();
		SimpleConnectionPool.printDebugMsg();

	}
}
