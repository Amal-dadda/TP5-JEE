package dao;

import model.User;

public interface UserDAO {
	
	public void registerUser(User user);
	public User loginUser(String username, String password);

}
