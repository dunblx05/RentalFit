package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dao.UserDao;
import com.ssafy.sports.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int join(User user) {
        return userDao.insert(user);
    }

    @Override
    public User login(String id, String pass) {
        User user = userDao.selectById(id);
        if (user != null && user.getUserPwd().equals(pass)) {
            user.setUserPwd("");
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean isUsedId(String id) {
        return userDao.selectById(id) != null;
    }

    @Override
    public User selectUser(String id) {
        User user = userDao.selectById(id);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public int selectStamp(String id) {
        return userDao.selectUserStamp(id);
    }

	@Override
	public int updateStamp(User user) {
		return userDao.updateStamp(user);
	}

}
