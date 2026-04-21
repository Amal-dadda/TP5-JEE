package service;

import model.User;

public interface UserService {
	
	public void registerUser(User user);
	public User loginUser(String username, String password);

}
