package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import cdi.UserManager;

@Named
@RequestScoped
public class RegisterBean {

	@NotNull(message = "必須入力です")
	@Size(max = 32, message = "32文字以内で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9\\-_]+$", message = "半角英数字と記号(-_)で入力してください")
	private String id;

	@NotNull(message = "必須入力です")
	@Size(max = 32, message = "32文字以内で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "半角英数字で入力してください")
	private String password;

	@NotNull(message = "必須入力です")
	private String confirmPassword;

	@NotNull(message = "ロールを選択してください")
	private String role;

	@Inject
	private UserManager userManager;

	private String errorMsg;

	public String register() {
		if (!password.equals(confirmPassword)) {
			errorMsg = "パスワードが一致しません";
			return null;
		}
		if (!userManager.register(id, password, role)) {
			errorMsg = "すでに存在するIDです";
			return null;
		}
		return "registerSuccess.xhtml?faces-redirect=true";
	}

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	// getter & setter

}
