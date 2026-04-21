package service;

import dao.UserDAO;
import dao.UserImp;
import model.User;

public class UserServiceImp implements UserService {
	
	private static UserServiceImp instance;
	
    private UserDAO dao = new UserImp();

    private UserServiceImp() {}
    
    public static UserServiceImp getInstance() {
        if (instance == null) instance = new UserServiceImp();
        return instance;
    }

	@Override
	public void registerUser(User user) {
		// TODO Auto-generated method stub
		dao.registerUser(user) ;
		
	}

	@Override
	public User loginUser(String username, String password) {
		// TODO Auto-generated method stub
		return dao.loginUser(username, password);
	}

}
