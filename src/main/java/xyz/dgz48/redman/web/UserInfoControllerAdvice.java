package xyz.dgz48.redman.web;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import xyz.dgz48.redman.domain.user.*;


/**
 * ControllerAdvice for set user info into model.
 */
@ControllerAdvice
@Slf4j
public class UserInfoControllerAdvice {

	/**
	 * Service for {@link User}.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Factory for {@link User}.
	 */
	@Autowired
	private UserFactory userFactory;

	/**
	 * Create {@link UserInfo} and set to model.
	 * @param model model
	 */
	@ModelAttribute
	public void userInfoAttributes(final Model model) {
		SecurityContextHolder.getContext();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		log.debug("Invoke UserInfoControllerAdvice.");


		// when implement login by redman as idp, implement for other type of Authentication.
		log.debug("Get authentication.");
		if (!(authentication instanceof OAuth2AuthenticationToken)) {
			log.debug("Authentication is not Outh2AuthenticationToken.");
		 	return;
		}

		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		IdpType idpType = IdpType.findByClientRegistrationId(token.getAuthorizedClientRegistrationId());

		UserInfoExtractor userInfoExtractor = new UserInfoExtractor(idpType);
		Optional<User> userByIdpUserName = userService.findUserByIdpUserName(token.getName(), idpType);
		User user = userByIdpUserName.orElseGet(() -> userService.saveUser(userFactory.createWithRandomId(authentication.getName(),
				userInfoExtractor.getEmail(token.getPrincipal().getAttributes()), idpType)));

		model.addAttribute("userInfo", new UserInfo(user.getUserId(), user.getEmail()));
	}
}
