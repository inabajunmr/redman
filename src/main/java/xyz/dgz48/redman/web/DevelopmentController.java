package xyz.dgz48.redman.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 開発者目的の{@link Controller}.
 *
 * @author win2cot
 *
 */
@Controller
@Profile("debug")
@Slf4j
public class DevelopmentController {

	/**
	 * path:/starter.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/starter.html")
	public String viewStarter(final Model model) {
		log.debug("View /starter.html");
		return "starter";
	}

}
