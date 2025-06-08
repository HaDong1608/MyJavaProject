package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import cdi.UserManager;

@Named
@RequestScoped
public class LoginBean {

	@NotNull(message = "必須入力です")
	private String id;

	@NotNull(message = "必須入力です")
	private String password;

	private String errorMsg;

	@Inject
	private UserManager userManager;

	public String login() {
		if (!userManager.login(id, password)) {
			errorMsg = "IDまたはパスワードが間違っています";
			return null;
		}

		String role = userManager.getRole();
		if ("user".equals(role)) {
			return "user.xhtml";
		} else if ("admin".equals(role)) {
			return "admin.xhtml";
		}

		return null;
	}

	public String logout() {
		userManager.logout();
		return "login.xhtml";
	}

	// Getter/Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
