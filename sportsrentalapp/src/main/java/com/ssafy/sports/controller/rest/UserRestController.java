package com.ssafy.sports.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.ssafy.sports.model.dto.PlaceReservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sports.model.dto.User;
import com.ssafy.sports.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin("*")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    UserService uService;

    @PostMapping
    @Operation(summary = "사용자 정보를 추가한다. 성공하면 true를 리턴한다. "
            , description = "<pre> json중에서 id, name, pass만 입력해도 정상동작한다. \n"
            + "아래는 aa12 사용자를 추가하는 샘플코드\n "
            + "{\r\n"
            + "  \"id\": \"aa12\",\r\n"
            + "  \"name\": \"aa12\",\r\n"
            + "  \"pass\": \"aa12\",\r\n"
            + "}"
            + "</pre>")
    public Boolean insert(@RequestBody User user) {
        logger.debug("user.insert {}", user);
        int result = 0;
        try {
            result = uService.join(user);
        } catch (Exception e) {
            result = -1;
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }


    @GetMapping("/isUsed")
    @Operation(summary = "request parameter로 전달된 id가 이미 사용중인지 반환한다.")
    public Boolean isUsedId(String id) {
        return uService.isUsedId(id);
    }

    @GetMapping("/stamp")
    @Operation(summary = "전달된 id의 스탬프 개수를 반환한다.")
    public int selectStamp(String id) {
        return uService.selectStamp(id);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 처리 후 성공적으로 로그인 되었다면 loginId라는 쿠키를 내려보낸다."
            , description = "<pre>id와 pass 두개만 넘겨도 정상동작한다. \n 아래는 id, pass만 입력한 샘플코드\n"
            + "{\r\n"
            + "  \"id\": \"aa12\",\r\n"
            + "  \"pass\": \"aa12\"\r\n"
            + "}"
            + "</pre>")

    public User login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
        User selected = uService.login(user.getUserId(), user.getUserPwd());
        if (selected != null) {
            Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getUserId(), "utf-8"));
//            Cookie cookie = new Cookie("loginId", selected.getId());
            cookie.setMaxAge(60 * 60 * 24 * 30); //30일
            response.addCookie(cookie);
        }
        return selected;
    }

}
