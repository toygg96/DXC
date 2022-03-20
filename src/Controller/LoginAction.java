package Controller;

import com.opensymphony.xwork2.ActionSupport;
import Model.User;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware {

	// Declaring all the variables
	private static final long serialVersionUID = -6657385697110463918L;
	private String username;
	private String pass;
	private String errorMessage;
	private User user;
	private SessionMap<String, Object> sessionMap;

	// Struts2 will call execute() when the user clicks on the login button after
	// filling up the login form in login.jsp
	public String execute() throws Exception {

		final HttpSession session = ServletActionContext.getRequest().getSession(true);
		final String sessionUserRole = (String) session.getAttribute("userRole");
		
		// See if the user is already logged in
		if (sessionUserRole != null) {
			user = new User((String) session.getAttribute("username"), sessionUserRole,
					(String) session.getAttribute("realName"));
			return "SUCCESS";
		}

		if (username == null) {
			errorMessage = "URL Manipulation to profile page is not allowed.";
			return "FAILURE";
		}

		user = new User(username, pass);
		// Calls the authenticate function
		user = user.isAuthenticated();
		
		// if the user is successfully authenticated,
		// it will return SUCCESS which struts2 will resolve it to display success.jsp (defined in struts.xml)
		if (user.getUserRole() != null) {
			sessionMap.put((String) "username", (String) user.getUsername());
			sessionMap.put((String) "userRole", (String) user.getUserRole());
			sessionMap.put((String) "realName", (String) user.getRealName());
			return "SUCCESS";
		}
		
		// if the user is not successfully authenticated,
		// it will return FAILURE which struts2 will resolve it to display error message on login.jsp (defined in struts.xml) 
		errorMessage = "Invalid userid or password.";
		return "FAILURE";

	}

	@Override
	public void setSession(final Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}

	public String logout() {
		sessionMap.remove((String) "userRole");
		sessionMap.invalidate();
		return "LOGOUT";
	}

	public String locale() {
		return "LOCALE";
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(final String pass) {
		this.pass = pass;
	}

	public User getUser() {
		return user;
	}
}