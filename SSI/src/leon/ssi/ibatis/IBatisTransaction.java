package leon.ssi.ibatis;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class IBatisTransaction implements Transaction {

	private DataSource dataSource;
	private Connection connection;

	public IBatisTransaction(DataSource dataSource, Connection con,
			boolean autoCommit) {
		this.dataSource = dataSource;
		this.connection = con;
	}

	public Connection getConnection() {
		return connection;
	}

	public void commit() throws SQLException {
	}

	public void rollback() throws SQLException {
	}

	public void close() throws SQLException {
		if (dataSource != null && connection != null) {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
	}

}
