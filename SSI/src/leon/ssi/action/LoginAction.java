package leon.ssi.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import leon.ssi.model.Role;
import leon.ssi.model.User;
import leon.ssi.service.LoginService;
import leon.ssi.util.Constants;
import leon.ssi.util.StringUtil;
import leon.ssi.util.session.UserSessionInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("LoginAction")
public class LoginAction extends ActionSupport{

	protected final Log logger = LogFactory.getLog(getClass());
	private List<User> userList;
	private List<Role> roleList;
	
	private User currUser;
	
	@Resource
	LoginService loginService;
	private String userId;
	private String userName;
	private String passWord;
	
	private String roleId;
	
	private String[] comments;
	
	public String login() throws Exception{
		try{
			System.out.println("login...");
			
			User u1 = new User("Leon","leon709","a001");
			User u2 = new User("Mike","mike1234","a002");
			User u3 = new User("Goly","goly123","a003");
			User u4 = new User("Leon","leon709","a001");
			User u5 = new User("Mike","mike1234","a002");
			User u6 = new User("Goly","goly123","a003");
			
			userList = new ArrayList<User>(); 
			userList.add(u1);
			userList.add(u2);
			userList.add(u3);
			userList.add(u4);
			userList.add(u5);
			userList.add(u6);
			
//			List<User> list = new ArrayList<User>();
//			list.add(new User("adsfa","asdf","adsf"));
//			list.add(new User("hky","asdf89","svfggd"));
//			
//			ValueStack vs = ActionContext.getContext().getValueStack();
//			vs.setValue("userList", list);
//			vs.setValue("hello", "HelloWorld");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String doLogin()throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		String result=null;
		try{
			if (loginService.login(userName, passWord)) {
				UserSessionInfo uinfo = new UserSessionInfo();
				uinfo.setLoginId(userName);
				session.setAttribute(Constants.USER_SESSION, uinfo);
				
				currUser = new User(userName,passWord,null);
				
				Map<String,String> param = new HashMap<String,String>();
				userList = loginService.getUserListByParam(param);
				roleList = loginService.queryRoleAsList();
				
				result = SUCCESS;
			} else {
				ServletActionContext.getRequest().setAttribute("loginCode", "101");
				result=INPUT;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String searchUser()throws Exception{
		try {
			Map<String,String> param = new HashMap<String,String>();
			if(!StringUtil.isEmpty(userName)){
				param.put("userName", userName);
			}
			if(!StringUtil.isEmpty(userId)){
				param.put("userId", userId);
			}
			if(!StringUtil.isEmpty(roleId)){
				param.put("roleId", roleId);
			}
			userList = loginService.getUserListByParam(param);
			roleList = loginService.queryRoleAsList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String welcome(){
		int cmCount = Integer.parseInt(ServletActionContext.getRequest().getParameter("cmCount"));
		
		if(cmCount>0){
			String[] cm = ServletActionContext.getRequest().getParameterValues("comments");
			for(String s : cm){
				System.out.println(s);
			}
		}
		
		return SUCCESS;
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public User getCurrUser() {
		return currUser;
	}

	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}

	public String[] getComments() {
		return comments;
	}

	public void setComments(String[] comments) {
		this.comments = comments;
	}
}
