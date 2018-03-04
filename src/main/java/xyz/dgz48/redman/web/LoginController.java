package xyz.dgz48.redman.web;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controller for login.
 */
@Controller
public class LoginController {

    /**
     * 認証後に表示するページ。最終的にリソースの一覧か何かになると思う.
     *
     * @param authentication authentication info
     * @return user info
     */
    @GetMapping("/")
    public String index(final OAuth2AuthenticationToken authentication) {
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
