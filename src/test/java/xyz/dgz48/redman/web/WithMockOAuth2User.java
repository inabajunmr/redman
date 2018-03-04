package xyz.dgz48.redman.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

/**
 * Annotation for test with OAuth2 Authentication.
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithOAuth2SecurityContextFactory.class)
public @interface WithMockOAuth2User {

	/**
	 * Username.
	 *
	 * @return username
	 */
	String username() default "testuser";

}
