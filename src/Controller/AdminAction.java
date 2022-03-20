package Controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import Model.User;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements SessionAware
{
    private static final long serialVersionUID = -6657385697110463918L;
    private User user;
    
    public String execute() throws Exception {
    	
        final HttpSession session = ServletActionContext.getRequest().getSession(true);
        final String sessionUserRole = (String)session.getAttribute("userRole");
        if (sessionUserRole == null) {
            return "NOTAUTHORISED";
        }
        
        if (sessionUserRole.equalsIgnoreCase("MANAGER")) {
            user = new User((String)session.getAttribute("username"), sessionUserRole, (String)session.getAttribute("realName"));
            return "ADMIN";
        } else {
            user = new User((String)session.getAttribute("username"), sessionUserRole, (String)session.getAttribute("realName"));
            return "NOTADMIN";
        }
        
    }
    
    public void setSession(final Map<String, Object> arg0) {
    	
    }
    
    public String locale() {
        return "LOCALE";
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
}
