package cdi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import model.User;

@Named
@SessionScoped
public class UserManager implements Serializable {

	private Map<String, User> userMap = new HashMap<>();
	private User currentUser;

	public boolean register(String id, String password, String role) {
		if (userMap.containsKey(id))
			return false;
		userMap.put(id, new User(id, password, role));
		return true;
	}

	public boolean login(String id, String password) {
		if (!userMap.containsKey(id))
			return false;
		User user = userMap.get(id);
		if (!user.getPassword().equals(password))
			return false;
		currentUser = user;
		return true;
	}

	public void logout() {
		currentUser = null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public String getRole() {
		return currentUser != null ? currentUser.getRole() : null;
	}

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
