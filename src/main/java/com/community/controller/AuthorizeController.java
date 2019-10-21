package com.community.controller;

import com.community.dto.AccesstokenDTO;
import com.community.dto.GitHubUser;
import com.community.model.User;
import com.community.provider.GitHubProvider;
import com.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Classname AuthorizeController
 * @Description TODO
 * @Date 2019/10/11 20:23
 * @Created by Tengxq
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.redirect_uri}")
    private String redirect_uri;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "state", required = false) String state,
                           HttpServletRequest request, HttpServletResponse response) {

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id(client_id);
        accesstokenDTO.setClient_secret(client_secret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_uri(redirect_uri);
        String accessToken = gitHubProvider.getaccesstokentDTO(accesstokenDTO);
        GitHubUser gitHubUseruser = gitHubProvider.getUser(accessToken);
        if (null == gitHubUseruser) {
            return "redirect:/";
        } else {
            User user = new User();
            user.setName(gitHubUseruser.getName());
            user.setAccountId(String.valueOf(gitHubUseruser.getId()));
            user.setToken(UUID.randomUUID().toString());
            user.setBio(gitHubUseruser.getBio());
            user.setAvatarUrl(gitHubUseruser.getAvatar_url());
            userService.insertUser(user);
            response.addCookie(new Cookie("token", user.getToken()));
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }
    }
}
