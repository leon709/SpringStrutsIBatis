package leon.ssi.dao;

import java.util.List;
import java.util.Map;

import leon.ssi.model.Role;
import leon.ssi.model.User;

public interface LoginDAO {

	public int validLogin(Map<String, Object> paraMap) throws Exception;

	public List<User> getUserListByParam(Map<String, String> param)throws Exception;
	
	public List<Role> queryRoleAsList()throws Exception;
}
