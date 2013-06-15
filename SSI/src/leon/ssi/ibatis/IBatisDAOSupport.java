package leon.ssi.ibatis;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

public class IBatisDAOSupport<T> {

	protected Logger log = Logger.getLogger(this.getClass());

	@Resource
	private SqlSessionFactory sessionFactory;

	private T mapper;

	protected SqlSession getSqlSession() {
		return sessionFactory.openSession();
	}

	public T getMapper(Class<T> clazz) {
		mapper = getSqlSession().getMapper(clazz);
		return mapper;
	}

	public T getMapper(Class<T> clazz, SqlSession session) {
		mapper = session.getMapper(clazz);
		return mapper;
	}

	/**
	 * close SqlSession
	 */
	protected void closeSqlSession(SqlSession sqlSession) throws Exception {
		try {
			if (sqlSession != null) {
				sqlSession.close();
				sqlSession = null;
			}
		} catch (Exception e) {
			log.error("Exception when closing the SQL Session"+e.getMessage());
			
		}
	}

	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
