package leon.ssi.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leon.ssi.model.User;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("JsonAction")
public class JsonAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String message; 
	private User userInfo; 
	private List<User> userInfosList; 
	private Map<String, User> userInfosMap; 
	
	public String returnMessage(){ 
        this.message = "成功返回单个值:杨金德"; 
        return "message"; 
    }  
	
	public String returnUserInfo(){ 
        userInfo = new User(); 
        userInfo.setUserId("10000"); 
        userInfo.setUserName("liumengli"); 
        userInfo.setPassWord("liumengli"); 
        return "userInfo"; 
    }
	
	public String returnList(){ 
        userInfosList = new ArrayList<User>(); 
        User u1 = new User(); 
        u1.setUserId("10000"); 
        u1.setUserName("柳梦黎"); 
        u1.setPassWord("liumengli"); 

        User u3 = new User(); 
        u3.setUserId("10002"); 
        u3.setUserName("云天河"); 
        u3.setPassWord("yuntianhe"); 
       
        userInfosList.add(u1); 
        userInfosList.add(u3); 
        
        userInfosList.add(u3); 
        userInfosList.add(u3); 
        userInfosList.add(u3); 
        return "list"; 

    } 
	
	public String returnMap(){ 
        userInfosMap = new HashMap<String,User>(); 

        User u1 = new User(); 
        u1.setUserId("10000"); 
        u1.setUserName("linyueru"); 
        u1.setPassWord("linyueru"); 

        User u2 = new User(); 
        u2.setUserId("10001"); 
        u2.setUserName("zhaolinger"); 
        u2.setPassWord("zhaolinger"); 

        User u3 = new User(); 
        u3.setUserId("10002"); 
        u3.setUserName("lixiaoyao"); 
        u3.setPassWord("lixiaoyao"); 

        userInfosMap.put(u1.getUserId()+"", u1); 
        userInfosMap.put(u2.getUserId()+"", u2); 
        userInfosMap.put(u3.getUserId()+"", u3); 

        return "map"; 
    }
	
	/**
     * <p>
     *  获得对象，也就是通过表达获得对象(异步的)
     * </P>
     * @return
     */ 
    public String gainUserInfo(){ 
        System.out.println("用户ID："+userInfo.getUserId()); 
        System.out.println("用户名："+userInfo.getUserName()); 
        System.out.println("密码："+userInfo.getPassWord()); 
        return "message"; 
    }
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	public List<User> getUserInfosList() {
		return userInfosList;
	}
	public void setUserInfosList(List<User> userInfosList) {
		this.userInfosList = userInfosList;
	}
	public Map<String, User> getUserInfosMap() {
		return userInfosMap;
	}
	public void setUserInfosMap(Map<String, User> userInfosMap) {
		this.userInfosMap = userInfosMap;
	}
	
	
}
