package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.PlaceReservation;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.sports.model.dto.User;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(User user);

    User selectById(String userId);

    User selectByUser(User user);

    int selectUserStamp(String userId);


}
