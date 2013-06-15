package leon.ssi.ibatis;

import java.io.IOException;
import java.io.Reader;

import javax.sql.DataSource;


import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

/**
 * 
 * IBatis3SQLSessionFactoryBean is responsible for integrating iBatis 3
 * <p>
 * with spring 3. Since all environment configurations have been moved to
 * <p>
 * spring, this class takes the responsibility to get environment information
 * <p>
 * from spring configuration to generate SqlSessionFactory.
 * 
 * @author lifetragedy
 * 
 */
public class IBatisSQLSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {

	private String configLocation;   
    private DataSource dataSource;   
    private SqlSessionFactory sqlSessionFactory;   
    private boolean useTransactionAwareDataSource = true;   
    private String environmentId = "development";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.sqlSessionFactory = this.buildSqlSessionFactory(configLocation);  
	}

	@Override
	public SqlSessionFactory getObject() throws Exception {
		return this.sqlSessionFactory;
	}

	@Override
	public Class<? extends SqlSessionFactory> getObjectType() {
		return  SqlSessionFactory.class; 
	}

	@Override
	public boolean isSingleton() {
		
		return true;
	}

	protected SqlSessionFactory buildSqlSessionFactory(String configLocation)
			throws IOException {
		if (configLocation == null) {
			throw new IllegalArgumentException(
					"configLocation entry is required");
		}
		DataSource dataSourceToUse = this.dataSource;
		if (this.useTransactionAwareDataSource
				&& !(this.dataSource instanceof TransactionAwareDataSourceProxy)) {
			dataSourceToUse = new TransactionAwareDataSourceProxy(
					this.dataSource);
		}

		Environment environment = new Environment(environmentId,
				new IBatisTransactionFactory(dataSourceToUse), dataSourceToUse);

		Reader reader = Resources.getResourceAsReader(configLocation);
		XMLConfigBuilder parser = new XMLConfigBuilder(reader, null, null);
		Configuration config = parser.parse();
		config.setEnvironment(environment);

		return new DefaultSqlSessionFactory(config);
	}
	
	//getter and setter
	public String getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public boolean isUseTransactionAwareDataSource() {
		return useTransactionAwareDataSource;
	}

	public void setUseTransactionAwareDataSource(
			boolean useTransactionAwareDataSource) {
		this.useTransactionAwareDataSource = useTransactionAwareDataSource;
	}

	public String getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(String environmentId) {
		this.environmentId = environmentId;
	}

}
