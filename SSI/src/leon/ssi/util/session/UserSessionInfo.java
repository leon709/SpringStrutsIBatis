package leon.ssi.util.session;

import java.io.Serializable;

public class UserSessionInfo implements Serializable {
	private static final long serialVersionUID = 1l;
	private String loginId = "";

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}