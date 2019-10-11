package com.community.controller;

import com.community.dto.AccesstokenDTO;
import com.community.dto.GitHubUser;
import com.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code",required = false) String code,@RequestParam(name = "state",required = false) String state){

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id("Iv1.7dfb08ee8d449c3b");
        accesstokenDTO.setClient_secret("28b16030a5ad967b1788eae7812cd7dbfcd4ee36");
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_uri("http://localhost:8090/callback");
        String accessToken = gitHubProvider.getaccesstokentDTO(accesstokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());

        return "index";
    }
}
