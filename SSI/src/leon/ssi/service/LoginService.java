package leon.ssi.service;

import java.util.List;
import java.util.Map;

import leon.ssi.model.Role;
import leon.ssi.model.User;

public interface LoginService {
	public boolean login(String loginId, String loginPwd) throws Exception;
	
	public List<User> getUserListByParam(Map<String,String> param) throws Exception;
	
	public List<Role> queryRoleAsList()throws Exception;
}
