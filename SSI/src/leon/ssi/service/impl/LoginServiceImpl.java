package leon.ssi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import leon.ssi.dao.LoginDAO;
import leon.ssi.model.Role;
import leon.ssi.model.User;
import leon.ssi.service.LoginService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	private Log logger = LogFactory.getLog(this.getClass());
	@Resource
	private LoginDAO loginDAO;

	public boolean login(String loginId, String loginPwd) throws Exception {
		boolean answer = false;
		int result = 0;
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("loginId", loginId);
			paraMap.put("loginPwd", loginPwd);
			result = loginDAO.validLogin(paraMap);
		} catch (Exception e) {
			result = 0;
			logger.error("login error:" + e.getMessage(), e);
		}
		if (result == 1) {
			answer = true;
		} else {
			answer = false;
		}
		return answer;
	}

	@Override
	public List<User> getUserListByParam(Map<String, String> param)
			throws Exception {
		
		return loginDAO.getUserListByParam(param);
	}

	@Override
	public List<Role> queryRoleAsList() throws Exception {
		
		return loginDAO.queryRoleAsList();
	}

}
