package com.community.controller;

import com.community.dto.AccesstokenDTO;
import com.community.dto.GitHubUser;
import com.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Value("${github.redirect_uri}")
    private String redirect_uri;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code",required = false) String code,@RequestParam(name = "state",required = false) String state){

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id(client_id);
        accesstokenDTO.setClient_secret(client_secret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_uri(redirect_uri);
        String accessToken = gitHubProvider.getaccesstokentDTO(accesstokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(client_id);
        System.out.println(client_secret);
        System.out.println(redirect_uri);
        System.out.println(user.getName());

        return "index";
    }
}
