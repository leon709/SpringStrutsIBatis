package leon.ssi.dao.impl;

import java.util.List;
import java.util.Map;

import leon.ssi.dao.LoginDAO;
import leon.ssi.ibatis.IBatisDAOSupport;
import leon.ssi.model.Role;
import leon.ssi.model.User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl extends IBatisDAOSupport<LoginDAO> implements
		LoginDAO {

	@Override
	public int validLogin(Map<String, Object> param) throws Exception {
		SqlSession session = this.getSqlSession();
		try {
			return this.getMapper(LoginDAO.class, session).validLogin(param);
		} catch (Exception e) {
			log.error(this.getClass().getName()+"->validLogin"+e.getMessage());
			throw new Exception(e);
		} finally {
			this.closeSqlSession(session);
		}
	}

	@Override
	public List<User> getUserListByParam(Map<String, String> param)
			throws Exception {
		SqlSession session = this.getSqlSession();
		try {
			return this.getMapper(LoginDAO.class, session).getUserListByParam(param);
		} catch (Exception e) {
			log.error(this.getClass().getName()+"->getUserListByParam"+e.getMessage());
			throw new Exception(e);
		} finally {
			this.closeSqlSession(session);
		}
	}

	@Override
	public List<Role> queryRoleAsList() throws Exception {
		SqlSession session = this.getSqlSession();
		try {
			return this.getMapper(LoginDAO.class, session).queryRoleAsList();
		} catch (Exception e) {
			log.error(this.getClass().getName()+"->queryRoleAsList"+e.getMessage());
			throw new Exception(e);
		} finally {
			this.closeSqlSession(session);
		}
	}

}
