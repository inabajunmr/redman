package xyz.dgz48.redman.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for login.
 */
@Controller
@Slf4j
public class LoginController {

    /**
     * 認証後に表示するページ.
     *
     * @param authentication authentication info
     * @return index page
     */
    @GetMapping({"/", "/index.html"})
    public String index(final OAuth2AuthenticationToken authentication) {
        log.info("Login user:{} from:{}",
                authentication.getName(), authentication.getAuthorizedClientRegistrationId());
        log.debug("Login user attributes:{}", authentication.getPrincipal().getAttributes());

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
