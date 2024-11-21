package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dto.PlaceReservation;
import com.ssafy.sports.model.dto.User;

import java.util.List;

public interface UserService {
	
	public int join(User user);
	
	public User login(String id, String pass);
	
	public boolean isUsedId(String id);
	
	public User selectUser(String id);

	public int selectStamp(String id);


}
