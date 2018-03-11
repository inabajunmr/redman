package xyz.dgz48.redman.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.dgz48.redman.domain.user.User;
import xyz.dgz48.redman.domain.user.UserService;


/**
 * Controller for login.
 */
@Controller
@Slf4j
public class LoginController {

    /**
     * Service for {@link User}.
     */
    @Autowired
    UserService userService;

    /**
     * 認証後に表示するページ。
     *
     * @param authentication authentication info
     * @return user info
     */
    @GetMapping("/")
    public String index(final OAuth2AuthenticationToken authentication) {
        log.info("Login user:{}", authentication.getName());
//        String name = authentication.getName();
//
//        userService.findUserByIdpUserName( );
        return "index";
    }

    /**
     * ログイン画面を表示する.
     *
     * @return login page
     */
    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }
}
